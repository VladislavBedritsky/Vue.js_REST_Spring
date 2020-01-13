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
          }
    }
</script>

<style>

</style>