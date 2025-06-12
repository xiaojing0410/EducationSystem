<script setup>
import { useRoute } from 'vue-router';
import Nav from "./page/Nav.vue";

const route = useRoute(); // 获取当前路由信息
</script>

<template>
  <!-- 只有当路由不是 /login 和 /reg 时才显示 Nav 组件 -->
  <Nav v-if="!(route.path === '/login' || route.path === '/reg')" />
  <router-view v-if="route.path === '/login' || route.path === '/reg'" />

</template>
<style scoped>
/* 设置整体页面高度 */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}
html, body {
    height: 100%;
    width: 100%;
    /* max-height: 100vh; */
    /* // background-image: url(@/assets/common/indexPic.jpeg); */
    overflow: hidden;
    /* // overflow-y: auto; */
}
</style>
