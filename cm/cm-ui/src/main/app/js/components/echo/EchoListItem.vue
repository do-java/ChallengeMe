<template>
	<div class="form-group-light">
		<div class="row">
			<div class="col">{{item.id}}: {{item.value}}</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<input class="form-control" v-model="value"/>
			</div>
			<div class="col">
				<button class="btn btn-primary" @click="update">Update</button>
				<router-link class="btn btn-primary" :to="{ name: 'EchoEdit', params: {id: item.id}}">Edit</router-link>
				<button class="btn btn-primary" @click="doEcho">Do</button>
				<button class="btn btn-primary" @click="del">X</button>
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
				value: this.item.value
			}
		},
		methods: {
			update: function() {
				var echoToUpdate = {id: this.item.id, value: this.value};
				this.$resource('/rest/echos{/id}').update({id: this.item.id}, echoToUpdate).then(result =>
					result.json().then(data => {
						this.item.value = data.value;
						this.value = data.value;
					})
				)
			},
			del: function() {
				this.$resource('/rest/echos{/id}').delete({id: this.item.id}).then(result => {
					this.items.splice($.inArray(this.item, this.items), 1)
				})
			},
			doEcho: function() {
				this.$resource('/rest/echos{/id}/do').get({id: this.item.id, n: 8}).then(result =>
					result.text().then(data => {
						console.log(data)
					})
				)
			},

		}
   }
</script>

<style>

</style>