<template>
	<div class="card">
	   <h2 class="card-header">Challenge Add</h2>
	   <div class="card-body">
		   <challenge-edit-form :item="{id: null, name:'Sport Event', type:'Sport', description:'UEFA championship', priority:4, maxMembers:8, access:'PRIVATE', status:'ACTIVE', startDate: '2018-11-15T16:30:00.000+0000', endDate: null}"></challenge-edit-form>
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