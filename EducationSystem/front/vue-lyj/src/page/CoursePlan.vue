<template>
  <div id="course-plan">
    <!-- 操作区 -->
    <div class="actions" >
      <div class="row">
        <el-input class="ipt" v-model="queryCoursePlanCmd.class_id" placeholder="请输入班级id" />
        <el-input class="ipt" v-model="queryCoursePlanCmd.semester" placeholder="请输入学期" />
        <el-input class="ipt" v-model="queryCoursePlanCmd.student_id" placeholder="请输入学生id" />
        <el-button @click="queryCoursePlanHandler" class="btn" type="primary">查询</el-button>
        <el-button class="btn" type="warning" @click="addCoursePlanCmd.dialogVisible = true">新增课程安排</el-button>
      </div>
    </div>

    <!-- 数据区 -->
    <div class="data">
      <el-table :data="coursePlanTable" style="width: 100%">
        <el-table-column label="课程 ID" prop="course_id" />
        <el-table-column label="学期" prop="semester" />
        <el-table-column label="上课时间" prop="time" />
        <el-table-column label="上课地点" prop="loc" />
        <el-table-column label="课程名称" :prop="'courseCode.course_name'" />
        <el-table-column label="课程类型" :prop="'courseCode.type'" />
        <el-table-column label="学分" :prop="'courseCode.credit'" />
        <el-table-column label="教师" :prop="'teacherInfoVo.username'" />
        <el-table-column label="学院" :prop="'classInfo.college'" />
        <el-table-column label="专业" :prop="'classInfo.major'" />
        <el-table-column label="年级" prop="classInfo.grade" />
        <el-table-column label="学年" prop="classInfo.year" />
<!--        <el-table-column label="操作">-->
<!--          <template #default="{ row }">-->
<!--            <el-button type="danger" plain @click="removeCourseHandler(row.course_id)">-->
<!--              删除-->
<!--            </el-button>-->
<!--          </template>-->
<!--        </el-table-column>-->
      </el-table>
    </div>

    <el-dialog
        title="添加课程安排"
        v-model="addCoursePlanCmd.dialogVisible"
        width="50%"
    >
      <el-form :model="addCoursePlanCmd" label-width="120px">
        <el-form-item label="课程代码">
          <el-input v-model="addCoursePlanCmd.course_code_id" disabled></el-input>
        </el-form-item>

        <el-form-item label="学期">
          <el-input v-model="addCoursePlanCmd.semester" type="number"></el-input>
        </el-form-item>

        <el-form-item label="教师 ID">
          <el-input v-model="addCoursePlanCmd.teacher_id" type="number"></el-input>
        </el-form-item>

        <el-form-item label="班级 ID">
          <el-input v-model="addCoursePlanCmd.class_id" type="number"></el-input>
        </el-form-item>

        <el-form-item label="上课时间">
          <el-input v-model="addCoursePlanCmd.time"></el-input>
        </el-form-item>

        <el-form-item label="上课地点">
          <el-input v-model="addCoursePlanCmd.loc"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
      <el-button @click="addCoursePlanCmd.dialogVisible = false">取消</el-button>
      <el-button type="primary" @click="addCoursePlanHandler">保存</el-button>
    </span>
    </el-dialog>

  </div>
</template>

<script setup>
// 数据区
import {addCoursePlanApi, queryCoursePlanApi} from "../api/CoursePlanApi.js";
import {ElMessage} from "element-plus";

const coursePlanTable = ref([
  // {
  //   course_id: 1,
  //   semester: 20251,
  //   time: "每周一、三上午10:00",
  //   loc: "B001",
  //   courseCode: {
  //     course_code_id: 1,
  //     course_name: "数学",
  //     type: "必修",
  //     credit: 5
  //   },
  //   teacherInfoVo: {
  //     teacher_id: 2000,
  //     username: "小熊"
  //   },
  //   classInfo: {
  //     class_id: 1,
  //     college: "信息工程学院",
  //     major: "软件工程",
  //     grade: 1,
  //     year: 4
  //   }
  // },
])

/**
 * 查询课程信息
 */
const queryCoursePlanCmd = ref({
  class_id: null,
  semester: null,
  student_id: null,
})
const queryCoursePlanHandler = async () => {
  const resp = await queryCoursePlanApi(toRaw(queryCoursePlanCmd.value))
  coursePlanTable.value = resp.data
}

/**
 * 添加课程安排
 */
const addCoursePlanCmd = ref({
  dialogVisible: false,
  course_code_id: null,
  semester: null,
  teacher_id: null,
  class_id: null,
  time: null,
  loc: null
})
const addCoursePlanHandler = async () => {
  await addCoursePlanApi(toRaw(addCoursePlanCmd.value))
  ElMessage.success("添加成功")
}

onMounted(async () => {
  await queryCoursePlanHandler()
})


</script>

<style lang="scss" scoped>
#course-plan {
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
