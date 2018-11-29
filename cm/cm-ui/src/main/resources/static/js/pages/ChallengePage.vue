<template>
	<div class="card">
	   <h2 class="card-header">Challenge Page</h2>
	   <div class="card-body">
		      <challenge-add-form :items="challenges"></challenge-add-form>
           		   <hr/>
           	<challenge-list :items="challenges"></challenge-list>
		</div>
   </div>
</template>

<script>
import ChallengeList from 'components/challenge/ChallengeList.vue'
   import ChallengeAddForm from 'components/challenge/ChallengeAddForm.vue'
   import ChallengeGet from 'components/challenge/ChallengeGet.vue'

   export default {
		components: {
			ChallengeList,
			ChallengeAddForm,
			ChallengeGet
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