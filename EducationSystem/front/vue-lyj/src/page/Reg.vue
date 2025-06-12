<template>
  <div class="register-container">
    <el-card class="register-form">
      <h2 class="title">注册</h2>

      <el-form :model="regCmd" ref="registerFormRef" label-position="top">
        <!-- 选择权限 -->
        <el-form-item label="选择权限" prop="role">
          <el-select v-model="regCmd.identity" placeholder="请选择身份">
            <el-option label="管理员" :value="0"></el-option>
            <el-option label="教师" :value="1"></el-option>
            <el-option label="学生" :value="2"></el-option>
            <el-option label="家长" :value="3"></el-option>
          </el-select>
        </el-form-item>

        <!-- 账号 -->
        <el-form-item label="账号" prop="username" :rules="[{ required: true, message: '请输入账号', trigger: 'blur' }]">
          <el-input v-model="regCmd.username" placeholder="请输入账号"></el-input>
        </el-form-item>

        <!-- 密码 -->
        <el-form-item label="密码" prop="password" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
          <el-input v-model="regCmd.password" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>

        <!-- 确认密码 -->
        <el-form-item label="确认密码" prop="confirmPassword" :rules="[{ validator: validateConfirmPassword, trigger: 'blur' }]">
          <el-input v-model="regCmd.confirmPassword" type="password" placeholder="请再次输入密码"></el-input>
        </el-form-item>

        <el-form-item label="性别" prop="gender">
          <el-input v-model="regCmd.gender" placeholder="请输入性别"></el-input>
        </el-form-item>

        <el-form-item label="身份证号" prop="card_id">
          <el-input v-model="regCmd.card_id" placeholder="请输入身份证号"></el-input>
        </el-form-item>

        <el-form-item v-if="regCmd.identity === 2" label="班级id" prop="class_id">
          <el-input v-model="regCmd.class_id" placeholder="请输入班级id"></el-input>
        </el-form-item>

        <el-form-item v-if="regCmd.identity === 3" label="学生id列表" prop="student_array">
          <el-input v-model="regCmd.student_array" placeholder="请输入学生id列表，使用逗号分割"></el-input>
        </el-form-item>

        <!-- 注册按钮 -->
        <el-form-item>
          <el-button type="primary" @click="handleRegister" :loading="loading">注册</el-button>
        </el-form-item>
      </el-form>

      <!-- 返回登录 -->
      <div class="login-link">
        <span>已有账号？</span>
        <el-button type="text" @click="goToLogin">返回登录</el-button>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import {ElMessage} from "element-plus";
import {regApi} from "../api/UserinfoApi.js";

const router = useRouter();

const regCmd = reactive({
  identity: null, //0管理员 1教师 2学生 3家长
  username: null,
  password: null,
  confirmPassword: null,
  gender: null,
  card_id: null,
  class_id: null,
  student_array: null,
});

const loading = ref(false); // 注册按钮状态
const registerFormRef = ref(null);

// 注册逻辑
const handleRegister = async () => {
  if (regCmd.password !== regCmd.confirmPassword) {
    ElMessage.error("两次输入密码不一致");
  }
  const resp = await regApi(toRaw(regCmd))
  if (resp.code === 0) {
    ElMessage.success("注册成功")
    // 清空字段
    Object.keys(regCmd).forEach(key => {
      regCmd[key] = "";
    });
  }
};

// 返回登录页面
const goToLogin = () => {
  router.push('/login');
};
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.register-form {
  width: 400px;
  padding: 20px;
}

.title {
  text-align: center;
  margin-bottom: 20px;
}

.login-link {
  text-align: center;
  margin-top: 10px;
}
</style>
