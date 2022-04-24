<template>
  <div>
    <logout-button v-if="authenticated">Logout</logout-button>
    <login-button v-else>Login</login-button>
    <user-info v-if="authenticated"></user-info>
    <students-display
      @create-student="create"
      @retrieve-students="retrieve"
      :students="students"
    ></students-display>
    <records-display
      @create-record="create"
      @retrieve-records="retrieve"
      :records="records"
    ></records-display>
  </div>
</template>

<script>
import LoginButton from "@/components/LoginButton";
import LogoutButton from "@/components/LogoutButton";
import UserInfo from "@/components/UserInfo";
import StudentsDisplay from "@/components/StudentsDisplay";
import RecordsDisplay from "@/components/RecordsDisplay";

export default {
  components: {
    LoginButton,
    LogoutButton,
    UserInfo,
    StudentsDisplay,
    RecordsDisplay,
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
    async retrieve({ entity }) {
      console.log(entity);
      const token = await this.$auth0.getAccessTokenSilently();
      try {
        const response = await fetch(`http://localhost:8080/${entity}`, {
          headers: {
            Authorization: "Bearer " + token,
          },
          mode: "cors",
        });

        console.log(response.status);

        if (response.status === 401) {
          this[entity] = `Please login to see the ${entity}!`;
        } else if (response.status === 403) {
          this[entity] = `You do not have the rights to see the ${entity}!`;
        } else if (response.ok) {
          this[entity] = await response.json();
        }
      } catch (e) {
        this[entity] = e.message;
      }
    },
    async create({ entity, object }) {
      console.log(entity, object);
      const token = await this.$auth0.getAccessTokenSilently();
      try {
        const response = await fetch(`http://localhost:8080/${entity}`, {
          method: "POST",
          headers: {
            Authorization: "Bearer " + token,
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          mode: "cors",
          body: JSON.stringify(object),
        });

        console.log(response.status);

        if (response.status === 401) {
          this[entity] = `Please login to create new ${entity}!`;
        } else if (response.status === 403) {
          this[entity] = `You do not have the rights to create new ${entity}!`;
        } else if (response.status === 204) {
          await this.retrieve(entity);
        }
      } catch (e) {
        this[entity] = e.message;
      }
    },
  },
};
</script>
