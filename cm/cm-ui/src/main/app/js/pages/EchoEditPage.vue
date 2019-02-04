<template>
	<div class="card">
	   <h2 class="card-header">Echo Edit</h2>
	   <div class="card-body">
		   <echo-edit-form v-if="echo" :item="echo"></echo-edit-form>
		   <p v-else>Echo with id: {{id}} not found</p>
		</div>
   </div>
</template>

<script>
   import EchoEditForm from 'components/echo/EchoEditForm.vue'

   export default {
   		props: [
   			'id'
   		],
		components: {
			EchoEditForm
		},
		data: function() {
			return {
				echo: null
			}
		},
		created: function () {
			this.$resource('/rest/echos{/id}').get({id: this.id}).then(result =>
				result.json().then(data =>
					this.echo = data
				)
			)
		}
   }
</script>

<style>

</style>