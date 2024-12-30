import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
    routes: [
        {
            redirect: '/login',
        },
        // 权限相关
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

        //学生信息
        {
            path: '/student',
            children: [
                {
                    path: 'my_info',
                    name: 'MyInfo',
                    component: () => import("@/page/MyInfo.vue")
                },
            ]
        },

    ],
    history: createWebHistory()
})

export default router