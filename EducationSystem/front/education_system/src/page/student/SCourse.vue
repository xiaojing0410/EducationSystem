<template>
  <div id="course">
    <!-- 查询区(学生应该可以根据 学期 查询课程信息)  -->
<!--    <div class="query">-->
<!--      <el-input class="ipt" v-model="queryScoreReq.semester" placeholder="请输入学期号" />-->
<!--      <el-button class="btn" type="primary" plain>查询</el-button>-->
<!--    </div>-->

    <!--  课程列表  -->
    <div class="list">
      <el-table :data="courseList" style="width: 100%" height="600">
        <el-table-column prop="id" label="课程id" />
        <el-table-column prop="course_name" label="课程名字" />
        <el-table-column prop="teacher_id" label="教师id" />
        <el-table-column prop="class_id" label="班级id" />
      </el-table>
    </div>
  </div>
</template>

<script setup>
import { course_info_cmd } from "@/composables/course/course-cmd.js"
import { onBeforeMount } from "vue";

// 课程列表
const courseList = ref([
  {
    id: 4,
    course_name: "政治",
    teacher_id: 1000,
    class_id: 1
  }
])

onBeforeMount(()=> {
  // 查询课程
  const course_info = async () => {
    courseList.value = await course_info_cmd()
  }
  course_info()
})


</script>

<style lang="scss" scoped>
#course {
  width: 1200px;
  margin: 0 auto;
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