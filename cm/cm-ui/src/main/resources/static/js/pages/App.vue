<template>
	<div>
		<div class="row">
		   <div class="col-sm-2"/>
		   <div class="col-sm-8">
			   <navigation></navigation>
			   <router-view/>
		   </div>
		   <div class="col-sm-2"/>
		</div>
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