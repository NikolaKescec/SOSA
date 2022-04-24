<template>
  <h1>Records</h1>
  <span v-if="!authenticated">Please login to manage records</span>

  <div v-else>
    <button @click="retrieveRecords">Retrieve students</button>
    <div>
      <code>
        {{ records }}
      </code>
    </div>
    <form @submit.prevent="emitNewRecord">
      <input v-model="recordName" placeholder="Record name" required />
      <input
        v-model="recordDate"
        placeholder="Record date"
        type="date"
        required
      />
      <input v-model="recordOrigin" placeholder="Record origin" required />
      <input
        v-model="recordValue"
        placeholder="Record value"
        type="number"
        required
      />
      <button>Create new record</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "RecordsDisplay",
  props: ["records"],
  emits: ["create-record", "retrieve-records"],
  data() {
    return {
      recordName: "",
      recordDate: null,
      recordOrigin: "",
      recordValue: null,
    };
  },
  computed: {
    authenticated() {
      return this.$auth0.isAuthenticated.value;
    },
  },
  methods: {
    emitNewRecord() {
      this.$emit("createRecord", {
        entity: "records",
        object: {
          recordName: this.recordName,
          recordDate: this.recordDate,
          recordOrigin: this.recordOrigin,
          recordValue: this.recordValue,
        },
      });
    },
    retrieveRecords() {
      this.$emit("retrieveRecords", {
        entity: "records",
      });
    },
  },
};
</script>

<style scoped></style>
