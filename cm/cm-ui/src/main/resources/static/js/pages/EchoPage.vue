<template>
	<div>
	   <h2>Echo Page</h2>
	   <echo-add-form :items="echos"></echo-add-form>
	   <echo-list :items="echos"></echo-list>
	   <br/>
	   <echo-get :id="11"></echo-get>
	   <echo-get :id="11"></echo-get>
	   <echo-get :id="11"></echo-get>
	   <echo-get :id="11"></echo-get>
	   <echo-get :id="11"></echo-get>
	   <echo-get :id="11"></echo-get>
	   <echo-get :id="11"></echo-get>
   </div>
</template>

<script>
   import EchoList from 'components/echo/EchoList.vue'
   import EchoAddForm from 'components/echo/EchoAddForm.vue'
   import EchoGet from 'components/echo/EchoGet.vue'

   export default {
		components: {
			EchoList,
			EchoAddForm,
			EchoGet
		},
		data: function() {
			return {
				echos: []
			}
		},
		created: function () {
			this.$resource('/rest/echos{/id}').get().then(result =>
				result.json().then(data =>
					data.forEach(item => this.echos.push(item))
				)
			)
		}
   }
</script>

<style>

</style>