<template>
  <div id="student">
    <!-- 查询区  -->
    <div class="query">
      <el-input class="ipt" v-model="queryStudentReq.studentId" placeholder="请输入学生id" />
      <el-input class="ipt" v-model="queryStudentReq.classId" placeholder="请输入班级id" />
      <el-button class="btn" type="primary" plain>查询</el-button>
    </div>

    <!--  学生信息列表  -->
    <div class="list">
      <el-table :data="studentList" style="width: 100%" height="600">
        <el-table-column prop="id" label="学生ID" />
        <el-table-column prop="username" label="用户名" />
        <el-table-column prop="year" label="学年制" />
        <el-table-column prop="major" label="专业" />
        <el-table-column prop="class_id" label="班级id" />
        <el-table-column prop="gender" label="性别" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="phone" label="手机号" />
        <el-table-column prop="createtime" label="创建时间" />
      </el-table>
    </div>
  </div>
</template>

<script setup>

//学生信息查询参数
const queryStudentReq = ref({
  studentId: null,
  classId: null,
})

// 体测成绩列表
const studentList = ref([
  {
    id: '12',
    username: '龙洋静',
    year: '4', // 学年制
    major: '软件工程', // 专业
    class_id: '1', // 班级id
    gender: '女',
    email: 'www.baidu.com',
    phone: '13895642222',
    // password: '', // 不展示
    createtime: '2024.01.12',
    // updatetime: '2024.01.12' // 这个不展示，没必要
  }
])

</script>

<style lang="scss" scoped>
#student {
  width: 1200px;
  margin: 0 auto;
}

#student .list {
  margin: 20px auto;
}

#student .query {
  width: 1200px;
  padding-top: 20px;
  display: flex;
  justify-content: left;
  align-items: center;

  .ipt {
    width: 250px;
    height: 45px;
    margin-right: 10px;
  }

  .btn {
    width: 100px;
    height: 45px;
  }
}
</style>
