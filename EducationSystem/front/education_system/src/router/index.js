import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
    routes: [
        {
            redirect: '/login',
        },
        {
            path: '/login',
            name: 'Login',
            component: () => import("@/page/Login.vue")
        },
        {
            path: '/reg',
            name: 'Reg',
            component: () => import("@/page/Reg.vue")
        },
    ],
    history: createWebHistory()
})

export default router