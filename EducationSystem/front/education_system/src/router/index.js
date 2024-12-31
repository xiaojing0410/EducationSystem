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
                {
                    // 成绩
                    path: 'score',
                    name: 'Score',
                    component: () => import("@/page/student/Score.vue")
                },
                {
                    // 荣誉
                    path: 'honor',
                    name: 'Honor',
                    component: () =>  import("@/page/student/Honor.vue")
                },
                {
                    // 体测
                    path: 'physical',
                    name: 'Physical',
                    component: () =>  import("@/page/student/Physical.vue")
                }
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