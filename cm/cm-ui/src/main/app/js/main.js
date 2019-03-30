import Vue from 'vue'
import VueResource from 'vue-resource'

import 'bootstrap/dist/js/bootstrap.min.js';
import 'bootstrap-cm.scss'
import Datetime from 'vue-datetime'
import 'vue-datetime/dist/vue-datetime.css'
import '@fortawesome/fontawesome-free/js/all.js'

import router from './router/router';
import store from './store/store'
import { connect } from './util/websocket'
import './snippets/filters'
import App from 'pages/App.vue'

import $ from 'jquery'
window.jQuery = $;
window.$ = $;

connect()

Vue.use(VueResource)
Vue.use(Datetime)

Vue.http.interceptors.push((request, next) => {
    if (localStorage.getItem('userToken')) {
        request.headers.set('Authorization', localStorage.getItem('userToken'));
        request.headers.set('Accept', 'application/json');
    }
    next();
})

new Vue({
	store,
	router,
    el: '#app',
    render: h => h(App),
    mounted: () => {
        if (localStorage.getItem('userToken')) {
            Vue.http.post('/rest/public/user/login', {token: localStorage.getItem('userToken')})
                .then(result => {
                    store.commit('userLogin', {user: result.body.user});
                });
        }
    }
});