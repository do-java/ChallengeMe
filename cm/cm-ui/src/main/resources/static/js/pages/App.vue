<template>
	<div class="container">
	   <h1>App Component</h1>
	   <echo-page></echo-page>
   </div>
</template>

<script>
   import EchoPage from 'pages/EchoPage.vue'

   export default {
   		components: {
			EchoPage
		},
   }
</script>

<style>

</style>