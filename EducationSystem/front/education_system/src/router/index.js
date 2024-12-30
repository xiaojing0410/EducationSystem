import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
    routes: [
        {
            path: '/',
            name: 'Index',
            component: () => import("@/views/Index.vue")
        },
    ],
    history: createWebHistory()
})

export default router