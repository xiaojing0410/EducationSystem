<template>
  <!-- 个人信息描述 -->
  <div id="my-info">
    <el-descriptions
        title="个人信息"
        direction="vertical"
        border
        style="margin-top: 20px"
    >
<!--  头像，如果后期想加  -->
<!--      <el-descriptions-item-->
<!--          :rowspan="2"-->
<!--          :width="140"-->
<!--          label="头像"-->
<!--          align="center"-->
<!--      >-->
<!--        <el-image-->
<!--            style="width: 100px; height: 100px"-->
<!--            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"-->
<!--        />-->
<!--      </el-descriptions-item>-->

      <el-descriptions-item label="用户名">
        {{ userinfo.username }}
      </el-descriptions-item>

      <el-descriptions-item label="性别">
        {{ userinfo.gender }}
      </el-descriptions-item>

      <el-descriptions-item label="学年制">
        {{ userinfo.year }}
      </el-descriptions-item>

      <el-descriptions-item label="专业">
        {{ userinfo.major }}
      </el-descriptions-item>

      <el-descriptions-item label="班级">
        {{ userinfo.class_id }}
      </el-descriptions-item>

      <el-descriptions-item label="手机号">
        <el-input v-model="userinfo.phone" />
      </el-descriptions-item>

      <el-descriptions-item label="邮箱">
        <el-input v-model="userinfo.email" />
      </el-descriptions-item>

      <el-descriptions-item label="旧密码">
        <el-input v-model="oldpassword" />
      </el-descriptions-item>

      <el-descriptions-item label="密码(修改密码前，必须输入旧密码)">
        <el-input v-model="userinfo.password" />
      </el-descriptions-item>

      <el-descriptions-item label="入学时间">
        {{ userinfo.createtime }}
      </el-descriptions-item>

    </el-descriptions>

    <!--  操作区  -->
    <div id="opt">
      <el-button class="btn" type="primary" plain @click="update_user_info_cmd(oldpassword, userinfo.password, userinfo.email, userinfo.phone)">修改信息</el-button>
    </div>
  </div>
</template>

<script setup>
import {update_user_info_cmd} from "@/composables/user/user-cmd"
import { get_user_info } from "@/stores/user-local"

const oldpassword = ref('')
const userinfo = ref({
  id: '12',
  username: '龙洋静',
  year: '4', // 学年制
  major: '软件工程', // 专业
  class_id: '1', // 班级id
  gender: '女',
  email: 'www.baidu.com',
  phone: '13895642222',
  password: '',
  createtime: '2024.01.12',
  updatetime: '2024.01.12'
})

onBeforeMount(()=> {
  userinfo.value = JSON.parse(get_user_info())[0]
})
</script>

<style lang="scss" scoped>
#my-info {
  width: 1200px;
  margin: 0 auto;
}

#opt {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}
#opt .btn {
  width: 300px;
  height: 50px;
  font-size: 20px;
}

</style>