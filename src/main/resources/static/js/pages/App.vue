<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
    <v-app>

        <v-app-bar app>
            <v-toolbar-title>Vue</v-toolbar-title>

            <v-btn class="ml-2" text
                   v-if="profile"
                   :disabled="$route.path === '/'"
                   @click="showMessages">
                Messages
            </v-btn>

            <v-spacer></v-spacer>

            <v-btn text
                   v-if="profile"
                   v-bind:disabled="$route.path === '/profile'"
                   v-on:click="showProfile" >
                {{profile.name}}
            </v-btn>

            <v-btn v-if="profile" icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>

            <v-btn v-if="!profile" href="/login">
                Google
            </v-btn>

        </v-app-bar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import { addHandler } from 'util/websocket'
    import { getIndex } from 'util/collections'
    import { mapGetters, mapState, mapMutations  } from 'vuex'

    export default {

          computed: {
            ...mapState(['profile']),
            ...mapGetters(['sortedMessages'])
          },

          methods: {
            ...mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),

            showMessages() {
                this.$router.push('/')
            },
            showProfile() {
                this.$router.push('/profile')
            }
          },

          created() {
            addHandler(data => {
                if(data.objectType === 'MESSAGE') {

                    const index = getIndex(this.sortedMessages, data.body.id);

                    switch(data.eventType) {
                        case 'CREATE':

                            this.addMessageMutation(data.body);

                           break;
                        case 'UPDATE':

                           if (index > -1) {
                              this.updateMessageMutation(data.body);
                           }

                           break;
                        case 'REMOVE':

                             this.removeMessageMutation(data.body);

                            break;
                        default:
                            console.error(`Looks like the event type is unknown "${data.eventType}"`)
                    }
                } else {
                    console.error(`Looks like the object type is unknown "${data.objectType}"`)
                }

            })
          },

          beforeMount() {
                if(!this.profile) {
                    this.$router.replace('/auth')
                }
          }
    }
</script>

<style>

</style>