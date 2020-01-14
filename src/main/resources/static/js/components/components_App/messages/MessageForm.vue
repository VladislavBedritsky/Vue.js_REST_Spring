<template xmlns:v-on="http://www.w3.org/1999/xhtml">
    <div>
        <input type="text" placeholder="vue" v-model="text" />
        <input type="submit" value="Save" v-on:click="save" />
    </div>
</template>

<script>
    import { sendMessage } from 'util/websocket'

/*
id="before I integrated websocket"
*/
/*
    function getIndex (list, id) {
        for (var i = 0; i < list.length; i++) {
            if (list[i].id === id) {
                return i;
            }
        }
         return -1;
    }

    function lastIndexId(list) {
        return list[list.length - 1];
    }
    */

    export default {
        props: ['messages', 'messageAttr'],
        data:
             function () {
                return {
                    text: '',
                    id: ''
                }
             },
        watch: {
             messageAttr: function(newVal, oldVal) {
                    this.text = newVal.text;
                    this.id = newVal.id;
             }
        },
        methods: {
          save: function () {
                sendMessage({id: this.id, text: this.text});
                this.text="";
                this.id="";


/*
id="before I integrated websocket"
*/
/*            const message = {id: this.id, text: this.text };

            if (this.text != 0) {
                if (this.id <= lastIndexId(this.messages).id && this.id > 0) {
                    this.$resource('/message{/id}').update( {id: this.id}, message).then(result =>
                    result.json().then(data => {
                         const index = getIndex(this.messages, data.id);
                         this.messages.splice(index, 1, data);
                         this.text="";
                         this.id="";
                }
                ))
            } else {
                    this.$resource('/message{/id}').save({}, message).then(result =>
                        result.json().then(data => {
                            this.messages.push(data);
                            this.text="";
                        }))
            }
         }
            */





        }
     }
    }
</script>

<style>

</style>