<template>
  <div id="score">
    <!--  学业预警 & 个性化推荐  -->
    <el-descriptions
        title="学业预警"
        direction="vertical"
        border
        style="margin-top: 20px"
        class="warn"
    >
      <el-descriptions-item label="警告1">
        {{ scoreWarn[1] ?? '暂无' }}
      </el-descriptions-item>

      <el-descriptions-item label="警告2">
       {{ scoreWarn[2] ?? '暂无' }}
      </el-descriptions-item>

      <el-descriptions-item label="个性化推荐">
        {{ scoreSuggest[1] ?? '暂无' }}
      </el-descriptions-item>

    </el-descriptions>

    <hr>

    <!--  查询区  -->
    <div class="query">
      <el-input class="ipt" v-model="queryScoreReq.courseName" placeholder="请输入课程名称" />
      <el-input class="ipt" v-model="queryScoreReq.semester" placeholder="请输入学期号" />
      <el-button class="btn" type="primary" plain @click="get_grades(queryScoreReq.courseName, queryScoreReq.semester)">查询</el-button>
    </div>

    <!--  成绩列表  -->
    <div class="list" v-if="scoreList && scoreList.grades">
      <el-table :data="scoreList.grades" style="width: 100%">
        <el-table-column prop="id" label="课程id" />
        <el-table-column prop="course_name" label="课程" />
        <el-table-column prop="semester" label="学期" width="200" />
        <el-table-column prop="score" label="成绩" />
      </el-table>
    </div>
    <hr>
    <!-- 平均分 -->
    <el-button v-if="scoreList && scoreList.avgScore" style="margin-top:20px">
      平均分：{{ scoreList.avgScore }}
    </el-button>
  </div>
</template>

<script setup>
import { student_one_grades_cmd } from "@/composables/grades/grades-cmd.js"
import { onBeforeMount } from "vue";

// 学业预警
const scoreWarn = ref({
  1: "总平均分低于60",
  // 2: "及格率低于50%"
})

// 个性化推荐
const scoreSuggest = ref({
  1: "需要及时联系老师补考或重修了哦"
})

// 成绩单查询参数
const queryScoreReq = ref({
  courseName: "",
  semester: null
})

// 成绩列表
const scoreList = ref()

onBeforeMount(()=> {
  // 查询课程
  const student_one_grades = async () => {
    scoreList.value = await student_one_grades_cmd()
  }
  student_one_grades()
})

// 手动查询成绩
const get_grades = async(courseName, semester) => {
  scoreList.value = await student_one_grades_cmd(courseName, semester)
}
</script>

<style lang="scss" scoped>
#score {
  width: 1200px;
  margin: 0 auto;
}

#score .warn {
  margin: 20px auto;
}
#score .list {
  margin: 20px auto;
}

#score .query {
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