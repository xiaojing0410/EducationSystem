<template>
  <div id="physical">
    <!--  新增体测成绩区  -->
    <div class="add">
      <div class="row">
        <span>学生id</span>
        <el-input v-model="addScoreReq.student_id" class="ipt" />
      </div>

      <div class="row">
        <span>学期</span>
        <el-input v-model="addScoreReq.semester" class="ipt" />
      </div>

      <div class="row">
        <span>冲刺</span>
        <el-input v-model="addScoreReq.sprint" class="ipt" />
      </div>

      <div class="row">
        <span>立定跳远</span>
        <el-input v-model="addScoreReq.sit_up" class="ipt" />
      </div>

      <div class="row">
        <span>跳高</span>
        <el-input v-model="addScoreReq.jump" class="ipt" />
      </div>

      <div class="row">
        <span>身高</span>
        <el-input v-model="addScoreReq.height" class="ipt" />
      </div>

      <div class="row">
        <span>体重</span>
        <el-input v-model="addScoreReq.weight" class="ipt" />
      </div>
      <el-button type="primary" plain>批量新增/新增 学生体测成绩</el-button>
    </div>

    <hr>

    <!--  成绩查询区  -->
    <div class="query">
      <el-input class="ipt" v-model="queryScoreReq.student_id" placeholder="请输入学生id" />
      <el-input class="ipt" v-model="queryScoreReq.class_id" placeholder="请输入班级id" />
      <el-input class="ipt" v-model="queryScoreReq.semester" placeholder="请输入学期" />
      <el-button class="btn" type="primary" plain>查询</el-button>
    </div>

    <!--  成绩列表  -->
    <div class="list">
      <el-table :data="scoreList" style="width: 100%" height="600">
        <el-table-column prop="student_id" label="学生id" />
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
// 添加体测成绩参数
const addScoreReq = ref({
  student_id: null,
  semester: null,
  sprint: null,
  run: null,
  sit_up: null,
  jump: null,
  height: null,
  weight: null
})

// 成绩单查询参数
const queryScoreReq = ref({
  student_id: "",
  class_id: null,
  semester: null,
})

// 成绩列表
const scoreList = ref([
    {
      student_id: 1,
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
#physical {
  width: 1200px;
  margin: 0 auto;
}

#physical .add {
  width: 1200px;
  padding: 20px;

  .row {
    display: flex;
    justify-content: left;

    span {
      width: 80px;
    }
  }
}

#physical .list {
  margin: 20px auto;
}

#physical .query {
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