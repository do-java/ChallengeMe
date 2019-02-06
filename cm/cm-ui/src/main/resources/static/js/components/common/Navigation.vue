<template>
	<nav class="navbar navbar-expand-md navbar-light bg-primary mb-4 shadow">
        <router-link class="navbar-brand cm-logo" to="/">Challenge<span class="cm-logo-me">Me</span></router-link>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <router-link class="navbar-item nav-link" active-class="active cm-highlight" to="/echo"><span class="p-2">Echo</span></router-link>
                </li>
                <li class="nav-item">
                    <div><router-link class="navbar-item nav-link" active-class="active cm-highlight" to="/challenge"><span class="p-2">Challenge</span></router-link></div>
                </li>
                <li class="nav-item">
                    <div><router-link class="navbar-item nav-link" active-class="active cm-highlight" to="/login"><span class="p-2">Logout</span></router-link></div>
                </li>
            </ul>
        </div>
    </nav>
</template>

<script>

</script>

<style>
</style>