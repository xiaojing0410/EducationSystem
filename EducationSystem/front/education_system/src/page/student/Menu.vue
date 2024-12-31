<template>
  <div>
    <el-menu
        :default-active="1"
        class="el-menu-demo"
        mode="horizontal"
        :ellipsis="false"
        @select=""
    >

      <el-menu-item index="0">
        <img
            style="width: 50px"
            src="../../assets/doge.gif"
            alt="Element logo"
        />
      </el-menu-item>

      <router-link :to="{name: 'Score'}">
        <el-menu-item index="1">
          文化课成绩
        </el-menu-item>
      </router-link>

      <router-link :to="{name: 'Physical'}">
        <el-menu-item index="2">
          体测成绩
        </el-menu-item>
      </router-link>

      <router-link :to="{name: 'Honor'}">
        <el-menu-item index="3">
          荣誉
        </el-menu-item>
      </router-link>

      <router-link :to="{name: 'StudentInfo'}">
        <el-menu-item index="5">
          欢迎回来 {{ }}
        </el-menu-item>
      </router-link>

      <el-menu-item index="6" @click="logout_cmd()">
        退出登录
      </el-menu-item>
    </el-menu>

  </div>
</template>

<script setup>
import {logout_cmd} from "@/composables/user/user-cmd.js"
</script>

<style lang="scss" scoped>
</style>