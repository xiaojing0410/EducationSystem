<template>
  <div id="course-plan">
    <!-- 操作区 -->
    <div class="actions" >
      <div class="row">
        <!-- 不填；填学号；填班级id；填学号+学期；填班级id+学期 -->
        <el-autocomplete v-if="isTeacher(userinfo.getAuth()) || isAdmin(userinfo.getAuth()) || isParent(userinfo.getAuth())"  clearable
          v-model="queryCoursePlanCmd.student_id" class="ipt" placeholder="请输入学号"
          :fetch-suggestions="queryUserBasicinfoHandler"
          @select="handleSelectStudent"
          :hide-loading="true" style="width: 200px; margin-right: 20px;"
        />
        <el-autocomplete v-if="isTeacher(userinfo.getAuth()) || isAdmin(userinfo.getAuth())"  clearable
          v-model="queryCoursePlanCmd.class_id" class="ipt" placeholder="请输入班级id"
          :fetch-suggestions="queryClassBasicinfoHandler"
          @select="handleSelectClassId"
          :hide-loading="true" style="width: 200px; margin-right: 20px;"
        />
        
        <el-input v-if="!isParent(userinfo.getAuth())" clearable class="ipt" v-model="queryCoursePlanCmd.semester" placeholder="请输入学期" />
        <el-button @click="queryCoursePlanHandler" class="btn" type="primary">查询</el-button>
        <el-button v-if="isTeacher(userinfo.getAuth()) || isAdmin(userinfo.getAuth())" class="btn" type="warning" @click="addCoursePlanCmd.dialogVisible = true">新增课程安排</el-button>
      </div>
    </div>

    <!-- 数据区 -->
    <div class="data">
      <el-table :data="coursePlanTable" style="width: 100%">
        <el-table-column label="课程安排 ID" prop="course_id" />
        <el-table-column label="学期" prop="semester" />
        <el-table-column label="上课时间" prop="time" />
        <el-table-column label="上课地点" prop="loc" />
        <el-table-column label="课程id" prop="courseCode.course_code_id" />
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
        
        <el-form-item label="课程id">
          <el-autocomplete clearable
            v-model="addCoursePlanCmd.course_code_id" class="ipt"
            :fetch-suggestions="queryCourseCodeinfoHandler"
            @select="handleSelectCourseCode"
            :hide-loading="true"
          />
        </el-form-item>

        <el-form-item label="学期">
          <el-input v-model="addCoursePlanCmd.semester" type="number"></el-input>
        </el-form-item>

        <el-form-item label="教师 ID">
          <el-autocomplete clearable
          v-model="addCoursePlanCmd.teacher_id" class="ipt"
          :fetch-suggestions="queryUserBasicinfoHandler2"
          @select="handleSelectTeacher"
          :hide-loading="true"
        />
        </el-form-item>

        <el-form-item label="班级 ID">
          <el-autocomplete clearable
            v-model="addCoursePlanCmd.class_id" class="ipt"
            :fetch-suggestions="queryClassBasicinfoHandler2"
            @select="handleSelectClassId2"
            :hide-loading="true"
          />
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
import {queryUserinfoApi, queryUserBasicinfoApi} from "../api/UserinfoApi.js";
import {queryClassBasicInfoApi} from "../api/ClassApi.js";
import {addCoursePlanApi, queryCoursePlanApi} from "../api/CoursePlanApi.js";
import {ElMessage} from "element-plus";
import {isAdmin, isParent, isStudent, isTeacher} from "../infra/tools/authTools.js";
import {useUserInfoStore} from "../infra/store/userinfoStore.js";
import {queryCourseApi} from "../api/CourseApi.js";
const userinfo = useUserInfoStore()
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
  addCoursePlanCmd.value.dialogVisible = false
  await queryCoursePlanHandler()
  ElMessage.success("添加成功")
}
/**
 * 自动补全
 */
// 学生 - 查询
const queryUserBasicinfoHandler = async (query, cb) => {
  if (!query) {
    cb([])
    return
  }
  const resp = await queryUserBasicinfoApi({
    info: queryCoursePlanCmd.value.student_id,
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
// 教师 - 新增课程安排信息
const queryUserBasicinfoHandler2 = async (query, cb) => {
  if (!query) {
    cb([])
    return
  }
  const resp = await queryUserBasicinfoApi({
    info: addCoursePlanCmd.value.teacher_id,
    type: 2
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
// 班级 - 查询
const queryClassBasicinfoHandler = async (query, cb) => {
  if (!query) {
    cb([])
    return
  }
  const resp = await queryClassBasicInfoApi({
    info: queryCoursePlanCmd.value.class_id
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
// 班级 - 新增
const queryClassBasicinfoHandler2 = async (query, cb) => {
  if (!query) {
    cb([])
    return
  }
  const resp = await queryClassBasicInfoApi({
    info: addCoursePlanCmd.value.class_id
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
// 课程
const queryCourseCodeinfoHandler = async (query, cb) => {
  if (!query) {
    cb([])
    return
  }
  const resp = await queryCourseApi({
    course_info: addCoursePlanCmd.value.course_code_id
  })
  // 判断返回的数据是否存在
  if (resp && resp.data) {
    const suggestions = resp.data.map(item => ({
      value: item.course_code_id + ' | ' + item.course_name + ' | ' + item.type + ' | 学分' + item.credit, // 显示 user_id 字段
      label: item.course_code_id + ' | ' + item.course_name + ' | ' + item.type + ' | 学分' + item.credit
    }))
    cb(suggestions)
  } else {
    cb([])
  }
}
const handleSelectStudent = (item) => {
  queryCoursePlanCmd.value.student_id = item.label.split('|').map(s => s.trim())[1]
  console.log('Selected:', queryCoursePlanCmd.value.student_id)
}
const handleSelectTeacher = (item) => {
  addCoursePlanCmd.value.teacher_id = item.label.split('|').map(s => s.trim())[1]
  console.log('Selected:', addCoursePlanCmd.value.teacher_id)
}
const handleSelectClassId = (item) => {
  queryCoursePlanCmd.value.class_id = item.label.split('|').map(s => s.trim())[0]
  console.log('Selected:', queryCoursePlanCmd.value.class_id)
}
const handleSelectClassId2 = (item) => {
  addCoursePlanCmd.value.class_id = item.label.split('|').map(s => s.trim())[0]
  console.log('Selected:', addCoursePlanCmd.value.class_id)
}

const handleSelectCourseCode = (item) => {
  addCoursePlanCmd.value.course_code_id = item.label.split('|').map(s => s.trim())[0]
  console.log('Selected:', addCoursePlanCmd.value.course_code_id)
}
onMounted(async () => {
  // await queryCoursePlanHandler()
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


