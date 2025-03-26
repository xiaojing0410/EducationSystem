<template>
  <div>
    <el-card class="user-info-card">
      <div slot="header" class="clearfix">
        <span class="card-title">个人信息</span>
      </div>

      <el-form :model="userTable" ref="userForm" label-width="120px" @submit.native.prevent>
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户名">
              <el-input v-model="userTable.username" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学号">
              <el-input v-model="userTable.user_id" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="性别">
              <el-input v-model="userTable.gender" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学院">
              <el-input v-model="userTable.classInfo.college" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="专业">
              <el-input v-model="userTable.classInfo.major" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="年级">
              <el-input v-model="userTable.classInfo.grade" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="邮箱">
              <el-input v-model="userTable.email"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="电话">
              <el-input v-model="userTable.phone"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="密码">
              <el-input type="password" v-model="userTable.password"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="旧密码">
              <el-input type="password" v-model="userTable.oldpassword"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="宿舍号">
              <el-input v-model="userTable.dorm_id" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="卡号">
              <el-input v-model="userTable.card_id" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="创建时间">
              <el-input v-model="userTable.create_time" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
            <el-button type="primary" @click="updateUserinfoHandler">保存修改</el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>

import {queryUserinfoApi, updateUserinfoApi} from "../api/UserinfoApi.js";
import {ElMessage} from "element-plus";

const userTable = ref({
  user_id: 10003,
  username: "小静",
  password: null,
  oldpassword: null,
  gender: "女",
  email: null,
  phone: null,
  create_time: "2025-03-02",
  classInfo: {
    class_id: 1,
    college: "信息工程学院",
    major: "软件工程",
    grade: 1,
    year: 4
  },
  current_state: -1,
  card_id: "610902200107128841",
  dorm_id: "10-207",
  student_array: null
})

/**
 * 查询个人信息
 */
const querySelfInfoHandler = async () => {
  const resp = await queryUserinfoApi({})
  const user = resp.data[0]
  userTable.value.user_id = user.user_id
  userTable.value.username = user.username
  userTable.value.gender = user.gender
  userTable.value.email = user.email
  userTable.value.phone = user.phone
  userTable.value.create_time = user.create_time
  userTable.value.classInfo = user.classInfo
  userTable.value.current_state = user.current_state
  userTable.value.card_id = user.card_id
  userTable.value.dorm_id = user.dorm_id
  userTable.value.student_array = user.student_array
}

/**
 * 修改用户信息
 */
const updateUserinfoHandler = async () => {
  await updateUserinfoApi({
    oldpassword: userTable.value.oldpassword,
    password: userTable.value.password,
    email: userTable.value.email,
    phone: userTable.value.phone,
  })
  await querySelfInfoHandler()
  ElMessage.success("修改成功")
}


onMounted(async () => {
  await querySelfInfoHandler()
})

</script>

<style lang="scss" scoped>

</style>
