<template>
  <div id="attendance">
    <!-- 操作区 -->
    <div class="actions" >
      <div class="row">
        <el-input v-if="isTeacher(auth) || isAdmin(auth)" class="ipt" v-model="queryAttendanceCmd.course_id" placeholder="请输入课程id" clearable/>
        <el-input class="ipt" v-model="queryAttendanceCmd.semester" placeholder="请输入学期" clearable/>
        <el-input v-if="isTeacher(auth) || isAdmin(auth)" class="ipt" v-model="queryAttendanceCmd.student_id" placeholder="请输入学生id" clearable/>

        <el-button @click="queryAttendanceHandler" class="btn" type="primary">查询</el-button>
        <el-button
            v-if="isTeacher(auth) || isAdmin(auth)"
            style="width: 300px"
            class="btn"
            type="warning"
            @click="startAttendanceCmd.dialogVisible = true">
          开始考勤
        </el-button>
      </div>
    </div>

    <!-- 数据区 -->
    <div class="data">
      <el-table :data="courseTable" style="width: 100%">
        <!-- Attendance -->
        <el-table-column label="考勤 ID" prop="attendance.attendance_id"></el-table-column>
        <el-table-column label="课程 ID" prop="attendance.course_id"></el-table-column>
        <el-table-column label="学生 ID" prop="attendance.student_id"></el-table-column>
        <el-table-column label="出勤次数" prop="attendance.at_count"></el-table-column>

        <!-- Student Info -->
        <el-table-column label="学生 ID" prop="student_id"></el-table-column>
        <el-table-column label="学生姓名" prop="username"></el-table-column>

        <!-- Class Info -->
        <el-table-column label="班级 ID" prop="classInfo.class_id"></el-table-column>
        <el-table-column label="学院" prop="classInfo.college"></el-table-column>
        <el-table-column label="专业" prop="classInfo.major"></el-table-column>
        <el-table-column label="年级" prop="classInfo.grade"></el-table-column>
        <el-table-column label="学年" prop="classInfo.year"></el-table-column>

        <!-- Course Info -->
        <el-table-column label="课程 ID" prop="course.course_id"></el-table-column>
        <el-table-column label="课程代码" prop="course.course_code_id"></el-table-column>
        <el-table-column label="学期" prop="course.semester"></el-table-column>
        <el-table-column label="教师 ID" prop="course.teacher_id"></el-table-column>
        <el-table-column label="班级 ID" prop="course.class_id"></el-table-column>
        <el-table-column label="上课时间" prop="course.time"></el-table-column>
        <el-table-column label="上课地点" prop="course.loc"></el-table-column>
        <el-table-column label="出勤总数" prop="course.at_total"></el-table-column>
        <el-table-column label="操作" v-if="isShowOpt">
          <template #default="{ row }">
            <el-button
                v-if="!row.noShowConfirmOpt"
                type="success"
                plain
                @click="updateAttendanceHandler(row.attendance.attendance_id)"
            >
              确认出勤
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog v-model="startAttendanceCmd.dialogVisible" title="开始考勤" width="500px">
      <el-form :model="startAttendanceCmd" label-width="100px">
        <el-form-item label="课程名称">
          <el-input v-model="startAttendanceCmd.course_id" placeholder="请输入课程id" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="startAttendanceCmd.dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="startAttendanceHandler">提交</el-button>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
// 数据区
import {addAttendanceApi, queryAttendanceApi, updateAttendanceApi} from "../api/AttendanceApi.js";
import {ElMessage} from "element-plus";
import {isAdmin, isStudent, isTeacher} from "../infra/tools/authTools.js";
import {useUserInfoStore} from "../infra/store/userinfoStore.js";

const userinfo = useUserInfoStore()
const auth = userinfo.getAuth()

// 是否展示操作栏(只有开始考勤之后才会出现操作栏)
const isShowOpt = ref(false)
const courseTable = ref([
  // {
    // noShowConfirmOpt: false, // 是否展示该条数据的确认出勤
  //   attendance: {
  //     attendance_id: 2,
  //     course_id: 1,
  //     student_id: 10000,
  //     at_count: 1
  //   },
  //   courseCode: {
  //     course_code_id: 1,
  //     course_name: "数学",
  //     type: null,
  //     credit: null
  //   },
  //   student_id: 10000,
  //   username: "龙洋静",
  //   classInfo: {
  //     class_id: 1,
  //     college: "信息工程学院",
  //     major: "软件工程",
  //     grade: 1,
  //     year: 4
  //   },
  //   course: {
  //     course_id: 1,
  //     course_code_id: 1,
  //     semester: 20251,
  //     teacher_id: 2000,
  //     class_id: 1,
  //     time: null,
  //     loc: null,
  //     at_total: null
  //   }
  // }
])

/**
 * 查询考勤信息
 */
const queryAttendanceCmd = ref({
  semester: null,
  course_id: null,
  student_id: null,
})
const queryAttendanceHandler = async () => {
  const resp = await queryAttendanceApi(toRaw(queryAttendanceCmd.value))
  isShowOpt.value = false
  courseTable.value = resp.data
}

/**
 * 开始考勤
 */
const startAttendanceCmd = ref({
  dialogVisible: false,
  course_id: null,
})
const startAttendanceHandler = async () => {
  const resp = await addAttendanceApi(toRaw(startAttendanceCmd.value))
  // 开始考勤之后，按照开始考勤的课程id来查询考勤信息
  queryAttendanceCmd.value.course_id = startAttendanceCmd.value.course_id
  await queryAttendanceHandler()

  isShowOpt.value = true
  startAttendanceCmd.value = false
  ElMessage.success("添加考勤成功")
}

/**
 * 确认出勤
 */
const updateAttendanceHandler = async (id) => {
  const resp = await updateAttendanceApi({ attendance_id: id })
  // 每次开始考勤之后每条数据只能进行一次确认出勤
  const course = courseTable.value.find(item => item.attendance?.attendance_id === id);
  if (course) {
    course.noShowConfirmOpt = true;
  }
  // 更新数据(这里不能直接通过查询 handler 来更新，因为后端没有保存状态，因此由前端来处理)
  course.attendance.at_count += 1
  ElMessage.success("确认成功")
}

onMounted(async () => {
  if (isStudent(userinfo.getAuth())) {
    queryAttendanceCmd.value.student_id = userinfo.id
    await queryAttendanceHandler(toRaw(queryAttendanceCmd.value))
  }
})
</script>

<style lang="scss" scoped>
#attendance {
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
