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
            redirect: '/student/info',
            children: [
                {
                    // 个人信息
                    path: 'info',
                    name: 'SInfo',
                    component: () => import("@/page/student/SInfo.vue")
                },
                {
                    // 成绩
                    path: 'score',
                    name: 'SScore',
                    component: () => import("@/page/student/SScore.vue")
                },
                {
                    // 荣誉
                    path: 'honor',
                    name: 'SHonor',
                    component: () =>  import("@/page/student/SHonor.vue")
                },
                {
                    // 体测
                    path: 'physical',
                    name: 'SPhysical',
                    component: () =>  import("@/page/student/SPhysical.vue")
                },
                {
                    // 课程
                    path: 'course',
                    name: 'SCourse',
                    component: () =>  import("@/page/student/SCourse.vue")
                }
            ]
        },

        {
            //教师
            path: '/teacher',
            redirect: '/teacher/info',
            children: [
                {
                    // 个人信息
                    path: 'info',
                    name: 'TInfo',
                    component: () => import("@/page/teacher/TInfo.vue")
                },
                {
                    // 学生信息
                    path: 'stu_info',
                    name: 'TStudentInfo',
                    component: () => import("@/page/teacher/TStudentInfo.vue")
                },
                {
                    // 学生成绩
                    path: 'score',
                    name: 'TScore',
                    component: () => import("@/page/teacher/TScore.vue")
                },
                {
                    // 学生荣誉
                    path: 'honor',
                    name: 'THonor',
                    component: () => import("@/page/teacher/THonor.vue")
                },
                {
                    // 学生课程
                    path: 'course',
                    name: 'TCourse',
                    component: () => import("@/page/teacher/TCourse.vue")
                },
                {
                    // 学生体测成绩
                    path: 'physical',
                    name: 'TPhysical',
                    component: () => import("@/page/teacher/TPhysical.vue")
                },
            ]
        }

    ],
    history: createWebHistory()
})

// router.beforeEach((to, from, next) => {
//     if (to.fullPath !== from.fullPath) {
//       // 强制刷新页面
//       window.location.reload();
//     }
//     next();
//   })
export default router