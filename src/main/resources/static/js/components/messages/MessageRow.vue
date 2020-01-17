<template xmlns:v-on="http://www.w3.org/1999/xhtml">
    <v-card class="my-2">
        <v-card-text primary-tittle>
            <i>{{ message.id }}.</i> {{ message.text }}
        </v-card-text>

        <media v-if="message.link" v-bind:message="message"></media>

        <v-card-actions>
            <v-btn value="Edit" v-on:click="edit" small text rounded>Edit</v-btn>
            <v-btn icon v-on:click="del" small>
                <v-icon>
                    delete
                </v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
   import { mapActions  } from 'vuex'
   import Media from 'components/media/Media.vue'

   export default {
    props: ['message', 'editMessage'],
    components: { Media },
    methods: {
        ...mapActions(['removeMessageAction']),
        edit: function() {
            this.editMessage(this.message);
        },
        del: function() {
            this.removeMessageAction(this.message);
        }
    }
   }
</script>

<style>

</style>