<template>
	<div>
	   <h2>Echo Page</h2>
	   <echo-add-form :items="echos"></echo-add-form>
	   <echo-list :items="echos"></echo-list>
   </div>
</template>

<script>
   import EchoList from 'components/echo/EchoList.vue'
   import EchoAddForm from 'components/echo/EchoAddForm.vue'

   export default {
		components: {
			EchoList,
			EchoAddForm
		},
		data: function() {
			return {
				echos: [{id: 1, value: 'aaa'}, {id: 2, value: 'bbb'}]
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