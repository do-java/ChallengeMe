<template>
	<div class="form-group">
		<div class="row">
			<div class="col">{{item.id}}: {{item.value}}</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<input class="form-control" v-model="name"/>
			</div>
			<div class="col">
				<button class="btn btn-primary" @click="update">Update</button>
				<button class="btn btn-primary" @click="del">X</button>
				<router-link class="btn btn-primary" :to="{ name: 'ChallengeEdit', params: {id: item.id}}">Edit</router-link>
			</div>
		</div>
	</div>
</template>
<script>
   export default {
		props: {
		  items: Array,
		  item: Object
		},
		data: function() {
			return {
				name: this.item.name
			}
		},
		methods: {
			update: function() {
				var challengeToUpdate = {id: this.item.id, value: this.value};
				this.$resource('/rest/challenges{/id}').update({id: this.item.id}, challengeToUpdate).then(result =>
					result.json().then(data => {
						this.item.name = data.name;
						this.name = data.name;
					})
				)
			},
			del: function() {
				this.$resource('/rest/challenges{/id}').delete({id: this.item.id}).then(result => {
					this.items.splice($.inArray(this.item, this.items), 1)
				})

			},

		}
   }
</script>

<style>

</style>