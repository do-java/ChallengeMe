<template>
    <div class="container">

        <router-link to="/"><span><i class="far fa-user"></i></span> Log In</router-link>
        <hr>

        <div class="border text-center">
            <router-link class="navbar-brand cm-logo" to="/">Challenge<span class="cm-logo-me">Me</span></router-link>
            <h2>Application to create, manage, and share challenges</h2>
            <h4>Create an account</h4>
        </div>

        <div class="row justify-content-center">

            <div class="col-md-4 border">
                <input type="text" placeholder="First and Last Name"/>
                <input type="text" placeholder="E-mail"/>
                <input type="text" placeholder="Password show"/>
                <p>By registering, you are agree that you've read and accept our <a href="#">User Agreement</a></p>
                <button>Create Account</button>
            </div>

            <div class="col-md-1 border">
                <div>or</div>
            </div>

            <div class="col-md-4 border">
                <div class="border">Continue with Facebook</div>
                <div class="border">Continue with Google</div>
            </div>

        </div>
    </div>
</template>

<script>

</script>

<style>

</style>