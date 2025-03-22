<template>
  <div id="class">
    <!-- 操作区 -->
    <div class="actions" >
      <div class="row">
        <el-input class="ipt" placeholder="请输入班级信息" />
        <el-button @click="queryClassInfoHandler" class="btn" type="primary">查询</el-button>
        <el-button class="btn" type="warning" @click="addClassCmd.dialogVisible = true">新增班级</el-button>
      </div>
    </div>

    <!-- 数据区 -->
    <div class="data">
      <el-table :data="classTable" border style="width: 100%">
        <el-table-column prop="class_id" label="班级 ID" width="100" />
        <el-table-column prop="college" label="学院" width="200" />
        <el-table-column prop="major" label="专业" width="200" />
        <el-table-column prop="grade" label="年级" width="100" />
        <el-table-column prop="year" label="学年" width="100" />
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button type="warning" @click="openAddStudentToClassHandler(row)">添加学生</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog v-model="addStudentToClassCmd.isShow" title="添加学生到该班级" width="500px">
      <el-form :model="addStudentToClassCmd" label-width="100px">
        <el-form-item label="班级id">
          <el-input v-model="addStudentToClassCmd.class_id" disabled />
        </el-form-item>
        <el-form-item label="学生id">
          <el-input v-model="addStudentToClassCmd.student_ids" placeholder="请输入学生id(多个id通过逗号分割)" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="addStudentToClassCmd.isShow = false">取消</el-button>
        <el-button type="primary" @click="addStudentToClassHandler">提交</el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="addClassCmd.dialogVisible" title="新增课程" width="500px">
      <el-form :model="addClassCmd" label-width="100px">
        <el-form-item label="学院名称">
          <el-input v-model="addClassCmd.college" placeholder="请输入学院名称" />
        </el-form-item>
        <el-form-item label="专业类型">
          <el-input v-model="addClassCmd.major" placeholder="请输入专业名称" />
        </el-form-item>
        <el-form-item label="年级">
          <el-input v-model="addClassCmd.grade" placeholder="请输入年级" />
        </el-form-item>
        <el-form-item label="学年">
          <el-input v-model="addClassCmd.year" placeholder="请输入学年" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="addClassCmd.dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addClassHandler">提交</el-button>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
// 数据区
import {addClassInfoApi, queryClassInfoApi, updateClassInfoApi} from "../api/ClassApi.js";
import {ElMessage} from "element-plus";

const classTable = ref([
  // {
  //   class_id: 1,
  //   college: "信息工程学院",
  //   major: "软件工程",
  //   grade: 1,
  //   year: 4
  // }
])

/**
 * 查询班级信息
 */
const queryClassInfoHandler = async () => {
  const resp = await queryClassInfoApi()
  classTable.value = resp.data
}

/**
 * 添加班级
 */
const addClassCmd = ref({
  dialogVisible: false,
  college: null,
  major: null,
  grade: null,
  year: null
})
const addClassHandler = async () => {
  const resp = await addClassInfoApi({
    college: addClassCmd.value.college,
    major: addClassCmd.value.major,
    grade: addClassCmd.value.grade,
    year: addClassCmd.value.year
  })
  await queryClassInfoHandler()
  ElMessage.success("班级添加成功")
}

/**
 * 添加学生到指定班级
 */
const addStudentToClassCmd = ref({
  isShow: false,
  student_ids: null, // 逗号分割的数组
  class_id: null,
})
const openAddStudentToClassHandler = (row) => {
  addStudentToClassCmd.value.isShow = true
  addStudentToClassCmd.value.class_id = row.class_id
}
const addStudentToClassHandler = async () => {
  const resp = await updateClassInfoApi({
    student_ids: addStudentToClassCmd.value.student_ids,
    class_id: addStudentToClassCmd.value.class_id
  })
  ElMessage.success("添加成功")
}

onMounted(async () => {
  await queryClassInfoHandler()
})

</script>

<style lang="scss" scoped>
#class {
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
