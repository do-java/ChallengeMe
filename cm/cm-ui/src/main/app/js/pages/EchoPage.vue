<template>
	<div class="card">
	   <h2 class="card-header">Echo Page</h2>
	   <div class="card-body">
		   <echo-add-form :items="echos"></echo-add-form>
		   <hr/>
		   <echo-list :items="echos"></echo-list>
		</div>
   </div>
</template>

<script>
   import EchoList from 'components/echo/EchoList.vue'
   import EchoAddForm from 'components/echo/EchoAddForm.vue'
   import EchoGet from 'components/echo/EchoGet.vue'
   import { addEchoHandler } from 'util/websocket'

   export default {
		components: {
			EchoList,
			EchoAddForm,
			EchoGet,
		},
		data: function() {
			return {
				echos: [],
			}
		},
		created: function () {
			this.$resource('/rest/echos{/id}').get().then(result =>
				result.json().then(data =>
					data.forEach(item => this.echos.push(item))
				)
			)

			addEchoHandler(data => {
				this.$store.dispatch('addAlert', { class: 'alert-success', message: 'Echo Event: ' + JSON.stringify(data) });
			});
		},
		methods: {

		}
   }
</script>

<style>

</style>