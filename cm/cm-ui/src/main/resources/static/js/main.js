import Vue from 'vue'
import VueResource from 'vue-resource'
import Router from 'vue-router'

import 'bootstrap-cm.scss'

import Datetime from 'vue-datetime'
import 'vue-datetime/dist/vue-datetime.css'

import { library } from '@fortawesome/fontawesome-svg-core'
import { faCoffee } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

import App from 'pages/App.vue'

import $ from 'jquery'
window.jQuery = $;
window.$ = $;


Vue.use(VueResource)
Vue.use(Router)
Vue.use(Datetime)


import HomePage from 'pages/HomePage.vue'

import EchoPage from 'pages/EchoPage.vue'
import EchoAddPage from 'pages/EchoAddPage.vue'
import EchoEditPage from 'pages/EchoEditPage.vue'

import ChallengePage from 'pages/ChallengePage.vue'
import ChallengeAddPage from 'pages/ChallengeAddPage.vue'
import ChallengeEditPage from 'pages/ChallengeEditPage.vue'


library.add(faCoffee)
Vue.component('font-awesome-icon', FontAwesomeIcon)


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
	{
	  path: '/challenge/add',
	  name: 'ChallengeAdd',
	  component: ChallengeAddPage
	},
	{
	  path: '/challenge/:id',
	  name: 'ChallengeEdit',
	  component: ChallengeEditPage,
	  props: true
	},

  ]
})

new Vue({
	router,
    el: '#app',
    render: h => h(App),
});