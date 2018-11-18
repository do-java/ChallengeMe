<template>
	<div class="card">
	   <h2 class="card-header">Challenge Edit</h2>
	   <div class="card-body">
		   <challenge-edit-form v-if="challenge" :item="challenge"></challenge-edit-form>
		   <p v-else>Challenge with id: {{id}} not found</p>
		</div>
   </div>
</template>

<script>
   import ChallengeEditForm from 'components/challenge/ChallengeEditForm.vue'

   export default {
   		props: [
   			'id'
   		],
		components: {
			ChallengeEditForm
		},
		data: function() {
			return {
				challenge: null
			}
		},
		created: function () {
			this.$resource('/rest/challenges{/id}').get({id: this.id}).then(result =>
				result.json().then(data =>
					this.challenge = data
				)
			)
		}
   }
</script>

<style>

</style>