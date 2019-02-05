<template>
	<div class="row">
		<div class="col">
			<div class="form-group-light">
				<label>OrderBy</label>
				<select v-model="orderValue" @change="orderUpdated" class="form-control form-control-sm">
					<option value='{"by":"id","direction":"asc"}'>Id Asc</option>
					<option value='{"by":"id","direction":"desc"}'>Id Desc</option>
					<option value='{"by":"name","direction":"asc"}'>Name Asc</option>
					<option value='{"by":"name","direction":"desc"}'>Name Desc</option>
					<option value='{"by":"startDate","direction":"asc"}'>Start Date Asc</option>
					<option value='{"by":"startDate","direction":"desc"}'>Start Date Desc</option>
					<option value='{"by":"endDate","direction":"asc"}'>End Date Asc</option>
					<option value='{"by":"endDate","direction":"desc"}'>End Date Desc</option>
				</select>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
		  orderProp: Object
		},
		data: function() {
			return {
				orderValue: JSON.stringify(this.orderProp)
			}
		},
		methods: {
			orderUpdated: function() {
				this.$emit('order-updated', JSON.parse(this.orderValue));
			}
		}

	}
</script>