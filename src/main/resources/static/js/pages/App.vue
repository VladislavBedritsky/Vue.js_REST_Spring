<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
    <div>

    <div v-if="!profile">Необходимо авторизоваться через
        <a href="/login">Google</a>
    </div>
    <div v-else>
       <div>
            {{profile.name}} <a href="/logout">Выйти</a>
       </div>
        <messages-list v-bind:mes="messages" />
    </div>
    <div>
        <some-q />
    </div>

    </div>
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
                if (data.text != 0 ) {
                    if (data.id <= lastIndexId(this.messages).id && data.id > 0) {
                        this.messages.splice(index, 1, data);
                    } else {
                        this.messages.push(data);
                    }
                }
            })
          }
    }
</script>

<style>

</style>