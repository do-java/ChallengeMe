<template>
    <div class="container">

        <!-- Button trigger modal -->
        <div class="row justify-content-end">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#loginModal">
                <span class="login-tab"><i class="far fa-user"></i> Log In</span>
            </button>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Log In</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <form>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="email" class="form-control" id="email1" placeholder="E-mail">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" id="password1" placeholder="Password">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <router-link to="/"><button type="button" class="btn btn-primary" data-dismiss="modal">Submit</button></router-link>
                        </div>
                    </form>

                </div>
            </div>
        </div>

        <div class=" login-content">
            <div class="row justify-content-center text-center ">
                <router-link class="navbar-brand cm-logo" to="/">
                    <span class="logo-in-login-area">Challenge<span
                            class="cm-logo-me">Me</span>
                    </span>
                </router-link>
                <div class="text-login-content">
                    <h2>Application to create, manage, and share challenges</h2>
                    <h5><a href="#">Create an account</a></h5>
                </div>

            </div>

            <div class="row justify-content-center">

                <div class="col-md-4">

                    <form>
                        <div class="form-group">
                            <input type="text" class="form-control" id="name" placeholder="First and Last Name">
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control" id="email" placeholder="E-mail">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="password" placeholder="Password">
                        </div>

                        <p>By registering, you are agree that you've read and accept our <a href="#">User Agreement</a>
                        </p>

                        <button type="submit" class="btn btn-primary mb-3 cm-highlight"><span>Create Account</span>
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
        </div>
    </div>
</template>

<script>

</script>

<style>

    .login-tab {
        color: #000;
    }

    .login-content {
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