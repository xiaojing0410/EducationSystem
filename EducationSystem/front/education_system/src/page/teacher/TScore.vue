<template>
  <div id="score">
    <!--  新增成绩区  -->
    <div class="add">
      <div class="row">
        <span>课程id</span>
        <el-input class="ipt" />
      </div>

      <div class="row">
        <span>学生id</span>
        <el-input class="ipt" />
      </div>

      <div class="row">
        <span>学期</span>
        <el-input class="ipt" />
      </div>

      <div class="row">
        <span>成绩</span>
        <el-input class="ipt" />
      </div>
      <el-button type="primary" plain>批量新增/新增 学生成绩</el-button>
    </div>

    <hr>

    <!--  成绩查询区  -->
    <div class="query">
      <el-input class="ipt" v-model="queryScoreReq.courseName" placeholder="请输入课程名称" />
      <el-input class="ipt" v-model="queryScoreReq.semester" placeholder="请输入课程id" />
      <el-button class="btn" type="primary" plain>查询</el-button>
    </div>

    <!--  成绩列表  -->
    <div class="list">
      <el-table :data="scoreList" style="width: 100%" height="600">
        <el-table-column prop="id" label="成绩id" />

        <el-table-column prop="course_id" label="课程id" />

        <el-table-column label="成绩">
          <template v-slot="scope">
            <el-input v-model="scope.row.score" size="small" />
          </template>
        </el-table-column>

        <el-table-column prop="semester" label="学期" width="200" />

        <el-table-column label="操作" width="180">
          <template v-slot="scope">
            <el-button size="small" type="primary" @click="editScore(scope.row)">修改</el-button>
            <el-button size="small" type="danger" @click="deleteScore(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script setup>

// 成绩单查询参数
const queryScoreReq = ref({
  courseName: "",
  semester: null
})

// 成绩列表
const scoreList = ref([
    {
      id: 1,
      course_id: 1,
      score: 90.0,
      semester: 1
    }
])

</script>

<style lang="scss" scoped>
#score {
  width: 1200px;
  margin: 0 auto;
}

#score .add {
  width: 1200px;
  padding: 20px;
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