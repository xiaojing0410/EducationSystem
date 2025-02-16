<template>
  <div id="score">
    <!--  新增成绩区  -->
    <div class="add">
      <div class="row">
        <span>课程id</span>
        <el-input class="ipt" v-model="addScore.course_id" />
      </div>

      <div class="row">
        <span>学号</span>
        <el-input class="ipt" v-model="addScore.student_id" />
      </div>

      <div class="row">
        <span>学期</span>
        <el-input class="ipt" v-model="addScore.semester" />
      </div>

      <div class="row">
        <span>成绩</span>
        <el-input class="ipt" v-model="addScore.score" />
      </div>
      <el-button type="primary" plain @click="add_grades_cmd(addScore.course_id, addScore.student_id, addScore.semester, addScore.score)">新增学生成绩</el-button>
    </div>

    <hr>

    <!--  成绩查询区  -->
    <div class="query">
      <el-input class="ipt" v-model="queryScoreReq.class_id" placeholder="请输入班级id" />
      <el-input class="ipt" v-model="queryScoreReq.course_name" placeholder="请输入课程名" />
      <el-input class="ipt" v-model="queryScoreReq.student_id" placeholder="请输入学号" />
      <el-input class="ipt" v-model="queryScoreReq.semester" placeholder="请输入学期" />
      <el-button class="btn" type="primary" plain @click="get_score_list()">查询</el-button>
    </div>

    <!--  成绩列表  -->
    <div class="list"  v-if="scoreList && scoreList.grades">
      <el-table :data="scoreList.grades" style="width: 100%">
        <el-table-column prop="student_id" label="学号" />
        <el-table-column prop="id" label="成绩id" />
        <el-table-column prop="course_name" label="课程" />
        <el-table-column prop="semester" label="学期" width="200" />
        <el-table-column label="成绩">
          <template v-slot="scope">
            <el-input v-model="scope.row.score" size="small" />
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180">
          <template v-slot="scope">
            <el-button size="small" type="primary" @click="update_grades_cmd(scope.row.score, scope.row.grades_id)">修改</el-button>
            <el-button size="small" type="danger" @click="delete_grades_cmd(scope.row.grades_id)">删除</el-button>
          </template>
        </el-table-column>
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
import { students_grades_cmd, add_grades_cmd, delete_grades_cmd, update_grades_cmd } from "@/composables/grades/grades-cmd.js"

// 成绩新增参数
const addScore = ref({
  course_id:"",
  student_id:"", 
  semester:"",
  score:""
})
// 成绩单查询参数
const queryScoreReq = ref({
  class_id: "",
  course_name:"",
  student_id:"",
  semester: ""
})

// 成绩列表
const scoreList = ref()

// 查询
const get_score_list = async() => {
  scoreList.value = await students_grades_cmd(queryScoreReq.value.class_id,queryScoreReq.value.course_name,queryScoreReq.value.student_id,queryScoreReq.value.semester)
}

</script>

<style lang="scss" scoped>
#score {
  width: 1200px;
  margin: 0 auto;
}

#score .add {
  width: 1200px;
  padding: 20px;
  .row{
    margin-bottom: 10px;
    span{
      display: block;
      margin-bottom: 10px;
    }
  }
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