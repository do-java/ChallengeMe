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
			<label class="col-sm-2 col-form-label">Picture</label>
			<div class="col">
				File: <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
				<button v-if="isShowClearPictureButton" class="btn btn-primary" @click="clearPicture">Clear Picture</button>
				<button v-if="!isCreateMode" class="btn btn-primary" @click="updateAndStay">Update</button>
				<div>
					<img :src="challenge.pictureFilename | toChallengePictureUrl" class="img-fluid img-thumbnail w-100" alt="Challenge picture"></img>
				</div>
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
				<select class="form-control" v-model="challenge.access">
                  <option disabled value="">Select Access Type</option>
                  <option>PRIVATE</option>
                  <option>PUBLIC</option>
                  <option>PROTECTED</option>
                </select>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Status</label>
			<div class="col">
				<select class="form-control" v-model="challenge.status">
				  <option disabled value="">Select Status</option>
				  <option>ACTIVE</option>
				  <option>BLOCKED</option>
				  <option>FINISHED</option>
				  <option>ARCHIVATED</option>
				</select>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Start Date</label>
			<div class="col">
				<datetime
					v-model="challenge.startDate"
					type="datetime"
					placeholder="Select Date"
					input-class="form-control col-sm-4"
					format="yyyy-MM-dd HH:mm"
					auto
					>
				</datetime>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">End Date</label>
			<div class="col">
				<datetime
					v-model="challenge.endDate"
					type="datetime"
					placeholder="Select Date"
					input-class="form-control col-sm-4"
					format="yyyy-MM-dd HH:mm"
					auto
					>
				</datetime>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-2">
			</div>
			<div class="col">
				<router-link class="btn btn-primary mr-sm-5" to="/challenge">Back</router-link>
				<button v-if="isCreateMode" class="btn btn-primary" @click="createAndStay">Create</button>
				<button v-if="isCreateMode" class="btn btn-primary" @click="createAndExit">Create And Exit</button>
				<button v-if="isCreateMode" class="btn btn-primary" @click="createAndAdd">Create And Add</button>
				<button v-if="!isCreateMode" class="btn btn-primary" @click="updateAndStay">Update</button>
				<button v-if="!isCreateMode" class="btn btn-primary" @click="updateAndExit">Update And Exit</button>
				<button v-if="!isCreateMode" class="btn btn-danger" @click="deleteAndExit">Delete</button>
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
				challenge: this.item,
				file: null
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
			isShowClearPictureButton: function() {
				return this.challenge.pictureFilename || this.file;
			},
			restApi: function() {
				return this.$resource('/rest/challenges{/id}')
			}
		},
		methods: {
			createAsync: function() {
				return new Promise((resolve, reject) => {
					// Upload picture file
					this.uploadPictureFileAsync(this.file)
						.then(resultFilename => {
							if (resultFilename) {
								this.challenge.pictureFilename = resultFilename;
							}

							// Save challenge
							this.restApi.save({}, this.challenge)
								.then(result => {
									result.json().then(data => resolve(data));
								},	error => {
									reject(error);
								});
						}, error => {
							console.log("Unable to upload picture file: " + this.file + " error: " + error);
						});
				});

				return new Promise((resolve, reject) => {
					this.restApi.save({}, this.challenge)
						.then(result => {
							result.json().then(data => resolve(data));
						},	error => {
							reject(error);
						});
				});
			},
			updateAsync: function() {
				return new Promise((resolve, reject) => {
					// Upload picture file
					this.uploadPictureFileAsync(this.file)
						.then(resultFilename => {
							if (resultFilename) {
								this.challenge.pictureFilename = resultFilename;
							}

							// Update challenge
							this.restApi.update({id: this.challenge.id}, this.challenge)
								.then(result => {
									result.json().then(data => resolve(data));
								},	error => {
									reject(error);
								});
						}, error => {
							console.log("Unable to upload picture file: " + this.file + " error: " + error);
						});
				});
			},
			uploadPictureFileAsync: function(file) {
				return new Promise((resolve, reject) => {
					if (!file) {
						resolve(null);
						return;
					}

					// Create form data
					var formData = new FormData();
					formData.append('file', file);

					// Call file upload
					this.$resource('/files/upload').save({}, formData)
							.then(result =>	resolve(result.bodyText),
								error => reject(error));
				});
			},
			deleteAndExit: function() {
				this.restApi.delete({id: this.challenge.id}, this.challenge)
					.then(result => {
						this.$router.push({name: 'Challenge'});
					});
			},
			createAndStay: function() {
				this.createAsync().then(data => {
					this.challenge = data;
                    this.$router.push({ name: 'ChallengeEdit', params: {id: this.challenge.id}});
				});
			},
			createAndExit: function() {
				this.createAsync().then(data => {
					this.$router.push({name: 'Challenge'});
				});
			},
			createAndAdd: function() {
				this.createAsync().then(data => {
					this.challenge = {};
                    this.$router.push({name: 'ChallengeAdd'});
				});
			},
			updateAndStay: function() {
				this.updateAsync().then(data => {
					this.challenge = data;
				});
			},
			updateAndExit: function() {
				this.updateAsync().then(data => {
					this.challenge = data;
                    this.$router.push({name: 'Challenge'});
				});
			},
			handleFileUpload: function() {
				this.file = this.$refs.file.files[0];
			},
			clearPicture: function() {
				this.challenge.pictureFilename = null;
				if (this.$refs.file) {
					this.$refs.file.value = '';
				}
				this.file = null;
			},
		},

   }
</script>

<style>

</style>