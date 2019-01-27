<template>
	<div>
		<div class="form-group-light row">
			<label for="inpEchoId" class="col-sm-2 col-form-label">Id</label>
			<div class="col">
				<input class="form-control-plaintext" id="inpEchoId" v-model="echo.id" readonly/>
			</div>
		</div>
		<div class="form-group-light row">
			<label for="inpEchoValue" class="col-sm-2 col-form-label">Value</label>
			<div class="col">
				<input class="form-control" id="inpEchoValue" v-model="echo.value" placeholder="Echo Value"/>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-2">
			</div>
			<div class="col">
				<router-link class="btn-cm mr-sm-5" to="/echo">Back</router-link>
				<button v-if="isCreateMode" class="btn btn-primary cm-btn" @click="create">Create</button>
				<button v-if="isCreateMode" class="btn btn-primary cm-btn" @click="createAndExit">Create And Exit</button>
				<button v-if="isCreateMode" class="btn btn-primary cm-btn" @click="createAndAdd">Create And Add</button>
				<button v-if="!isCreateMode" class="btn btn-primary cm-btn" @click="update">Update</button>
				<button v-if="!isCreateMode" class="btn btn-primary cm-btn" @click="updateAndExit">Update And Exit</button>
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
				echo: this.item
			}
		},
		computed: {
			isCreateMode: function() {
				return this.echo.id == null;
			},
			restApi: function() {
				return this.$resource('/rest/echos{/id}')
			}
		},
		methods: {
			create: function() {
				this.restApi.save({}, this.echo).then(result =>
					result.json().then(data => {
						this.echo = data;
						this.$router.push({ name: 'EchoEdit', params: {id: this.echo.id}});
					})
				)
			},
			createAndExit: function() {
				this.restApi.save({}, this.echo).then(result =>
					result.json().then(data => {
						this.echo =  data;
						this.$router.push({name: 'Echo'});
					})
				)
			},
			createAndAdd: function() {
				this.restApi.save({}, this.echo).then(result =>
					result.json().then(data => {
						this.echo = {};
						this.$router.push({name: 'EchoAdd'});
					})
				)
			},
			update: function() {
				this.restApi.update({id: this.echo.id}, this.echo).then(result =>
					result.json().then(data => {
						this.echo = data
					})
				)
			},
			updateAndExit: function() {
				this.restApi.update({id: this.echo.id}, this.echo).then(result =>
					result.json().then(data => {
						this.echo = data;
						this.$router.push({name: 'Echo'});
					})
				)
			},
		},

   }
</script>

<style>

</style>