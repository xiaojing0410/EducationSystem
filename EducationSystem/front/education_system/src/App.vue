<script setup>
import {useRoute} from "vue-router";
import {computed} from "vue";
import SMenu from "@/page/student/SMenu.vue";
import TMenu from "@/page/teacher/TMenu.vue";

// 不在登录页面和注册页面才显示菜单栏
// TODO: 根据获取的用户信息，决定是 教师 还是 学生 的菜单
const route = useRoute();
const showMenu = computed(() => {
  return route.name !== 'Login' && route.name !== 'Reg'
})

// 判断当前路由是否包含 student，展示 SMenu
const showSMenu = computed(() => {
  return showMenu.value && route.path.includes('student');
});

// 判断当前路由是否包含 teacher，展示 TMenu
const showTMenu = computed(() => {
  return showMenu.value && route.path.includes('teacher');
});
</script>

<template>

  <SMenu v-if="showSMenu"></SMenu>
  <TMenu v-if="showTMenu"></TMenu>

  <router-view></router-view>
</template>

<style scoped>

</style>
