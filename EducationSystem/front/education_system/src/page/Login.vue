<template>
  <div id="login">
    <div class="main">
      <div class="title">高校智能教育评价系统登录</div>
      <div class="opt">
        <div class="row">
          <span>账号: </span>
          <el-input class="username ipt" type="text" v-model="idIpt" />
        </div>
        <div class="row">
          <span>密码: </span>
          <el-input class="password ipt" type="password" v-model="passwordIpt"/>
        </div>
      </div>
      <div class="reg">
        <router-link class="note" :to="{name: 'Reg'}">还没有账号？点我注册</router-link>
      </div>
      <div class="btn">
        <el-button class="login-btn" type="primary" @click="login_cmd(idIpt, passwordIpt, identityIpt)">登录</el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";
import {login_cmd} from "@/composables/user/user-cmd.js"
/**
 * 登录
 */
const idIpt = ref("1")
const passwordIpt = ref("123")
const identityIpt = ref(1)

</script>

<style lang="scss" scoped>
.main {
  width: 500px;
  height: 360px;
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

.main .reg {
  display: flex;
  justify-content: center;
  margin: 5px 0;

  .note {
    color: grey;
    cursor: pointer;
  }
}

</style>