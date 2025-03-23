<template>
  <div id="userinfo">
    <!-- 操作区 -->
    <div class="actions" >
      <div class="row">
        <el-input v-if="isAdmin(auth)" clearable
                  v-model="queryUserInfoCmd.admin_id" class="ipt" placeholder="请输入管理员id" />
        <el-input v-if="isAdmin(auth) || isTeacher(auth)" clearable
                  v-model="queryUserInfoCmd.teacher_id" class="ipt" placeholder="请输入教师id" />
        <el-input v-if="isAdmin(auth) || isTeacher(auth) || isParent(auth)" clearable
                  v-model="queryUserInfoCmd.student_id" class="ipt" placeholder="请输入学生id" />
        <el-input v-if="isAdmin(auth) || isTeacher(auth)" clearable
            v-model="queryUserInfoCmd.class_id" class="ipt" placeholder="请输入班级id" />
        <el-input v-if="isAdmin(auth) || isTeacher(auth)" clearable
            v-model="queryUserInfoCmd.major" class="ipt" placeholder="请输入专业" />
        <el-button class="btn" type="primary" @click="queryUserinfoHandler">查询</el-button>
      </div>
      <div class="row">
        <el-button v-if="isAdmin(auth)" @click="queryTypeAllUserinfoHandler(0)">查询所有管理员</el-button>
        <el-button v-if="isAdmin(auth) || isTeacher(auth)" @click="queryTypeAllUserinfoHandler(1)">查询所有老师</el-button>
        <el-button v-if="isAdmin(auth) || isTeacher(auth)" @click="queryTypeAllUserinfoHandler(2)">查询所有学生</el-button>
      </div>
    </div>

    <!-- 数据区 -->
    <div class="data">
      <el-table :data="userTable" border style="width: 100%">
        <el-table-column prop="user_id" label="用户id" />
        <el-table-column prop="username" label="用户名" />
        <el-table-column prop="gender" label="性别" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="phone" label="手机号" />
        <el-table-column prop="classInfo.class_id" label="班级id" />
        <el-table-column prop="classInfo.college" label="学院" />
        <el-table-column prop="classInfo.major" label="专业" />
        <el-table-column prop="classInfo.grade" label="班级" />
        <el-table-column prop="classInfo.year" label="学年" />
        <el-table-column prop="current_state" label="状态">
          <template #default="{ row }">
            <el-tag v-if="row.current_state === -2" type="success">毕业</el-tag>
            <el-tag v-else-if="row.current_state === -1" type="warning">预报到</el-tag>
            <el-tag v-else-if="row.current_state === 0" type="primary">就读</el-tag>
            <el-tag v-else-if="row.current_state === 1" type="danger">休学中</el-tag>
            <el-tag v-else type="info">未知状态</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="card_id" label="卡片号" />
        <el-table-column prop="dorm_id" label="宿舍号" />
        <el-table-column prop="create_time" label="创建时间" />
      </el-table>
    </div>


  </div>
</template>

<script setup>
// 数据区
import {queryUserinfoApi} from "../api/UserinfoApi.js";
import {isAdmin, isParent, isStudent, isTeacher} from "../infra/tools/authTools.js";
import {useUserInfoStore} from "../infra/store/userinfoStore.js";
import {ElMessage} from "element-plus";

const userinfo = useUserInfoStore()
const auth = userinfo.getAuth()
const userTable = ref([
  //   {
  //   "user_id": 10003,
  //   "username": "小静",
  //   "gender": "女",
  //   "email": "2234",
  //   "phone": "1237",
  //   "classInfo": {
  //     "class_id": 1,
  //     "college": "信息工程学院",
  //     "major": "软件工程",
  //     "grade": 1,
  //     "year": 4
  //   },
  //   "current_state": -1,  //-2毕业 -1预报到 0就读 1表示休学中
  //   "card_id": "610902200107128841",
  //   "dorm_id": "10-207",
  //   "student_array": null,
  //   "create_time": "2025-03-02",
  // }
])

/**
 * 查询用户列表
 */
const queryUserInfoCmd = ref({
  class_id: null,
  major: null,
  student_id: null,
  admin_id: null,
  teacher_id: null
})
const resetQueryUserInfoCmd = async () => {
  queryUserInfoCmd.value = {
    class_id: null,
    major: null,
    student_id: null,
    admin_id: null,
    teacher_id: null
  }
}
const queryTypeAllUserinfoHandler = async (type) => {
  if (type === 0) {
    queryUserInfoCmd.value.admin_id = 0
  } else if (type === 1) {
    queryUserInfoCmd.value.teacher_id = 0
  } else if (type === 2) {
    queryUserInfoCmd.value.student_id = 0
  } else {
    ElMessage.error("非法参数")
    return
  }
  try {
    const resp = await queryUserinfoHandler()
    userTable.value = resp.data
  } finally {
    await resetQueryUserInfoCmd()
  }
}
const queryUserinfoHandler = async () => {
  const resp = await queryUserinfoApi({
    class_id: queryUserInfoCmd.value.class_id,
    major: queryUserInfoCmd.value.major,
    student_id: queryUserInfoCmd.value.student_id,
    admin_id: queryUserInfoCmd.value.admin_id,
    teacher_id: queryUserInfoCmd.value.teacher_id
  })
  userTable.value = resp.data
}

onMounted(async () => {
  await queryUserinfoHandler()
})

</script>

<style lang="scss" scoped>
#userinfo {
  width: 100%;
  height: 100%;
}

#userinfo .actions {
  margin-top: 15px
}

.actions .row {
  display: flex;
  justify-content: left;
  align-items: center;
  margin-top: 10px;

  .ipt {
    width: 200px;
    margin-right: 20px;
  }
  .btn {
    width: 100px;
  }
}


#userinfo .data {
  width: 100%;
  height: 100%;
  margin-top: 20px;
  overflow-y: auto;
}

</style>
