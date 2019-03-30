<template>
    <div class="row justify-content-center">
        <div class="col-md-4">
            <form>
                <div class="form-group">
                    <input type="email" v-model="email" class="form-control" id="email" placeholder="E-mail">
                </div>

                <div class="form-group">
                    <input type="password" v-model="password" class="form-control" id="password" placeholder="Password">
                </div>

                <button v-if="registered" class="btn btn-primary mb-3 cm-highlight" @click="login">
                    <span>Log In</span>
                </button>

                <button v-if="!registered" class="btn btn-primary mb-3 cm-highlight" @click="register">
                    <span>Register</span>
                </button>
            </form>
        </div>

        <div class="col-md-1">
            <div>or</div>
        </div>

        <div class="col-md-4">
            <button type="button" class="btn btn-primary mb-3">Continue with Facebook</button>
            <button type="button" class="btn btn-primary">Continue with Google</button>
        </div>
    </div>
</template>

<script>
    import Vue from 'vue';

    export default {
        data() {
            return {
                email: '',
                password: '',
                registered: true
            }
        },
        computed: {
            restApiLogin() {
                return this.$resource('/rest/public/user/login');
            },
            restApiRegister() {
                return this.$resource('/rest/public/user/register');
            }
        },
        methods: {
            login() {
                this.restApiLogin.save({}, {email: this.email, password: this.password})
                    .then(result => {
                        localStorage.setItem('userToken', result.body.token);
                        this.$store.commit('userLogin', {user: result.body.user});
                        this.$router.push('/');
                    }, error => {
                        if (error.status == 404) {
                            this.registered = false;
                        }
                        alert(error.body.message);
                        this.email = '';
                        this.password = '';
                    });
            },
            register() {
                this.restApiRegister.save({}, {email: this.email, password: this.password})
                    .then(result => {
                        localStorage.setItem('userToken', result.body.token);
                        this.$store.commit('userLogin', {user: result.body.user});
                        this.$router.push('/');
                    }, error => {
                        this.registered = false;
                        alert(error.body.message);
                        this.email = '';
                        this.password = '';
                    });
            }
        }
    }
</script>

<style>
    form > p > a {
        color: #000;
        font-weight: bold;
    }
</style>