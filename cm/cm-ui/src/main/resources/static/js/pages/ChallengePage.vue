<template>
	<div class="row">
		<div class="col-3">
			<div class="container shadow">
				<challenge-filter :filter="filter" :items="challenges" :filteredItems="filteredChallenges"></challenge-filter>
			</div>
		</div>

		<div class="col">
			<div class="row mb-2">
				<div class="col">
					<div class="input-group input-group-sm">
						<input class="form-control" v-model="filter.searchQuery" placeholder="Quick search..">
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
		<div class="col-2">
			<div class="container shadow">
				<challenge-ordering :orderProp="filter.order" v-on:order-updated="orderUpdated"></challenge-ordering>
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
				challenges: [],
				filter: {
					searchQuery: '',
					order: {
						by: 'id',
						direction: 'desc'
					}
				}
			}
		},
		computed: {
			filteredChallenges() {
				var filtered = this.challenges.filter(item => {
					const query = this.filter.searchQuery.toLowerCase();

					if (!query) { // Empty search query
						return true;
					}

					return item.id == query
						|| item.name.toLowerCase().includes(query)
						|| item.description.toLowerCase().includes(query);

				});

				var sorted = _.orderBy(filtered, this.filter.order.by, this.filter.order.direction);

				return sorted;
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
            	this.filter.order = data;
            }
		}
   }
</script>

<style>

</style>