<template>
	<div v-if="challenge">Challenge[{{challenge.id}}]: {{challenge.name}}</div>
</template>

<script>
   export default {
   		props: {
   		  id: Number
   		},
		data: function() {
			return {
				challenge: null
			}
		},
		created: function () {
			this.$resource('/rest/challenges{/id}').get({id: this.id}).then(result =>
				result.json().then(data => {
						this.challenge = data;
					}
				)
			)
		}
   }
</script>

<style>

</style>