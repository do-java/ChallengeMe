<template>
	<div>
		<div v-for="item in items" class="alert alert-primary alert-dismissible" role="alert">
          <span>{{item}}</span>
          <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
	</div>
</template>

<script>
	export default {
		props: {
		  addEventHandlerFunction: Function
		},
		data() {
			return {
				items: []
			}
		},
		created() {
			console.log('created');
			this.addEventHandlerFunction(data => {
				this.items.push('Event: ' + JSON.stringify(data));
				setTimeout(() => {
					this.items.shift();
				}, 3000);
			})
		}
	}
</script>
