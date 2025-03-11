import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
    routes: [
        {
            path: '/',
            redirect: '/userinfo'
        },
        {
            name: 'UserInfo',
            path: '/userinfo',
            component: () => import('../page/userinfo/UserInfo.vue')
        }
    ],
    history: createWebHistory()
})

export default router