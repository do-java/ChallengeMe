<template>
	<div class="row">
		<div class="col">
			<div class="row">
				<div class="col text-center pt-2">
					<b class="">Filter</b>
				</div>
				<div class="col-1 d-md-none">
					<div class="navbar-light">
						<button class="btn btn-sm float-right" data-toggle="collapse" data-target="#filterForm">
							<span class="navbar-toggler-icon"></span>
						</button>
					</div>
				</div>
			</div>
			<hr class="mt-1 mb-2"/>
			<div class="collapse show" id="filterForm">
				<div class="form-group form-check">
					<input id="chkMyChallenges" type="checkbox" class="form-check-input" disabled>
					<label for="chkMyChallenges" class="form-check-label">My</label>
				</div>

				<div class="form-group form-check">
					<input id="chkActive" type="checkbox" v-model="filterActive" class="form-check-input" >
					<label for="chkActive" class="form-check-label">Active</label>
				</div>

				<div class="form-group form-check">
					<input id="chkDeadline" type="checkbox" class="form-check-input" disabled>
					<label for="chkDeadline" class="form-check-label">Deadline</label>
				</div>

				<div class="form-group">
					<label>Access</label>
					<select v-model="filterAccess" class="form-control form-control-sm">
						<option value="">All</option>
						<option value="PRIVATE">Private</option>
						<option value="PUBLIC">Public</option>
						<option value="PROTECTED">Protected</option>
					</select>
				</div>

				<div class="form-group">
					<label>Type</label>
					<select v-model="filterType" class="form-control form-control-sm">
						<option value="">All</option>
						<option value="Sport">Sport</option>
						<option value="Reading">Reading</option>
						<option value="Business">Business</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<b>Shown:</b> {{filteredItems.length}} of {{items.length}}</b>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
		  items: Array,
		  filteredItems: Array
		},
		computed: {
			filter() {
				return this.$store.state.challenge.filter;
			},
			filterActive: {
				get () {
				  return this.filter.active
				},
				set (value) {
				  this.$store.commit('updateChallengeFilter', {active: value});
				}
			},
			filterAccess: {
				get () {
				  return this.filter.access
				},
				set (value) {
				  this.$store.commit('updateChallengeFilter', {access: value});
				}
			},
			filterType: {
				get () {
				  return this.filter.type
				},
				set (value) {
				  this.$store.commit('updateChallengeFilter', {type: value});
				}
			},
		},
	}
</script>