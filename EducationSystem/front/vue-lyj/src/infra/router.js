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
            component: () => import('../page/UserInfo.vue')
        },
        {
            name: 'Honor',
            path: '/honor',
            component: () => import('../page/Honor.vue')
        },
        {
            name: 'Physical',
            path: '/physical',
            component: () => import('../page/Physical.vue')
        }
    ],
    history: createWebHistory()
})

export default router