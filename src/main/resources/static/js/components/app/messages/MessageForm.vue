<template xmlns:v-on="http://www.w3.org/1999/xhtml">
    <v-layout>
        <v-text-field
                label="Vue"
                v-model="text"
                required />
        <v-btn id="save_btn" value="Save"
               v-on:click="save">
            Save
        </v-btn>
    </v-layout>
</template>

<script>
    import messageApi from 'api/messages'
    import { lastIndexId } from 'util/collections'
    import { getIndex } from 'util/collections'

/*
    import { sendMessage } from 'util/websocket'
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

/* read me.md
                if (this.text != 0) {
                    sendMessage({id: this.id, text: this.text});
                    this.text="";
                    this.id="";
                }
*/
            const index = getIndex(this.messages, this.id);
            const message = {id: this.id, text: this.text };

            if (this.text != 0) {
                if (index > -1) {
                    messageApi.update(message).then(result =>
                    result.json().then(data => {
                         this.messages.splice(index, 1, data);
                                     this.text="";
                                     this.id="";
                }
                ))
            } else {
                    messageApi.add(message).then(result =>
                        result.json().then(data => {
                            this.messages.push(data);
                                 this.text="";
                        }))
            }


         }
/*
*/
        }
     }
    }
</script>

<style>

#save_btn {
    margin-top: 10px;
    margin-left: 10px;
}

</style>