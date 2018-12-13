<template>
	<div class="row">
		<div class="col">
			<div class="text-center mt-1"><b>Filter</b></div>
			<hr class="mt-1 mb-2"/>

			<div class="form-group form-check">
				<input id="chkMyChallenges" type="checkbox" class="form-check-input" >
				<label for="chkMyChallenges" class="form-check-label">My</label>
			</div>

			<div class="form-group form-check">
				<input id="chkActive" type="checkbox" class="form-check-input" >
				<label for="chkActive" class="form-check-label">Active</label>
			</div>

			<div class="form-group form-check">
				<input id="chkDeadline" type="checkbox" class="form-check-input" >
				<label for="chkDeadline" class="form-check-label">Deadline</label>
			</div>

			<div class="form-group">
				<label>Access</label>
				<select class="form-control form-control-sm">
					<option value="">All</option>
					<option value="PRIVATE">Private</option>
					<option value="PUBLIC">Public</option>
					<option value="PROTECTED">Protected</option>
				</select>
			</div>

			<div class="form-group">
				<label>Type</label>
				<select class="form-control form-control-sm">
					<option value="">All</option>
					<option value="Sport">Sport</option>
					<option value="Reading">Reading</option>
					<option value="Business">Business</option>
				</select>
			</div>

		</div>
	</div>
</template>

<script>

</script>