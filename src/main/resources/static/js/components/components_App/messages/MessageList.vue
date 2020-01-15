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
    import MessageRow from 'components/components_App/messages/MessageRow.vue'
    import MessageForm from 'components/components_App/messages/MessageForm.vue'

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
              editMethod: function(message) {
                 this.m1 = message;
              },
              deleteMethod: function(message) {
                   this.$resource('/message{/id}').remove({id: message.id}).then(result => {
                      if ( result.ok ) {
                          this.mes.splice(this.mes.indexOf(message),1)
                      }
                   })
              }
        }
    }
</script>

<style>

</style>