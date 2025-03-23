<template>
  <div id="relearn">
    <!-- 操作区 -->
    <div class="actions" >
      <div class="row">
        <el-input v-model="queryRelearnCmd.student_id" class="ipt" placeholder="请输入学生id" />
        <el-input v-model="queryRelearnCmd.semester" class="ipt" placeholder="请输入学期" />
        <el-button class="btn" type="primary" @click="queryRelearnHandler">查询</el-button>
        <el-button class="btn" type="warning" @click="addRelearnCmd.isShow = true">新增重修记录</el-button>
      </div>
    </div>

    <!-- 数据区 -->
    <div class="data">
      <el-table :data="relearnTable" border style="width: 100%">
        <el-table-column prop="student_id" label="学号"></el-table-column>
        <el-table-column prop="username" label="姓名"></el-table-column>

        <el-table-column label="重修信息">
          <el-table-column prop="relearn.relearn_id" label="重修 ID"></el-table-column>
          <el-table-column prop="relearn.teacher_id" label="教师 ID"></el-table-column>
          <el-table-column prop="relearn.course_code_id" label="课程 ID"></el-table-column>
          <el-table-column prop="relearn.credit" label="学分"></el-table-column>
          <el-table-column prop="relearn.score_old" label="原成绩"></el-table-column>
          <el-table-column prop="relearn.score_new" label="新成绩"></el-table-column>
          <el-table-column prop="relearn.semester" label="学期"></el-table-column>
          <el-table-column prop="relearn.reason" label="原因"></el-table-column>

          <el-table-column label="状态">
            <template #default="{ row }">
              <el-tag :type="getStateType(row.relearn.state)">
                {{ getStateText(row.relearn.state) }}
              </el-tag>
            </template>
          </el-table-column>
        </el-table-column>

        <el-table-column label="班级信息">
          <el-table-column prop="classInfo.class_id" label="班级 ID"></el-table-column>
          <el-table-column prop="classInfo.college" label="学院"></el-table-column>
          <el-table-column prop="classInfo.major" label="专业"></el-table-column>
          <el-table-column prop="classInfo.grade" label="年级"></el-table-column>
          <el-table-column prop="classInfo.year" label="届数"></el-table-column>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="{ row }">
            <el-button type="warning" @click="openUpdateRelearnDialog(row)">更新重修成绩</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog v-model="addRelearnCmd.isShow" title="新增重修记录" width="500px">
      <el-form :model="addRelearnCmd" label-width="100px" ref="formRef">
        <el-form-item label="学号" prop="student_id">
          <el-input v-model="addRelearnCmd.student_id" placeholder="请输入学号" />
        </el-form-item>

        <el-form-item label="教师 ID" prop="teacher_id">
          <el-input v-model="addRelearnCmd.teacher_id" placeholder="请输入教师 ID" />
        </el-form-item>

        <el-form-item label="课程 ID" prop="course_code_id">
          <el-input v-model="addRelearnCmd.course_code_id" placeholder="请输入课程 ID" />
        </el-form-item>

        <el-form-item label="学分" prop="credit">
          <el-input-number v-model="addRelearnCmd.credit" :min="0" />
        </el-form-item>

        <el-form-item label="原成绩" prop="score_old">
          <el-input-number v-model="addRelearnCmd.score_old" :min="0" :max="100" />
        </el-form-item>

        <el-form-item label="学期" prop="semester">
          <el-input v-model="addRelearnCmd.semester" placeholder="请输入学期" />
        </el-form-item>

        <el-form-item label="原因" prop="reason">
          <el-input v-model="addRelearnCmd.reason" type="textarea" placeholder="请输入重修原因" />
        </el-form-item>

        <el-form-item label="状态" prop="state">
          <el-select v-model="addRelearnCmd.state" placeholder="请选择状态">
            <el-option :value="0" label="重修中"></el-option>
            <el-option :value="1" label="重修通过"></el-option>
            <el-option :value="-1" label="重修不通过"></el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="addRelearnCmd.isShow = false">取消</el-button>
        <el-button type="primary" @click="addRelearnHandler">提交</el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="updateRelearnCmd.isShow" title="更新重修记录成绩" width="500px">
      <el-form :model="updateRelearnCmd" label-width="100px" ref="formRef">
        <el-form-item label="重修记录id">
          <el-input v-model="updateRelearnCmd.relearn_id" disabled />
        </el-form-item>

        <el-form-item label="新成绩">
          <el-input v-model="updateRelearnCmd.score_new" placeholder="请输入新成绩"/>
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="updateRelearnCmd.isShow = false">取消</el-button>
        <el-button type="primary" @click="updateRelearnHandler">提交</el-button>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>

import {addRelearnApi, queryRelearnApi, updateRelearnApi} from "../api/RelearnApi.js";
import {ElMessage} from "element-plus";

const relearnTable = ref([
  //   {
  // "student_id": 10000,
  // "username": "龙洋静",
  // "relearn": {
  //   "relearn_id": 1,
  //   "student_id": 10000,
  //   "teacher_id": 2000,
  //   "course_code_id": 1,
  //   "credit": 5,
  //   "score_old": 44.0,
  //   "score_new": 60.0,
  //   "semester": 20252,
  //   "reason": "考试不及格",
  //   "state": 1
  // },
  // "classInfo": {
  //   "class_id": 1,
  //   "college": "信息工程学院",
  //   "major": "软件工程",
  //   "grade": 1,
  //   "year": 4
  // }}
])
// 状态文本映射
const getStateText = (state) => {
  return state === 0 ? "重修中" : state === 1 ? "重修通过" : "重修不通过";
};
// 状态标签类型映射
const getStateType = (state) => {
  return state === 0 ? "warning" : state === 1 ? "success" : "danger";
};

/**
 * 查询重修记录
 */
const queryRelearnCmd = ref({
  student_id: null,
  semester: null,
})
const queryRelearnHandler = async () => {
  const resp = await queryRelearnApi(toRaw(queryRelearnCmd.value))
  relearnTable.value = resp.data
}

/**
 * 新增重修记录
 */
const addRelearnCmd = ref({
  isShow: false,
  student_id: null,
  teacher_id: null,
  course_code_id: null,
  credit: null,
  score_old: null,
  semester: null,
  reason: null,
  state: null,
})
const addRelearnHandler = async () => {
  await addRelearnApi(toRaw(addRelearnCmd.value))
  ElMessage.success("添加成功")
}

/**
 * 修改重修成绩
 */
const updateRelearnCmd = ref({
  isShow: false,
  relearn_id: null,
  score_new: null
})
const openUpdateRelearnDialog = (row) => {
  updateRelearnCmd.value = {
    isShow: true,
    relearn_id: row.relearn.relearn_id,
    score_new: row.relearn.score_old,
  }
}
const updateRelearnHandler = async () => {
  await updateRelearnApi(toRaw(updateRelearnCmd.value))
  ElMessage.success("更新成功")
}


</script>

<style lang="scss" scoped>
#relearn {
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
