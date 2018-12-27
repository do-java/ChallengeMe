<template>
	<div class="row">
		<div class="col-md-3">
			<div class="container shadow mb-2">
				<challenge-filter :filter="filter" :items="challenges" :filteredItems="filteredChallenges"></challenge-filter>
			</div>
		</div>
		<div class="col-md-2 order-md-last">
			<div class="container shadow mb-2 pt-3">
				<challenge-ordering :orderProp="filter.order" v-on:order-updated="orderUpdated"></challenge-ordering>
			</div>
		</div>
		<div class="col-md px-md-0">
			<div class="container shadow mb-2 pt-3">
			<div class="row mb-2">
				<div class="col">
					<div class="input-group input-group-sm">
						<input class="form-control" v-model="filterSearchQuery" placeholder="Quick search..">
						<div class="input-group-append">
							<div class="input-group-text"><i class="fas fa-search"></i></div>
						</div>
					</div>

				</div>
				<div class="col-1">
					<router-link class="btn btn-primary btn-sm float-right" to="/challenge/add"><i class="fas fa-plus"></i></router-link>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<challenge-list :items="filteredChallenges"></challenge-list>
				</div>
			</div>
			</div>
		</div>
   </div>
</template>

<script>
	import _ from 'lodash'

	import ChallengeList from 'components/challenge/ChallengeList.vue'
	import ChallengeFilter from 'components/challenge/ChallengeFilter.vue'
	import ChallengeOrdering from 'components/challenge/ChallengeOrdering.vue'

   export default {
		components: {
			ChallengeList,
			ChallengeFilter,
			ChallengeOrdering
		},
		data: function() {
			return {
				challenges: []
			}
		},
		computed: {
			filteredChallenges() {
				var filter = this.filter;
				var filtered = this.challenges.filter(item => {
					const query = filter.searchQuery.toLowerCase();

					if (!query) { // Empty search query
						return true;
					}

					return item.id == query
						|| item.name.toLowerCase().includes(query)
						|| item.description.toLowerCase().includes(query);

				});

				var sorted = _.orderBy(filtered, filter.order.by, filter.order.direction);

				return sorted;
			},
			filter() {
				return this.$store.state.challenge.filter;
			},
			filterSearchQuery: {
                get () {
                  return this.filter.searchQuery
                },
                set (value) {
                  this.$store.commit('updateChallengeFilter', {searchQuery: value});
                }
              }
		},
		created: function () {
			this.$resource('/rest/challenges{/id}').get().then(result =>
				result.json().then(data =>
					data.forEach(item => this.challenges.push(item))
				)
			)
		},
		methods: {
            orderUpdated: function(data) {
            	this.$store.commit('updateChallengeFilter', {order: data});
            }
		}
   }
</script>

<style>

</style>