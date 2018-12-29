<template>
	<div class="card mb-4 shadow">
		<div class="card-body">
			<div class="row mb-2">
				<div class="col">
					<router-link class="text-dark" :to="{ name: 'ChallengeEdit', params: {id: item.id}}">
						<div>
							<b>[{{item.id}}] {{item.name}}</b>
						</div>
						<div>
							<img :src="item.pictureFilename | toChallengePictureUrl" class="img-fluid w-100" alt="Challenge picture"></img>
						</div>
					</router-link>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<b>Description:</b> {{item.description}}</b>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<b>Timeline:</b> {{item.startDate | formatDateTime}} - {{item.endDate | formatDateTime}}</b>
				</div>
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
				name: this.item.name
			}
		},
		methods: {

		}
   }
</script>

<style>

</style>