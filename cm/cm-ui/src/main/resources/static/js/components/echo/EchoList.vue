<template>
	<div>
		<echo-list-item v-for="item in items" :items="items" :item="item" :key="item.id"/>
	</div>
</template>

<script>
	import EchoListItem from 'components/echo/EchoListItem.vue'

	export default {
		components: {
			EchoListItem
		},
		props: {
		  items: Array
		},

    }
</script>