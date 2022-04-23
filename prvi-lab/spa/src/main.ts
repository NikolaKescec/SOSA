import { createApp } from "vue";
import { createAuth0 } from "@auth0/auth0-vue";
import App from "./App.vue";

createApp(App)
  .use(
    createAuth0({
      domain: "nk-nrppw.eu.auth0.com",
      client_id: "M4FgnfntpnBl61mHCHQxIP8OnrmrDj97",
      redirect_uri: window.location.origin,
      audience: "http://localhost:8080",
    })
  )
  .mount("#app");
