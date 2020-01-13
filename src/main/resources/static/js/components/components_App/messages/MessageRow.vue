<template xmlns:v-on="http://www.w3.org/1999/xhtml">
    <div>
    {{ message.id }}. {{ message.text }}
    <span style="position: absolute; right: 0">
            <input type="submit" value="Edit" v-on:click="edit" />
            <input type="submit" value="X" v-on:click="del" />
    </span>
    </div>
</template>

<script>
   export default {
    props: ['message', 'editMessage', 'deleteMessage', 'messages'],
    methods: {
        edit: function() {
            this.editMessage(this.message);
        },
        del: function() {
            this.deleteMessage(this.message);
        }
    }
   }
</script>

<style>

</style>