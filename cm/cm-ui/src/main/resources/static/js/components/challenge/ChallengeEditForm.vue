<template>
	<div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Id</label>
			<div class="col">
				<input class="form-control-plaintext" v-model="challenge.id" readonly/>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Name</label>
			<div class="col">
				<input class="form-control" v-model="challenge.name" placeholder="Name"/>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Type</label>
			<div class="col">
				<input class="form-control" v-model="challenge.type" placeholder="Type"/>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Description</label>
			<div class="col">
				<textarea class="form-control" v-model="challenge.description" rows="4" placeholder="Description"/>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Priority</label>
			<div class="col">
				<input class="form-control" v-model="challenge.priority" placeholder="Priority"/>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Max Members</label>
			<div class="col">
				<input class="form-control" v-model="challenge.maxMembers" placeholder="Max Members"/>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Access</label>
			<div class="col">
				<input class="form-control" v-model="challenge.access" placeholder="Access">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Status</label>
			<div class="col">
				<input class="form-control" v-model="challenge.status" placeholder="Status"/>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Start Date</label>
			<div class="col">
				<input class="form-control" v-model="challenge.startDate" placeholder="Start Date"/>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">End Date</label>
			<div class="col">
				<input class="form-control" v-model="challenge.endDate" placeholder="End Date"/>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-2">
			</div>
			<div class="col">
				<router-link class="btn btn-primary mr-sm-5" to="/challenge">Back</router-link>
				<button v-if="isCreateMode" class="btn btn-primary" @click="create">Create</button>
				<button v-if="isCreateMode" class="btn btn-primary" @click="createAndExit">Create And Exit</button>
				<button v-if="isCreateMode" class="btn btn-primary" @click="createAndAdd">Create And Add</button>
				<button v-if="!isCreateMode" class="btn btn-primary" @click="update">Update</button>
				<button v-if="!isCreateMode" class="btn btn-primary" @click="updateAndExit">Update And Exit</button>
			</div>
		</div>
	</div>
</template>

<script>
   export default {
		props: {
			item: Object
		},
		data: function() {
			return {
				challenge: this.item
			}
		},
		private: function() {
			return {

			}
		},
		computed: {
			isCreateMode: function() {
				return this.challenge.id == null;
			},
			restApi: function() {
				return this.$resource('/rest/challenges{/id}')
			}
		},
		methods: {
			create: function() {
				this.restApi.save({}, this.challenge).then(result =>
					result.json().then(data => {
						this.challenge = data;
						this.$router.push({ name: 'ChallengeEdit', params: {id: this.challenge.id}});
					})
				)
			},
			createAndExit: function() {
				this.restApi.save({}, this.challenge).then(result =>
					result.json().then(data => {
						this.challenge =  data;
						this.$router.push({name: 'Challenge'});
					})
				)
			},
			createAndAdd: function() {
				this.restApi.save({}, this.challenge).then(result =>
					result.json().then(data => {
						this.challenge = {};
						this.$router.push({name: 'ChallengeAdd'});
					})
				)
			},
			update: function() {
				this.restApi.update({id: this.challenge.id}, this.challenge).then(result =>
					result.json().then(data => {
						this.challenge = data
					})
				)
			},
			updateAndExit: function() {
				this.restApi.update({id: this.challenge.id}, this.challenge).then(result =>
					result.json().then(data => {
						this.challenge = data;
						this.$router.push({name: 'Challenge'});
					})
				)
			},
		},

   }
</script>

<style>

</style>