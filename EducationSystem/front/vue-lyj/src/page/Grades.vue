<template>
  <div id="grades">
    <!-- 操作区 -->
    <div class="actions">
      <div class="row">
        <!-- 填学号；填班级id；填学号+学期；填班级id+学期 -->
        <el-autocomplete clearable
          v-model="queryGradesCmd.class_id" class="ipt" placeholder="请输入班级id"
          :fetch-suggestions="queryClassBasicinfoHandler"
          @select="handleSelectClassId"
          :hide-loading="true"  style="width: 200px; margin-right: 20px;"
        />
        <el-autocomplete clearable
          v-model="queryGradesCmd.student_id" class="ipt" placeholder="请输入学号"
          :fetch-suggestions="queryUserBasicinfoHandler"
          @select="handleSelectStudent"
          :hide-loading="true" style="width: 200px; margin-right: 20px;"
        />
        <el-input class="ipt" v-model="queryGradesCmd.semester" placeholder="请输入学期" />
        <el-button @click="queryGradesHandler" class="btn" type="primary">查询</el-button>
      </div>
    </div>

    <!-- 数据区 -->
    <div class="data">
      <el-table :data="gradesTable" border style="width: 100%">
        <el-table-column prop="user.id" label="学号" width="100"></el-table-column>
        <el-table-column prop="user.username" label="姓名" width="120"></el-table-column>
        <el-table-column prop="classInfo.college" label="学院" width="160"></el-table-column>
        <el-table-column prop="classInfo.major" label="专业" width="140"></el-table-column>
        <!-- <el-table-column prop="grades.grades_id" label="成绩ID" width="100"></el-table-column> -->
        <el-table-column prop="grades.course_id" label="课程ID" width="100"></el-table-column>
        <el-table-column prop="courseCode.course_name" label="课程" width="100"></el-table-column>
        <el-table-column prop="grades.score" label="成绩" width="100"></el-table-column>
        <el-table-column prop="grades.gpa" label="GPA" width="100"></el-table-column>

        <el-table-column prop="grades.state" label="成绩状态" width="120">
          <template #default="{ row }">
            <el-tag v-if="row.grades.state === 1" type="success">已录入</el-tag>
            <el-tag v-else type="warning">未录入</el-tag>
          </template>
        </el-table-column>

        <el-table-column prop="grades.pass" label="是否通过" width="120">
          <template #default="{ row }">
            <el-tag v-if="row.grades.pass === 1" type="success">通过</el-tag>
            <el-tag v-else-if="row.grades.pass === -1" type="danger">不通过</el-tag>
            <el-tag v-else type="info">未考试</el-tag>
          </template>
        </el-table-column>

        <el-table-column label="操作" v-if="!isStudent(auth) &&~ !isParent(userinfo.getAuth())">
          <template #default="{ row }">
            <el-button type="warning" @click="openUpdateGradesHandler(row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog v-model="updateGradesCmd.dialogVisible" title="新增课程" width="500px">
      <el-form :model="updateGradesCmd" label-width="100px">
        <el-form-item label="成绩id">
          <el-input v-model="updateGradesCmd.grades_id" disabled />
        </el-form-item>
        <el-form-item label="分数">
          <el-input v-model="updateGradesCmd.score" placeholder="请输入成绩" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="updateGradesCmd.dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="updateGradesHandle">提交</el-button>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
// 数据区
import {queryGradesApi, updateGradesApi} from "../api/GradesApi.js";
import {ElMessage} from "element-plus";
import {useUserInfoStore} from "../infra/store/userinfoStore.js";
import {isParent, isStudent, isTeacher} from "../infra/tools/authTools.js";
import {queryClassBasicInfoApi} from "../api/ClassApi.js";
import {queryUserBasicinfoApi} from "../api/UserinfoApi.js";
const userinfo = useUserInfoStore()
const auth = userinfo.getAuth()
const gradesTable = ref([
  // {
  //   user: {
  //     id: 10001,
  //     username: "小静",
  //     teacher_id: 10001
  //   },
  //   grades: {
  //     grades_id: 1,
  //     student_id: 10001,
  //     course_id: 1,
  //     score: 89.0,
  //     state: 1,
  //     pass: 1,
  //     gpa: 3.9
  //   },
  //   classInfo: {
  //     class_id: 1,
  //     college: "信息工程学院",
  //     major: "软件工程",
  //     grade: 1,
  //     year: 4
  //   }
  // }
])

/**
 * 查询成绩
 */
const queryGradesCmd = ref({
  class_id: null,
  semester: null,
  student_id: null,
})
const queryGradesHandler = async () => {
  const resp = await queryGradesApi(toRaw(queryGradesCmd.value))
  gradesTable.value = resp.data
}

/**
 * 编辑成绩
 */
const updateGradesCmd = ref({
  dialogVisible: false,
  grades_id: null,
  score: null
})
const openUpdateGradesHandler = (row) => {
  updateGradesCmd.value.dialogVisible = true
  updateGradesCmd.value.grades_id = row.grades.grades_id
  updateGradesCmd.value.score = row.grades.score
}
const updateGradesHandle = async () => {
  const resp = await updateGradesApi(toRaw(updateGradesCmd.value))
  updateGradesCmd.value.dialogVisible = false
  await queryGradesHandler()
  ElMessage.success("成绩修改成功")
}

// 班级 - 查询
const queryClassBasicinfoHandler = async (query, cb) => {
  if (!query) {
    cb([])
    return
  }
  const resp = await queryClassBasicInfoApi({
    info: queryGradesCmd.value.class_id
  })
  // 判断返回的数据是否存在
  if (resp && resp.data) {
    const suggestions = resp.data.map(item => ({
      value: item, // 显示 user_id 字段
      label: item,
    }))
    cb(suggestions)
  } else {
    cb([])
  }
}
// 学生 - 查询
const queryUserBasicinfoHandler = async (query, cb) => {
  if (!query) {
    cb([])
    return
  }
  const resp = await queryUserBasicinfoApi({
    info: queryGradesCmd.value.student_id,
    type: 3
  })
  // 判断返回的数据是否存在
  if (resp && resp.data) {
    const suggestions = resp.data.map(item => ({
      value: item, // 显示 user_id 字段
      label: item,
    }))
    cb(suggestions)
  } else {
    cb([])
  }
}
const handleSelectClassId = (item) => {
  queryGradesCmd.value.class_id = item.label.split('|').map(s => s.trim())[0]
  console.log('Selected:', queryGradesCmd.value.class_id)
}
const handleSelectStudent = (item) => {
  queryGradesCmd.value.student_id = item.label.split('|').map(s => s.trim())[1]
  console.log('Selected:', queryGradesCmd.value.student_id)
}
/**
 * 只能通过点击按钮查询
 */
// onMounted(async () => {
//   await queryGradesHandler()
// })

</script>

<style lang="scss" scoped>
#grades {
  width: 100%;
  height: 100%;
}

.actions {
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


.data {
  width: 100%;
  height: 100%;
  margin-top: 20px;
  overflow-y: auto;
}

</style>
