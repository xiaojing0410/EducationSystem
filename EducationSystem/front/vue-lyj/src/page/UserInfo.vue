<template>
  <div id="userinfo">
    <!-- 操作区 -->
    <div class="actions" >
      <div class="row">
        <el-select class="ipt"  v-model="selectedRole" placeholder="请选择角色">
          <el-option v-for="role in roles" :key="role.value" :label="role.label" :value="role.value"/>
        </el-select>
      </div>
      <div class="row">
        <el-input class="ipt" placeholder="请输入学生id" />
        <el-input class="ipt" placeholder="请输入班级id" />
        <el-input class="ipt" placeholder="请输入专业" />
        <el-button class="btn" type="primary">查询</el-button>
      </div>
      <div class="row">
        <el-input class="ipt" placeholder="请输入管理员id" />
        <el-button class="btn" type="primary">查询</el-button>
      </div>
      <div class="row">
        <el-input class="ipt" placeholder="请输入教师id" />
        <el-button class="btn" type="primary">查询</el-button>
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
<!--        <el-table-column label="操作">-->
<!--          <template #default="{ row }">-->
<!--            <el-button type="warning" @click="openUpdateUserInfoDialog(row)">编辑</el-button>-->
<!--          </template>-->
<!--        </el-table-column>-->
      </el-table>
    </div>

<!--    &lt;!&ndash; 编辑对话框 &ndash;&gt;-->
<!--    <el-dialog v-model="updateUserInfoCmd.dialogVisible" title="编辑信息">-->
<!--      <el-form label-width="80px">-->
<!--        <el-form-item label="旧密码">-->
<!--          <el-input v-model="updateUserInfoCmd.oldpassword" type="password" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="新密码">-->
<!--          <el-input v-model="updateUserInfoCmd.password" type="password" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="邮箱">-->
<!--          <el-input v-model="updateUserInfoCmd.email" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="手机号">-->
<!--          <el-input v-model="updateUserInfoCmd.phone" />-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <template #footer>-->
<!--        <span>-->
<!--          <el-button @click="updateUserInfoCmd.dialogVisible = false">取消</el-button>-->
<!--          <el-button type="primary" @click="">保存</el-button>-->
<!--        </span>-->
<!--      </template>-->
<!--   </el-dialog>-->

  </div>
</template>

<script setup>
// 数据区
const selectedRole = ref(0);
const roles = [
  { value: 0, label: "管理员" },
  { value: 1, label: "教师" },
  { value: 2, label: "学生" },
];
const userTable = ref([
  {
    "user_id": 10003,
    "username": "小静",
    "gender": "女",
    "email": "2234",
    "phone": "1237",
    "classInfo": {
      "class_id": 1,
      "college": "信息工程学院",
      "major": "软件工程",
      "grade": 1,
      "year": 4
    },
    "current_state": -1,  //-2毕业 -1预报到 0就读 1表示休学中
    "card_id": "610902200107128841",
    "dorm_id": "10-207",
    "student_array": null,
    "create_time": "2025-03-02",
  }
])

// /**
//  * 修改用户信息
//  */
// const updateUserInfoCmd = ref({
//   dialogVisible: false,
//   oldpassword: null,
//   password: null,
//   email: null,
//   phone: null,
// })
// const openUpdateUserInfoDialog = (row) => {
//   updateUserInfoCmd.value.email = row.email
//   updateUserInfoCmd.value.phone = row.phone
//   updateUserInfoCmd.value.dialogVisible = true;
// };

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
