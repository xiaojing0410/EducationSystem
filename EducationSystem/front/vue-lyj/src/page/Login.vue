<template>
  <div class="login-container">
    <el-card class="login-form">
      <h2 class="title">登录</h2>

      <el-form :model="loginTable" label-position="top">
        <el-form-item label="用户id">
          <el-input v-model="loginTable.userId" placeholder="请输入用户id"></el-input>
        </el-form-item>

        <el-form-item label="密码">
          <el-input v-model="loginTable.password" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="loginHandler">登录</el-button>
        </el-form-item>
      </el-form>

    </el-card>
  </div>
</template>

<script setup>
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import {ElForm, ElFormItem, ElInput, ElButton, ElRow, ElCol, ElCard, ElMessage} from 'element-plus';
import {loginApi, queryUserinfoApi} from "../api/UserinfoApi.js";
import {useUserInfoStore} from "../infra/store/userinfoStore.js";

const userinfo = useUserInfoStore()
const router = useRouter(); // 用于路由跳转
const loginTable = reactive({
  userId: "2",
  password: "123",
});
const loginHandler = async () => {
  const respToken = await loginApi({
    user_id: loginTable.userId,
    password: loginTable.password,
  })
  localStorage.setItem("token", respToken.data)
  const respUser = await queryUserinfoApi({})
  userinfo.update({
    id: respUser.data[0].user_id,
    username: respUser.data[0].username,
  })
  ElMessage.success("登录成功")
  await router.push({name: 'UserInfo'})
};

// 跳转到注册页面
const goToRegister = () => {
  router.push({ name: 'Reg' }); // 跳转到注册页面
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-form {
  width: 400px;
  padding: 20px;
}

.title {
  text-align: center;
  margin-bottom: 20px;
}

.signup-link {
  text-align: center;
  margin-top: 10px;
}
</style>
