<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
    <v-card>
        <v-flex v-if="type === 'href'" xs12 sm6 offset-sm3>

            <v-img v-if="message.linkCover"
                   v-bind:src="message.linkCover"
                   aspect-ratio="2.75">
            </v-img>

            <v-card-title>
                <div>
                    <h3>
                        <a v-bind:href="message.link">{{ message.linkTitle || message.link}}</a>
                    </h3>
                    <div v-if="message.linkDescription">{{ message.linkDescription }}</div>
                </div>
            </v-card-title>
        </v-flex>

        <v-flex v-if="type === 'image'" xs12 sm6 offset-sm3>
            <a v-bind:href="message.link">
                 <v-img v-if="message.linkCover"
                        v-bind:src="message.linkCover"
                        aspect-ratio="2.75">
                 </v-img>
                {{message.link}}
            </a>
        </v-flex>

        <v-flex v-if="type === 'youtube'" xs12 sm6 offset-sm3>
            <you-tube v-bind:src="message.link" >

            </you-tube>
        </v-flex>

    </v-card>
</template>

<script>

import YouTube from 'components/media/YouTube.vue'

export default {

    name: 'Media',
    components: { YouTube },
    props: ['message'],
    data: function() {
        return {
            type: 'href'
        }
    },
    beforeMount() {
        if(this.message.link.indexOf('youtu') > -1) {
            this.type = 'youtube'
        } else if (this.message.link.match(/\.(jpeg|jpg|gif|png)$/) !== null) {
            this.type = 'image'
        } else {
            this.type = 'href'
        }
    }
}

</script>

<style scoped>

</style>