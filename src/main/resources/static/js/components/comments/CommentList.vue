<template xmlns:v-bind="http://www.w3.org/1999/XSL/Transform">
    <v-list two-line>
        <v-subheader>
            Comments
        </v-subheader>
        <template
                v-for="(item, index) in comments"
        >
            <v-divider v-if="index > 0"
                       v-bind:key="index">
            </v-divider>
            <comment-item
                    v-bind:comment="item"
                    v-bind:key="'item'+index">
            </comment-item>
        </template>

        <comment-form v-bind:message-id="messageId">

        </comment-form>

    </v-list>
</template>

<script>
    import CommentItem from 'components/comments/CommentItem.vue'
    import CommentForm from 'components/comments/CommentForm.vue'


    export default {
        name: 'CommentList',
        props: ['comments', 'messageId'],
        components: { CommentItem, CommentForm}
    }
</script>

<style>

</style>