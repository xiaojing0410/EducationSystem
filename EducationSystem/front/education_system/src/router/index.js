import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
    routes: [
        {
            redirect: '/login',
        },

        {
            // 登录
            path: '/login',
            name: 'Login',
            component: () => import("@/page/Login.vue")
        },
        {
            // 注册
            path: '/reg',
            name: 'Reg',
            component: () => import("@/page/Reg.vue")
        },

        {
            //学生
            path: '/student',
            children: [
                {
                    // 个人信息
                    path: 'info',
                    name: 'StudentInfo',
                    component: () => import("@/page/student/StudentInfo.vue")
                },
            ]
        },

        {
            //教师
            path: '/teacher',
            children: [
                {
                    // 个人信息
                    path: 'info',
                    name: 'TeacherInfo',
                    component: () => import("@/page/teacher/TeacherInfo.vue")
                },
            ]
        }

    ],
    history: createWebHistory()
})

export default router