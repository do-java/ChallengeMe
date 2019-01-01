<template>
	<div class="container shadow">
		<div class="row">
			<div class="col">
				<h4>Challenge Add</h4>
				<challenge-edit-form :item="{id: null, name:'Running challenge', type:'Sport', description:'Run 2 km across the streets every evening during the month', priority:4, maxMembers:8, access:'PRIVATE', status:'ACTIVE', startDate: '2019-03-12T16:30:00.000+0000', endDate: null}"></challenge-edit-form>
			</div>
		</div>
	</div>
</template>

<script>
   import ChallengeEditForm from 'components/challenge/ChallengeEditForm.vue'

   export default {
		components: {
			ChallengeEditForm
		}
   }
</script>

<style>

</style>