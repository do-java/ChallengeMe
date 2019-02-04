<template>
	<div class="container shadow">
		<div class="row">
			<div class="col">
				<h4>Challenge Edit</h4>
				<challenge-edit-form v-if="challenge" :item="challenge"></challenge-edit-form>
				<p v-else>Challenge with id: {{id}} not found</p>
			</div>
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