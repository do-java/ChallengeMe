import Vue from 'vue'
import VueResource from 'vue-resource'
import { DateTime } from 'luxon'

import 'bootstrap-cm.scss'
import Datetime from 'vue-datetime'
import 'vue-datetime/dist/vue-datetime.css'
import '@fortawesome/fontawesome-free/js/all.js'

import router from './router/router';
import App from 'pages/App.vue'

import $ from 'jquery'
window.jQuery = $;
window.$ = $;

Vue.use(VueResource)
Vue.use(Datetime)


// Filters
Vue.filter('formatDateTime', function(value) {
  if (value) {
    return DateTime.fromISO(value).toFormat('yyyy-MM-dd HH:mm');
  }
})


new Vue({
	router,
    el: '#app',
    render: h => h(App),
});