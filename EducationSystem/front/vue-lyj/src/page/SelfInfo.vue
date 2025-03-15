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
            <el-form-item label="确认密码">
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
            <el-button type="primary" @click="saveChanges">保存修改</el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>

const userTable = ref({
  user_id: 10003,
  username: "小静",
  password: null,
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

</script>

<style lang="scss" scoped>

</style>
