<template>
	<div class="row">
		<div class="col-3">
			<div class="container shadow">
				<challenge-filter></challenge-filter>
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
		<div class="col-2"></div>
   </div>
</template>

<script>
	import ChallengeList from 'components/challenge/ChallengeList.vue'
	import ChallengeFilter from 'components/challenge/ChallengeFilter.vue'

   export default {
		components: {
			ChallengeList,
			ChallengeFilter,
		},
		data: function() {
			return {
				challenges: [],
				filter: {
					searchQuery: ''
				}
			}
		},
		computed: {
			filteredChallenges() {
				return this.challenges.filter(item => {
					const query = this.filter.searchQuery.toLowerCase();

					if (!query) { // Empty search query
						return true;
					}

					return item.id == query
						|| item.name.toLowerCase().includes(query)
						|| item.description.toLowerCase().includes(query);

				});
			}
		},
		created: function () {
			this.$resource('/rest/challenges{/id}').get().then(result =>
				result.json().then(data =>
					data.forEach(item => this.challenges.push(item))
				)
			)
		}
   }
</script>

<style>

</style>