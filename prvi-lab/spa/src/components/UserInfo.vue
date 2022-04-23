<template>
  <div>
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
};
</script>

<style scoped></style>
