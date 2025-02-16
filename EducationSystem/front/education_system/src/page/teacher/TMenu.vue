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

      <router-link :to="{name: 'TStudentInfo'}">
        <el-menu-item index="1">
          学生信息查询
        </el-menu-item>
      </router-link>

      <router-link :to="{name: 'TScore'}">
        <el-menu-item index="2">
          学生成绩
        </el-menu-item>
      </router-link>

      <router-link :to="{name: 'THonor'}">
        <el-menu-item index="3">
          学生荣誉
        </el-menu-item>
      </router-link>

      <router-link :to="{name: 'TCourse'}">
        <el-menu-item index="4">
          学生课程
        </el-menu-item>
      </router-link>

      <router-link :to="{name: 'TPhysical'}">
        <el-menu-item index="5">
          学生体测成绩
        </el-menu-item>
      </router-link>

      <router-link :to="{name: 'TInfo'}">
        <el-menu-item index="6">
          个人信息
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