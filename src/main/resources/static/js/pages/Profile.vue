<template>
    <v-container>
        <v-layout justify-space-around>
            <v-flex >
                <h3 class="tittle mb-3">User profile</h3>
                <v-layout row justify-space-between>
                    <v-flex class="px-1" xs4>
                        <v-img :src="profile.userPic"></v-img>
                    </v-flex>
                    <v-flex class="px-1">
                        <v-layout column>
                            <v-flex>{{profile.name}}</v-flex>
                            <v-flex>{{profile.email}}</v-flex>
                            <v-flex>{{profile.gender}}</v-flex>
                            <v-flex>{{profile.locale}}</v-flex>
                            <v-flex>{{profile.lastVisit}}</v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import { mapState } from 'vuex'

    export default {
        name: 'Profile',
        computed: {
            ...mapState(['profile'])
        },
    }
</script>

<style scoped>
    img {
        max-width: 100%;
        height: auto;
    }
</style>