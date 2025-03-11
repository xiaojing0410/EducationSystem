<script setup>
import { useRoute } from 'vue-router';
import Nav from "./page/Nav.vue";

const route = useRoute(); // 获取当前路由信息
</script>

<template>
  <!-- 只有当路由不是 /login 和 /reg 时才显示 Nav 组件 -->
  <Nav />
  <router-view v-if="route.path === '/login' || route.path === '/reg'" />
</template>

<style scoped>
</style>
