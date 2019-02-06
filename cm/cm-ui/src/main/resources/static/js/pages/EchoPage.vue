<template>
	<div class="card">
	   <h2 class="card-header">Echo Page</h2>
	   <div class="card-body">
	   	   <alerts :addEventHandlerFunction="addEventHandlerFunction"></alerts>
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
   import Alerts from 'components/common/Alerts.vue'
   import { addEchoHandler } from 'util/websocket'

   export default {
		components: {
			EchoList,
			EchoAddForm,
			EchoGet,
			Alerts,
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
		},
		methods: {
			addEventHandlerFunction: function(data) {
				addEchoHandler(data);
			}
		}
   }
</script>

<style>

</style>