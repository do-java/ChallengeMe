import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
import $ from 'jquery'
window.jQuery = $;
window.$ = $;

Vue.use(VueResource)

new Vue({
    el: '#app',
    render: h => h(App),
});