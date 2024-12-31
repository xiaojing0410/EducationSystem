<template>
  <div id="course">
    <!--  新增课程区  -->
    <div class="add">
      <div class="row">
        <span>课程id</span>
        <el-input class="ipt" v-model="addCourseReq.class_id"/>
      </div>

      <div class="row">
        <span>教师id</span>
        <el-input class="ipt" v-model="addCourseReq.teacher_id" />
      </div>

      <div class="row" >
        <span>课程名称</span>
        <el-input class="ipt" v-model="addCourseReq.course_name"/>
      </div>

      <el-button type="primary" plain>新增课程</el-button>
    </div>

    <hr>

    <!--  课程查询区  -->
    <div class="query">
      <el-input class="ipt" v-model="queryCourseReq.class_id" placeholder="请输入班级id" />
      <el-button class="btn" type="primary" plain>查询</el-button>
    </div>

    <!--  课程列表  -->
    <div class="list">
      <el-table :data="courseList" style="width: 100%" height="600">
        <el-table-column prop="id" label="课程id" />
        <el-table-column prop="course_name" label="课程名称" />
        <el-table-column prop="teacher_id" label="教师id" />
        <el-table-column prop="class_id" label="课程id" />
      </el-table>
    </div>
  </div>
</template>

<script setup>
// 新增课程参数
const addCourseReq = ref({
  course_name: "",
  teacher_id: null,
  class_id: null
})

// 课程查询参数
const queryCourseReq = ref({
  class_id: null,
})

// 成绩列表
const courseList = ref([
    {
      id: 4,
      course_name: "政治",
      teacher_id: 1000,
      class_id: 1
    }
])

</script>

<style lang="scss" scoped>
#course {
  width: 1200px;
  margin: 0 auto;
}

#course .add {
  width: 1200px;
  padding: 20px;
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