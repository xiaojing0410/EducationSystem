<template>
  <div id="nav">
    <el-menu
        active-text-color="#00D1FF"
        background-color="#1E1E2E"
        text-color="#ABB2BF"
        class="el-menu-vertical-demo left-menu"
        default-active="0"
    >
      <el-menu-item index="0">
        <strong>学生学习成效智能评价系统</strong>
      </el-menu-item>

      <router-link :to="{name: 'UserInfo'}">
        <el-menu-item index="1">
          <span>用户管理</span>
        </el-menu-item>
      </router-link>

      <router-link :to="{name: 'Honor'}">
        <el-menu-item index="2">
          <span>荣誉管理</span>
        </el-menu-item>
      </router-link>

      <router-link :to="{name: 'Course'}">
        <el-menu-item index="3">
          <span>课程管理</span>
        </el-menu-item>
      </router-link>

      <router-link :to="{name: 'CoursePlan'}">
        <el-menu-item index="4">
          <span>课程安排</span>
        </el-menu-item>
      </router-link>

      <router-link :to="{name: 'Attendance'}">
        <el-menu-item index="5">
          <span>考勤管理</span>
        </el-menu-item>
      </router-link>

      <el-menu-item index="6">
        <span>成绩管理</span>
      </el-menu-item>

      <router-link :to="{name: 'Physical'}">
        <el-menu-item index="7">
          <span>体测中心</span>
        </el-menu-item>
      </router-link>

      <router-link :to="{name: 'Evaluation'}">
        <el-menu-item index="8">
          <span>评测中心</span>
        </el-menu-item>
      </router-link>

      <router-link :to="{name: 'SelfInfo'}">
        <el-menu-item index="9">
          <span>个人信息</span>
        </el-menu-item>
      </router-link>

    </el-menu>

    <div class="container-right">
      <div class="top-menu">
        <div class="col">用户名: {{ userinfo.username }}</div>
        <div class="col">角色: {{ userinfo.auth }}</div>
        <div class="col" @click="logoutHandler">退出登录</div>
      </div>

      <!--   除了登录注册，所有的 router 渲染根基都在这里  -->
      <div class="context">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script setup>
import {useUserInfoStore} from "../infra/store/userinfoStore.js";
import router from "../infra/router.js";

const userinfo = useUserInfoStore()

const logoutHandler = () => {
  if (!confirm("您确定要退出登录么？")) {
    return
  }
  router.push({name: "Login"})
}

</script>

<style lang="scss" scoped>
#nav {
  width: 100%;
  height: 100%;
  display: flex;
}
#nav .logo {
  width: 25px;
  height: 25px;
  margin-right: 5px;
}

#nav .left-menu {
  width: 220px;
  min-width: 220px;
  max-width: 220px; /* 防止被缩小或放大 */
  height: 100%;
}

#nav .container-right {
  flex-grow: 1; /* 填补剩余宽度 */
}

#nav .top-menu {
  width: 100%;
  height: 80px;
  display: flex;
  justify-content: left;
  align-items: center;
  background: #2C3E50;  /* 经典后台深色蓝灰 */
  padding: 10px 20px;
  color: #ECF0F1;  /* 浅色字体，增强对比 */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}
.col {
  font-size: 14px;
  margin-left: 15px;
}
.col:last-child {
  cursor: pointer;
  color: #F39C12;  /* 退出按钮高亮 */
  font-weight: bold;
  transition: color 0.3s ease;
}

.col:last-child:hover {
  color: #E74C3C;  /* 悬停时变成红色，提示危险操作 */
}
</style>