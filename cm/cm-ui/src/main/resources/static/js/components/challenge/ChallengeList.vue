<template>
	<div>
		<challenge-list-item v-for="item in items" :items="items" :item="item" :key="item.id"/>
	</div>
</template>

<script>
	import ChallengeListItem from 'components/challenge/ChallengeListItem.vue'

	export default {
		components: {
			ChallengeListItem
		},
		props: {
		  items: Array
		},

    }
</script>