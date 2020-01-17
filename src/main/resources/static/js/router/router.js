import Vue from 'vue'
import VueRouter from 'vue-router'
import MessagesList from 'pages/MessageList.vue'
import Some from 'pages/Some.vue'
import Auth from 'pages/Auth.vue'
import Profile from 'pages/Profile.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/', component: MessagesList },
    { path: '/some', component: Some },
    { path: '/auth', component: Auth },
    { path: '/profile', component: Profile },
    { path: '*', component: MessagesList }
]

export default new VueRouter({
    mode: 'history',
    routes
})