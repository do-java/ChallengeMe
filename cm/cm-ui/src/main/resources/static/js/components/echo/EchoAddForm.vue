<template>
	<div>
		<input v-model="value" placeholder="Echo Value"/>
		<button @click="create">Create</button>
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