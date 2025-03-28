import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
    routes: [
        {
            path: '/',
            redirect: '/userinfo'
        },
        {
            name: 'Login',
            path: '/login',
            component: () => import('../page/Login.vue')
        },
        {
            name: 'Reg',
            path: '/reg',
            component: () => import('../page/Reg.vue')
        },
        {
            name: 'UserInfo',
            path: '/userinfo',
            component: () => import('../page/UserInfo.vue')
        },
        {
            name: 'Class',
            path: '/class',
            component: () => import('../page/Class.vue')
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
        },
        {
            name: 'Evaluation',
            path: '/evaluation',
            component: () => import('../page/Evaluation.vue')
        },
        {
            name: 'Course',
            path: '/course',
            component: () => import('../page/Course.vue')
        },
        {
            name: 'CoursePlan',
            path: '/course_plan',
            component: () => import('../page/CoursePlan.vue')
        },
        {
            name: 'Attendance',
            path: '/attendance',
            component: () => import('../page/Attendance.vue')
        },
        {
            name: 'SelfInfo',
            path: '/selfInfo',
            component: () => import('../page/SelfInfo.vue')
        },
        {
            name: 'Grades',
            path: '/grades',
            component: () => import('../page/Grades.vue')
        },
        {
            name: 'StudyWarn',
            path: '/study_warn',
            component: () => import('../page/StudyWarn.vue')
        },
        {
            name: 'Relearn',
            path: '/relearn',
            component: () => import('../page/Relearn.vue')
        },
        {
            name: 'Recommend',
            path: '/recommend',
            component: () => import('../page/Recommend.vue')
        },
    ],
    history: createWebHistory()
})

export default router