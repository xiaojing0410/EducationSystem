<script setup>
import {useRoute} from "vue-router";
import {computed} from "vue";
import Menu from "@/page/Menu.vue";

//不在登录页面和注册页面才显示菜单栏
const route = useRoute();
const showMenu = computed(() => {
  return route.name !== 'Login' && route.name !== 'Reg'
})
</script>

<template>
  <Menu ></Menu>
  <router-view></router-view>
</template>

<style scoped>

</style>
