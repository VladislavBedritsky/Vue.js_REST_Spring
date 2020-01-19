<template xmlns:v-on="http://www.w3.org/1999/xhtml">
    <v-layout>
        <v-text-field
                label="Vue"
                v-model="text"
                v-on:keyup.enter="save" />
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
    import { mapActions  } from 'vuex'

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
          ...mapActions(['addMessageAction', 'updateMessageAction']),

          save: function () {

            const index = getIndex(this.messages, this.id);
            const message = {id: this.id, text: this.text};

            if (this.text != 0) {
                if (index > -1) {

                   this.updateMessageAction(message);

                } else {

                   this.addMessageAction(message);

                }

                this.id="";
                this.text="";

            }

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