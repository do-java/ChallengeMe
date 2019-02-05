<template>
	<div class="card">
	   <h2 class="card-header">Echo Add</h2>
	   <div class="card-body">
		   <echo-edit-form :item="{}"></echo-edit-form>
		</div>
   </div>
</template>

<script>
   import EchoEditForm from 'components/echo/EchoEditForm.vue'

   export default {
		components: {
			EchoEditForm
		}
   }
</script>

<style>

</style>