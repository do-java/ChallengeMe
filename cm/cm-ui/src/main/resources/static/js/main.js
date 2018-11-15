import Vue from 'vue'
import VueResource from 'vue-resource'
import Router from 'vue-router'
import BootstrapVue from "bootstrap-vue"
import App from 'pages/App.vue'
import $ from 'jquery'
window.jQuery = $;
window.$ = $;
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(VueResource)
Vue.use(Router)
Vue.use(BootstrapVue)


import HomePage from 'pages/HomePage.vue'
import EchoPage from 'pages/EchoPage.vue'
import EchoAddPage from 'pages/EchoAddPage.vue'
import EchoEditPage from 'pages/EchoEditPage.vue'
import ChallengePage from 'pages/ChallengePage.vue'


const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: HomePage
    },
    {
	  path: '/echo',
	  name: 'Echo',
	  component: EchoPage
	},
	{
	  path: '/echo/add',
	  name: 'EchoAdd',
	  component: EchoAddPage,
	},
	{
	  path: '/echo/:id',
	  name: 'EchoEdit',
	  component: EchoEditPage,
	  props: true
	},
	{
	  path: '/challenge',
	  name: 'Challenge',
	  component: ChallengePage
	},
  ]
})

new Vue({
	router,
    el: '#app',
    render: h => h(App),
});