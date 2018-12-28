import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  strict: process.env.NODE_ENV !== 'production', // Strict mode for development
  state: {
  	challenge: {
  		filter: {
			searchQuery: '',
			order: {
				by: 'id',
				direction: 'desc'
			}
		}
  	}
  },
  mutations: {
  	/**
  	* Usage: this.$store.commit('updateChallengeFilter', {filterField1: newValue, filterField2: newValue, ...}
  	*/
    updateChallengeFilter(state, payload) {
       if (typeof payload.searchQuery != 'undefined') {
       		state.challenge.filter.searchQuery = payload.searchQuery;
       }
       if (typeof payload.order != 'undefined') {
       		state.challenge.filter.order = payload.order;
       }
    }
  }
})