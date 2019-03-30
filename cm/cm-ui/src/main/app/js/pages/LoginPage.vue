<template>
    <div class="container">

        <!-- Button trigger modal -->
        <!--
        <div class="row justify-content-end">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#loginModal">
                <span class="login-tab"><i class="far fa-user"></i> Log In</span>
            </button>
        </div>
        -->
        <!-- Modal -->
        <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Log In</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <login-form/>
                    </div>
                </div>
            </div>
        </div>

        <div class="login-content">
            <div class="row justify-content-center text-center ">
                <router-link class="navbar-brand cm-logo" to="/">
                    <span class="logo-in-login-area">Challenge<span class="cm-logo-me">Me</span>
                    </span>
                </router-link>
                <div class="text-login-content">
                    <h2>Application to create, manage, and share challenges</h2>
                    <!--<h5><a href="#">Create an account</a></h5>-->
                </div>

            </div>

            <login-form/>
        </div>
    </div>
</template>

<script>
    import LoginForm from 'components/user/LoginForm.vue'

    export default {
        components: {
            LoginForm
        }
   }
</script>

<style>
    .login-tab {
        color: #000;
    }

    .login-content, .modal-content {
        background-color: #b3cfdb;
    }

    .logo-in-login-area {
        font-size: 1.5em;
        color: black;
    }

    .text-login-content {
        margin: 30px 0;
    }

    .text-login-content a {
        color: #000;
    }

    form > p > a {
        color: #000;
        font-weight: bold;
    }
</style>