<template>
	<div>
		<span>{{item.id}}: {{item.value}}</span>
		<input v-model="value"/>
		<button @click="update">Update</button>
		<button @click="del">X</button>
		<button @click="doEcho">Do</button>
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