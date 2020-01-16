<template>
    <v-layout align-space-around justify-start column>
        <message-form v-bind:messages="mes" v-bind:messageAttr="m1"/>
        <message-row v-for="m in mes"
                     v-bind:key="m.id"
                     v-bind:message="m"
                     v-bind:editMessage="editMethod"
                     v-bind:messages="mes"
                     v-bind:deleteMessage="deleteMethod" />
    </v-layout>
</template>

<script>
    import MessageRow from 'components/app/messages/MessageRow.vue'
    import MessageForm from 'components/app/messages/MessageForm.vue'
    import messageApi from 'api/messages'
    import { mapActions  } from 'vuex'

    export default {
        props: ['mes'],
        components: {
            MessageRow,
            MessageForm
        },
        data:
             function() {
                return {
                    m1: null
                }
             },
        methods: {
              ...mapActions(['removeMessageAction']),

              editMethod: function(message) {
                 this.m1 = message;
              },
              deleteMethod: function(message) {
/*                  messageApi.remove(message.id);   */

                    this.removeMessageAction(message);

              }

        }
    }
</script>

<style>

</style>