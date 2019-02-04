<template>
	<div class="form-group row">
		<div class="col-sm-6">
			<input class="form-control" v-model="name" placeholder="Challenge"/>
		</div>
		<div class="col">
			<button class="btn btn-primary" @click="create">Create</button>
			<router-link class="btn btn-primary" to="/challenge/add">Create on New Page</router-link>
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
				name: '',
				type: '',
				description: '',
				priority: '',
				maxMembers: '',
				access: '',
				status: '',
				startDate: '',
				endDate: ''

			}
		},
		methods: {
			create: function() {
				var challengeToCreate = {id: null, name: this.name, type: this.type, description: this.description,
				 priority: this.priority, maxMembers: this.maxMembers, access: this.access,
				  status: this.status, startDate: this.startDate, endDate: this.endDate};
				this.$resource('/rest/challenges{/id}').save({}, challengeToCreate).then(result =>
					result.json().then(data => {
						this.items.push(data);
						this.name = '';
					})
				)
			}

		}
   }
</script>

<style>

</style>