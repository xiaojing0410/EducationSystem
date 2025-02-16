<template>
  <div id="course">
    <!--  新增课程区  -->
    <div class="add">
      <div class="row" >
        <span>课程名称</span>
        <el-input class="ipt" v-model="addCourseReq.course_name"/>
      </div>

      <div class="row">
        <span>班级</span>
        <el-input class="ipt" v-model="addCourseReq.class_id"/>
      </div>

      <div class="row">
        <span>教师编号</span>
        <el-input class="ipt" v-model="addCourseReq.teacher_id" />
      </div>

      <el-button type="primary" plain @click="add_course_cmd(addCourseReq.course_name, addCourseReq.teacher_id, addCourseReq.class_id)">新增课程</el-button>
    </div>

    <hr>

    <!--  课程查询区  -->
    <div class="query">
      <el-input class="ipt" v-model="queryCourseReq.class_id" placeholder="请输入班级id" />
      <el-button class="btn" type="primary" plain @click="get_course_list(queryCourseReq.class_id)">查询</el-button>
    </div>

    <!--  课程列表  -->
    <div class="list">
      <el-table :data="courseList" style="width: 100%" height="600">
        <el-table-column prop="id" label="课程号" />
        <el-table-column prop="course_name" label="课程" />
        <el-table-column prop="teacher_id" label="教师编号" />
        <el-table-column prop="class_id" label="班级" />
      </el-table>
    </div>
  </div>
</template>

<script setup>
import { course_info_cmd, add_course_cmd } from "@/composables/course/course-cmd"

// 新增课程参数
const addCourseReq = ref({
  course_name: "",
  teacher_id: null,
  class_id: null
})

// 课程查询参数
const queryCourseReq = ref({
  class_id: "",
})

// 成绩列表
const courseList = ref()

onBeforeMount(()=> {
  get_course_list()
})
// 查询学生基本信息
const get_course_list = async () => {
  if(queryCourseReq.value.class_id == ""){
    courseList.value = await course_info_cmd(1)
  } else {
    courseList.value = await course_info_cmd(queryCourseReq.value.class_id)
  }
  
}
</script>

<style lang="scss" scoped>
#course {
  width: 1200px;
  margin: 0 auto;
}

#course .add {
  width: 1200px;
  padding: 20px;
  .row{
    margin-bottom: 10px;
    span{
      display: block;  /* 确保 span 元素占据一整行 */
      margin-bottom: 10px;  /* 控制 span 和 input 之间的间距 */
    }
  }
}

#course .list {
  margin: 20px auto;
}

#course .query {
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