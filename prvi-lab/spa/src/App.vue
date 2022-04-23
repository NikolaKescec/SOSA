<template>
  <div>
    <h2>User Profile</h2>
    <logout-button v-if="authenticated">Logout</logout-button>
    <login-button v-else>Login</login-button>
    <user-info v-if="authenticated"></user-info>
    <h1>Students</h1>
    <span v-if="!authenticated">Please login to retrieve students</span>
    <button @click="() => retrieve('students')" v-else>
      Retrieve students
    </button>
    <div>
      <code>
        {{ students }}
      </code>
    </div>

    <h1>Records</h1>
    <span v-if="!authenticated">Please login to retrieve records</span>
    <button @click="() => retrieve('records')" v-else>Retrieve records</button>
    <div>
      <code>
        {{ records }}
      </code>
    </div>
  </div>
</template>

<script>
import LoginButton from "@/components/LoginButton";
import LogoutButton from "@/components/LogoutButton";
import UserInfo from "@/components/UserInfo";

export default {
  components: {
    LoginButton,
    LogoutButton,
    UserInfo,
  },
  data: function () {
    return {
      students: "",
      records: "",
    };
  },

  computed: {
    authenticated() {
      return this.$auth0.isAuthenticated.value === true;
    },
  },
  methods: {
    async retrieve(object) {
      console.log(object);
      const token = await this.$auth0.getAccessTokenSilently();
      try {
        const response = await fetch(`http://localhost:8080/${object}`, {
          headers: {
            Authorization: "Bearer " + token,
          },
          mode: "cors",
        });

        console.log(response.status);

        if (response.status === 401) {
          this[object] = `Please login to see the ${object}!`;
        } else if (response.status === 403) {
          this[object] = `You do not have the rights to see the ${object}!`;
        } else if (response.ok) {
          this[object] = await response.json();
        }
      } catch (e) {
        this[object] = e.message;
      }
    },
  },
};
</script>
