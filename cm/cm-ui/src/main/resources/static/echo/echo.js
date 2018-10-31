
var echoApi = Vue.resource('/rest/echos{/id}', {}, {
	doEcho : {method: 'GET', url: '/rest/echos{/id}/do', params: {}},
});

Vue.component('add-echo-form', {
  props: ['items'],
  data: function () {
      return {
      	value: ''
      }
   },
  template: '<div><input v-model="value" placeholder="Echo Value"/><button @click="create">Create</button></div>' ,
  methods: {
	create: function() {
		var echoToCreate = {id: null, value: this.value};
		echoApi.save({}, echoToCreate).then(result =>
			result.json().then(data => {
				this.items.push(data);
				this.value = '';
			})
		)
	}
    },
});


Vue.component('echo-row', {
	props: ['item', 'items'],
	data: function () {
	  return {
		value: this.item.value
	  }
   },
	template: '<div><span>{{item.id}}: {{item.value}}</span> <input v-model="value"/><button @click="update">Update</button><button @click="del">X</button><button @click="doEcho">Do</button></div>',
	methods: {
		update: function() {
			var echoToUpdate = {id: this.item.id, value: this.value};
			echoApi.update({id: this.item.id}, echoToUpdate).then(result =>
				result.json().then(data => {
					this.item.value = data.value;
					this.value = data.value;
				})
			)
		},
		del: function() {
			echoApi.delete({id: this.item.id}).then(result => {
				this.items.splice($.inArray(this.item, this.items), 1)
			})
		},
		doEcho: function() {
			echoApi.doEcho({id: this.item.id, n: 8}).then(result =>
				result.text().then(data => {
					console.log(data)
				})
			)
		},

	}
});


Vue.component('echos-list', {
	props: {
	  items: Array
	},
	data: function () {
	  return {
	  }
	},
	template: '<div><echo-row v-for="item in items" :items="items" :item="item" :key="item.id"/></div>',
	created: function () {
		echoApi.get().then(result =>
			result.json().then(data =>
				data.forEach(item => this.items.push(item))
			)
		)
	},
	methods: {

	},
});


var app = new Vue({
  el: '#app',
  data: {
  	echos : []
  }
});