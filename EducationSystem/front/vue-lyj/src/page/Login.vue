<template>
  <div class="login-container">
    <el-card class="login-form">
      <h2 class="title">登录</h2>

      <el-form :model="loginForm" ref="loginFormRef" label-position="top">
        <!-- 用户名输入框 -->
        <el-form-item label="用户名" :rules="[{ required: true, message: '请输入用户名', trigger: 'blur' }]">
          <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
        </el-form-item>

        <!-- 密码输入框 -->
        <el-form-item label="密码" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
          <el-input v-model="loginForm.password" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>

        <!-- 登录按钮 -->
        <el-form-item>
          <el-button type="primary" @click="loginFacade" :loading="loading">登录</el-button>
        </el-form-item>
      </el-form>

    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import {ElForm, ElFormItem, ElInput, ElButton, ElRow, ElCol, ElCard, ElMessage} from 'element-plus';

const router = useRouter(); // 用于路由跳转
const loginForm = reactive({
  username: '',
  password: '',
});

const loading = ref(false); // 按钮加载状态
const loginFormRef = ref(null);

// 登录处理
const loginFacade = async () => {
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
