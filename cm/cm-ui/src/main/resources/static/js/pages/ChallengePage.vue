<template>
	<div class="card">
	   <h2 class="card-header">Challenge Page</h2>
	   <div class="card-body">
			<router-link class="btn btn-primary" to="/challenge/add">Add</router-link>
			<hr/>
			<challenge-list :items="challenges"></challenge-list>
		</div>
   </div>
</template>

<script>
	import ChallengeList from 'components/challenge/ChallengeList.vue'

   export default {
		components: {
			ChallengeList,
		},
		data: function() {
			return {
				challenges: []
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