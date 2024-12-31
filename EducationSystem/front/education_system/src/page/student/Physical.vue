<template>
  <div id="score">
    <!-- 查询区  -->
    <div class="query">
      <el-input class="ipt" v-model="queryScoreReq.semester" placeholder="请输入学期号" />
      <el-button class="btn" type="primary" plain>查询</el-button>
    </div>

    <!--  体测成绩列表  -->
    <div class="list">
      <el-table :data="scoreList" style="width: 100%" height="600">
        <el-table-column prop="semester" label="学期" />
        <el-table-column prop="sprint" label="冲刺" />
        <el-table-column prop="run" label="跑步" />
        <el-table-column prop="sit_up" label="立定跳远" />
        <el-table-column prop="jump" label="跳高" />
        <el-table-column prop="height" label="升高" />
        <el-table-column prop="weight" label="体重" />
      </el-table>
    </div>
  </div>
</template>

<script setup>

//成绩查询参数
const queryScoreReq = ref({
  semester: null
})

// 体测成绩列表
const scoreList = ref([
  {
    // student_id: 1, 查询自己的体育成绩，这个就没必要展示了
    semester: 1,
    sprint: 9.8,
    run: 3.55,
    sit_up: 38,
    jump: 186,
    height: 150,
    weight: 40
  }
])

</script>

<style lang="scss" scoped>
#score {
  width: 1200px;
  margin: 0 auto;
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