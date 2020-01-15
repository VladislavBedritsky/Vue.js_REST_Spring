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
                <messages-list v-bind:mes="messages" />
            </div>
            <div>
                <some-q />
            </div>
        </v-content>
    </v-app>
</template>

<script>
    import MessagesList from 'components/components_App/messages/MessageList.vue'
    import SomeQ from 'components/components_App/some/Some.vue'
    import { addHandler } from 'util/websocket'
    import { getIndex } from 'util/collections'
    import { lastIndexId } from 'util/collections'

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
          created() {
            addHandler(data => {
                let index = getIndex(this.messages, data.id);

                    if (data.id <= lastIndexId(this.messages).id && data.id > 0) {
                        this.messages.splice(index, 1, data);
                    } else {
                        this.messages.push(data);
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