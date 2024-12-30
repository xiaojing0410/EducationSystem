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

      <router-link :to="{name: 'TeacherInfo'}">
        <el-menu-item index="5">
          欢迎回来 {{ }}
        </el-menu-item>
      </router-link>

      <el-menu-item index="6" >
        退出登录
      </el-menu-item>
    </el-menu>

  </div>
</template>

<script setup>

</script>

<style lang="scss" scoped>
</style>