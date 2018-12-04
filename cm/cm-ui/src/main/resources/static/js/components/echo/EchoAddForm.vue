<template>
	<div class="form-group row">
		<div class="col-sm-6">
			<input class="form-control" v-model="value" placeholder="Echo Value"/>
		</div>
		<div class="col">
			<button class="btn btn-primary" @click="create">Add</button>
			<router-link class="btn btn-primary" to="/echo/add">Add on New Page</router-link>
		</div>
	</div>
</template>

<script>
   export default {
		props: {
			items: Array
		},
		data: function() {
			return {
				value: ''
			}
		},
		methods: {
			create: function() {
				var echoToCreate = {id: null, value: this.value};
				this.$resource('/rest/echos{/id}').save({}, echoToCreate).then(result =>
					result.json().then(data => {
						this.items.push(data);
						this.value = '';
					})
				)
			}

		}
   }
</script>

<style>

</style>