<template>
    <v-layout align-space-around justify-start column>
        <message-form v-bind:messages="mes" v-bind:messageAttr="m1"/>
        <message-row v-for="m in mes"
                     v-bind:key="m.id"
                     v-bind:message="m"
                     v-bind:editMessage="editMethod"
                      />
    </v-layout>
</template>

<script>
    import MessageRow from 'components/app/messages/MessageRow.vue'
    import MessageForm from 'components/app/messages/MessageForm.vue'

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
              }
        }
    }
</script>

<style>

</style>