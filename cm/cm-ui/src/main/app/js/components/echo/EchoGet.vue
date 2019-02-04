<template>
	<div v-if="echo">Echo[{{echo.id}}]: {{echo.value}}</div>
</template>

<script>
   export default {
   		props: {
   		  id: Number
   		},
		data: function() {
			return {
				echo: null
			}
		},
		created: function () {
			this.$resource('/rest/echos{/id}').get({id: this.id}).then(result =>
				result.json().then(data => {
						this.echo = data;
					}
				)
			)
		}
   }
</script>

<style>

</style>