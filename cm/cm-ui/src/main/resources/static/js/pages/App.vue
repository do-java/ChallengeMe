<template>
	<div>
		<div class="row">
		   <div class="col-xl-2"/>
		   <div class="col-xl-8">
			   <navigation></navigation>
			   <router-view/>
		   </div>
		   <div class="col-xl-2"/>
		</div>
		<footer class="text-center mt-3">Copyright © Do Java 2018</footer>
   </div>
</template>

<script>
   import Navigation from 'components/common/Navigation.vue'

   export default {
   		components: {
			Navigation
		},
   }
</script>

<style>

</style>