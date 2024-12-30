<template>
  <div id="reg">
    <div class="main">
      <div class="title">注册用户</div>
      <div class="opt">
        <div class="row">
          <span>账号: </span>
          <el-input class="username ipt" type="text" v-model="usernameIpt"/>
        </div>
        <div class="row">
          <span>密码: </span>
          <el-input class="password ipt" type="password" v-model="passwordIpt"/>
        </div>
        <!-- <div class="row">
          <span>权限: </span>
          <el-input class="role ipt" type="text" placeholder="请输入数字(1学生 2教师)" v-model="roleIpt"/>
        </div> -->
      </div>
      <div class="login">
        <router-link class="note" :to="{name: 'Login'}">返回到登录页面</router-link>
      </div>
      <div class="btn">
        <el-button class="login-btn" type="primary" @click="reg_cmd(usernameIpt, passwordIpt, identityIpt)">注册</el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";
import {reg_cmd} from "@/composables/user/user-cmd.js"
const usernameIpt = ref('lyj')
const passwordIpt = ref('123')
const identityIpt = ref(1)
// const roleIpt = ref()

</script>

<style lang="scss" scoped>
.main {
  width: 500px;
  height: 350px;
  max-height: 100vh;
  margin: 150px auto;
  padding: 20px 50px;
  border: 5px solid transparent; /* 透明边框 */
  box-shadow:
      0 0 15px rgba(0, 0, 0, 0.1),  /* 轻微的外部灰色阴影 */
      0 0 30px rgba(0, 0, 0, 0.2),  /* 稍强的外部阴影 */
      0 0 45px rgba(0, 0, 0, 0.3);  /* 强烈的外部灰色阴影 */
}

.main .title {
  width: 300px;
  text-align: center;
  font-size: 30px;
  font-weight: 700;
  margin: 0 auto;
}

.main .opt {
  padding: 10px 5px;
}

.main .row {
  display: flex;
  justify-content: space-around;
  align-items: center;
  margin-top: 10px;

  .ipt {
    width: 300px;
    height: 50px;
    font-size: 20px;
  }
}

.main .btn {
  display: flex;
  justify-content: center;
  margin-top: 10px;

  .login-btn {
    width: 350px;
    height: 50px;
  }
}

.main .login {
  display: flex;
  justify-content: center;
  margin: 5px 0;

  .note {
    color: grey;
    cursor: pointer;
  }
}

</style>
