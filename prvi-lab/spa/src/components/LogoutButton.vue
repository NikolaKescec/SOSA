<template>
  <div>
    <button @click="logout">Log out</button>
  </div>
</template>

<script>
export default {
  name: "LogoutButton",
  methods: {
    logout() {
      this.$auth0.logout({ returnTo: window.location.origin });
    },
  },
};
</script>
