<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
    <v-app>

        <v-app-bar app>
            <v-toolbar-title>Vue</v-toolbar-title>
            <v-spacer></v-spacer>

            <span v-if="profile">{{profile.name}}</span>

            <v-btn v-if="profile" icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>

            <v-btn v-if="!profile" href="/login">
                Google
            </v-btn>

        </v-app-bar>
        <v-content class="content">
            <div v-if="profile">
                <messages-list v-bind:mes="sortedMessages" />
            </div>
            <div>
                <some-q />
            </div>
        </v-content>
    </v-app>
</template>

<script>
    import MessagesList from 'components/app/messages/MessageList.vue'
    import SomeQ from 'components/app/some/Some.vue'
    import { addHandler } from 'util/websocket'

    import { getIndex } from 'util/collections'

    export default {
          components: {
                MessagesList,
                SomeQ
          },
          data: function() {
            return {
              messages: frontendData.messages,
              profile: frontendData.profile
            }
          },
          computed: {
            sortedMessages() {
                return this.messages.sort((a,b) => -(a.id - b.id))
            }
          },
          created() {
            addHandler(data => {
                if(data.objectType === 'MESSAGE') {
                    const index = getIndex(this.sortedMessages, data.body.id);

                    switch(data.eventType) {
                        case 'CREATE':
                            this.sortedMessages.push(data.body);
                        case 'UPDATE':
                           if (index > -1) {
                                this.sortedMessages.splice(index, 1, data.body);
                           }
                            break;
                        case 'REMOVE':
                                this.sortedMessages.splice(index, 1);
                            break;
                        default:
                            console.error(`Looks like the event type is unknown "${data.eventType}"`)
                    }

/* read me.md
                    if (index > -1) {
                        this.sortedMessages.splice(index, 1, data);
                    } else {
                        this.sortedMessages.push(data);
                    }
*/

                } else {
                    console.error(`Looks like the object type is unknown "${data.objectType}"`)
                }

            })
          }
    }
</script>

<style>

.content {
    margin-top: 30px;
    margin-left: 50px;
    margin-right: 50px;
}

</style>