<template>
    <v-container>
        <v-layout align-space-around justify-start column>
             <message-form v-bind:messages="sortedMessages" v-bind:messageAttr="m1"/>
             <message-row v-for="m in sortedMessages"
                     v-bind:key="m.id"
                     v-bind:message="m"
                     v-bind:editMessage="editMethod"
                      />
        </v-layout>
    </v-container>
</template>

<script>
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'
    import { mapGetters } from 'vuex'

    export default {
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
        computed: {
            ...mapGetters(['sortedMessages'])
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