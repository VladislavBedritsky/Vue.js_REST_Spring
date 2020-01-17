import Vue from 'vue'
import Vuetify from 'vuetify'
import 'api/resource'
import router from 'router/router'
import App from 'pages/App.vue'
import store from 'store/store'
import { connect } from './util/websocket'
import 'vuetify/dist/vuetify.min.css'

if (frontendData.profile) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store: store,
    router: router,
    render: a => a(App),
    vuetify: new Vuetify()
})
