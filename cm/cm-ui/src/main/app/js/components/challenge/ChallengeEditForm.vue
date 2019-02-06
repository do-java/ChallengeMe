<template>
	<div>
		<div class="form-group-light row">
			<label class="col-sm-2 col-form-label">Id</label>
			<div class="col">
				<input class="form-control-plaintext" v-model="challenge.id" readonly/>
			</div>
		</div>
		<div class="form-group-light row">
			<label class="col-sm-2 col-form-label">Name</label>
			<div class="col">
				<input class="form-control" v-model="challenge.name" placeholder="Name"/>
			</div>
		</div>

		<div class="form-group-light row">
			<label class="col-sm-2 col-form-label">Picture</label>
			<div class="col">
				<button class="btn btn-primary" @click="toggleShowPictureCropUpload">Upload Picture..</button>
				<button v-if="isShowClearPictureButton" class="btn btn-primary" @click="clearPicture">Clear Picture</button>
				<vue-image-crop-upload field="img"
						   @crop-success="cropSuccess"
						   v-model="showPictureCropUpload"
						   :width="700"
						   :height="433"
						   img-format="png"
						   lang-type="en"
						   :no-circle="true"
						   :no-square="true"
						   :no-rotate="false"
				></vue-image-crop-upload>
				<div>
					<img :src="challengePictureUrl" class="img-fluid img-thumbnail w-100" alt="Challenge picture"></img>
				</div>
			</div>
		</div>

		<div class="form-group-light row">
			<label class="col-sm-2 col-form-label">Type</label>
			<div class="col">
				<input class="form-control" v-model="challenge.type" placeholder="Type"/>
			</div>
		</div>
		<div class="form-group-light row">
			<label class="col-sm-2 col-form-label">Description</label>
			<div class="col">
				<textarea class="form-control" v-model="challenge.description" rows="4" placeholder="Description"/>
			</div>
		</div>
		<div class="form-group-light row">
			<label class="col-sm-2 col-form-label">Priority</label>
			<div class="col">
				<input class="form-control" v-model="challenge.priority" placeholder="Priority"/>
			</div>
		</div>
		<div class="form-group-light row">
			<label class="col-sm-2 col-form-label">Max Members</label>
			<div class="col">
				<input class="form-control" v-model="challenge.maxMembers" placeholder="Max Members"/>
			</div>
		</div>
		<div class="form-group-light row">
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
		<div class="form-group-light row">
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
		<div class="form-group-light row">
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
		<div class="form-group-light row">
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
			<div class="col-1">
				<router-link class="btn btn-primary mr-sm-5" to="/challenge">Back</router-link>
			</div>
			<div class="col text-right">
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
	import Vue from 'vue';
	import VueImageCropUpload from 'vue-image-crop-upload';

   export default {
		props: {
			item: Object
		},
		components: {
			VueImageCropUpload
		},
		data: function() {
			return {
				challenge: this.item,
				showPictureCropUpload: false,
				pictureDataUrl: ''
			}
		},
		computed: {
			isCreateMode: function() {
				return this.challenge.id == null;
			},
			isShowClearPictureButton: function() {
				return this.challenge.pictureFilename || this.pictureDataUrl;
			},
			restApi: function() {
				return this.$resource('/rest/challenges{/id}')
			},
			challengePictureUrl: function() {
				return this.pictureDataUrl ? this.pictureDataUrl : Vue.filter('toChallengePictureUrl')(this.challenge.pictureFilename);
			}
		},
		methods: {
			createAsync: function() {
				return new Promise((resolve, reject) => {
					// Upload picture file
					this.uploadPictureAsync()
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
							console.log("Unable to upload picture data. Cause: " + error);
						});
				});
			},
			updateAsync: function() {
				return new Promise((resolve, reject) => {
					// Upload picture
					this.uploadPictureAsync()
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
			uploadPictureAsync: function() {
				return new Promise((resolve, reject) => {
					if (!this.pictureDataUrl) {
						resolve(null);
						return;
					}

					// Create form data
					var formData = new FormData();
					formData.append('data', this.pictureDataUrl);

					// Call file upload
					this.$resource('/files/upload64').save({}, formData)
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
					this.clearPictureDataUrl();
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
					this.clearPictureDataUrl();
					this.challenge = {};
                    this.$router.push({name: 'ChallengeAdd'});
				});
			},
			updateAndStay: function() {
				this.updateAsync().then(data => {
					this.clearPictureDataUrl();
					this.challenge = data;
				});
			},
			updateAndExit: function() {
				this.updateAsync().then(data => {
                    this.$router.push({name: 'Challenge'});
				});
			},
			toggleShowPictureCropUpload() {
				this.showPictureCropUpload = !this.showPictureCropUpload;
			},
			cropSuccess(pictureDataUrl, field) {
				this.pictureDataUrl = pictureDataUrl;
			},
			clearPictureDataUrl: function() {
				this.pictureDataUrl = null;
			},
			clearPicture: function() {
				this.challenge.pictureFilename = null;
				this.clearPictureDataUrl();
			},

		},

   }
</script>

<style>

</style>