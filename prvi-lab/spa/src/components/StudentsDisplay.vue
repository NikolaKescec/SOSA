<template>
  <h1>Students</h1>
  <span v-if="!authenticated">Please login to manage students</span>
  <div v-else>
    <button @click="retrieveStudents">Retrieve students</button>
    <div>
      <code>
        {{ students }}
      </code>
    </div>
    <form @submit.prevent="emitNewStudent">
      <input v-model="studentName" placeholder="Student name" required />
      <input
        v-model="password"
        placeholder="Student password"
        type="password"
        required
      />
      <button>Create new student</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "StudentsDisplay",
  props: ["students"],
  emits: ["create-student", "retrieve-students"],
  data() {
    return {
      studentName: "",
      password: "",
    };
  },
  computed: {
    authenticated() {
      return this.$auth0.isAuthenticated.value;
    },
  },
  methods: {
    emitNewStudent() {
      this.$emit("createStudent", {
        entity: "students",
        object: { userName: this.studentName, passWord: this.password },
      });
    },
    retrieveStudents() {
      this.$emit("retrieveStudents", {
        entity: "students",
      });
    },
  },
};
</script>

<style scoped></style>
