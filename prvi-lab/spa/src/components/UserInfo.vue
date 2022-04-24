<template>
  <div v-if="authenticated">
    <h2>User Profile</h2>
    <pre>
        <code>{{ user }}</code>
      </pre>
  </div>
</template>

<script>
export default {
  name: "UserInfo",
  data: function () {
    return {
      user: this.$auth0.user,
    };
  },
  computed: {
    authenticated() {
      return this.$auth0.isAuthenticated.value;
    },
  },
};
</script>

<style scoped></style>
