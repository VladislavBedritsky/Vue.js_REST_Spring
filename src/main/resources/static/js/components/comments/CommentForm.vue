<template>
    <v-layout>
        <v-text-field
                label="Add comment"
                v-model="text"
                v-on:keyup.enter="save" />
        <v-btn id="save_btn" value="Save"
               v-on:click="save">
            Add comment
        </v-btn>
    </v-layout>
</template>

<script>
    import { mapActions } from 'vuex'

    export default {
        name: 'CommentForm',
        props: ['messageId'],
        data: function() {
              return {
                text: ''
              }
        },
        methods: {
            ...mapActions(['addCommentAction']),
            save() {
                this.addCommentAction({
                    text: this.text,
                    message: {
                        id: this.messageId
                    }
                }) ;

                this.text = '';

            }
        }
    }
</script>

<style>

</style>