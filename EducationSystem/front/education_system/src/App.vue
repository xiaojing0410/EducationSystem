<script setup>
import {useRoute} from "vue-router";
import {computed} from "vue";
import Menu from "@/page/student/Menu.vue";

// 不在登录页面和注册页面才显示菜单栏
// TODO: 根据获取的用户信息，决定是 教师 还是 学生 的菜单
const route = useRoute();
const showMenu = computed(() => {
  return route.name !== 'Login' && route.name !== 'Reg'
})
</script>

<template>

  <Menu v-if="showMenu"></Menu>
  <router-view></router-view>
</template>

<style scoped>

</style>
