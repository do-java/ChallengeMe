<template>
	<div class="row">
		<div class="cm-alerts position-absolute  col-xl-12">
			<div v-for="alert in alerts" class="alert alert-item alert-dismissible" :class="alert.class"role="alert">
			  <span>{{alert.message}}</span>
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			  </button>
			</div>
		</div>
	</div>
</template>

<script>
	import { mapState } from 'vuex'

	export default {
		computed: {
			...mapState({
				alerts: state => state.alert.alerts
			})
		}
	}
</script>
