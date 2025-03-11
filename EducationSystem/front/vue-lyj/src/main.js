import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from "./infra/router.js";
import 'element-plus/dist/index.css';
import { createPinia } from 'pinia';

const pinia = createPinia()

const app = createApp(App)
app.use(router)  //一定要在挂在前 use
app.use(pinia)
app.mount('#app')
