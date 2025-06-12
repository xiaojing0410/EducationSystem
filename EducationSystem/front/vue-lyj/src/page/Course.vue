<template>
  <div id="course">
    <!-- 操作区 -->
    <div class="actions" >
      <div class="row">
        <el-input v-model="queryCourseCmd.course_info" clearable class="ipt" placeholder="请输入课程信息" />
        <el-button class="btn" type="primary" @click="queryCourseHandler">查询</el-button>
        <el-button class="btn" type="warning" @click="addCourseCmd.dialogVisible = true">新增课程</el-button>
      </div>
    </div>

    <!-- 数据区 -->
    <div class="data">
      <el-table :data="courseTable" border style="width: 100%">
        <el-table-column prop="course_code_id" label="课程id" />
        <el-table-column prop="course_name" label="课程名称" />
        <el-table-column prop="type" label="类型" />
        <el-table-column prop="credit" label="学分" />
<!--        <el-table-column label="操作">-->
<!--          <template #default="{ row }">-->
<!--            <el-button type="danger" plain @click="removeCourseHandler(row.course_code_id)">-->
<!--              删除-->
<!--            </el-button>-->
<!--          </template>-->
<!--        </el-table-column>-->
      </el-table>
    </div>

    <el-dialog v-model="addCourseCmd.dialogVisible" title="新增课程" width="500px">
      <el-form :model="addCourseCmd" label-width="100px">
        <el-form-item label="课程名称">
          <el-input v-model="addCourseCmd.course_name" placeholder="请输入课程名称" />
        </el-form-item>
        <el-form-item label="课程类型">
          <el-input v-model="addCourseCmd.type" placeholder="请输入类型(必修/选修)" />
        </el-form-item>
        <el-form-item label="学分">
          <el-input v-model="addCourseCmd.credit" placeholder="请输入学分" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="addCourseCmd.dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addCourseHandler">提交</el-button>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
// 数据区
import {addCourseApi, queryCourseApi} from "../api/CourseApi.js";
import {ElMessage} from "element-plus";

const courseTable = ref([
  {
    course_code_id: 1,
    course_name: "数学",
    type: "必修",
    credit: 5
  }
])

/**
 * 查询课程
 */
const queryCourseCmd = ref({
  course_info: null,
})
const queryCourseHandler = async () => {
  const resp = await queryCourseApi({
    course_info: queryCourseCmd.value.course_info
  })
  courseTable.value = resp.data
}

/**
 * 添加课程
 */
const addCourseCmd = ref({
  dialogVisible: false,
  course_name: null,
  type: null,
  credit: null,
})
const addCourseHandler = async () => {
  await addCourseApi({
    course_name: addCourseCmd.value.course_name,
    type: addCourseCmd.value.type,
    credit: addCourseCmd.value.credit
  })
  addCourseCmd.value.dialogVisible = false
  await queryCourseHandler()
  ElMessage.success("添加成功")
}

onMounted(async () => {
  await queryCourseHandler()
})

</script>

<style lang="scss" scoped>
#course {
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
