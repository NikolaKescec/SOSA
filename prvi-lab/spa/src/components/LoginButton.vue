<template>
  <div>
    <button @click="login">Log in</button>
  </div>
</template>

<script>
export default {
  name: "LoginButton",
  methods: {
    login() {
      this.$auth0.loginWithRedirect();
    },
  },
};
</script>
