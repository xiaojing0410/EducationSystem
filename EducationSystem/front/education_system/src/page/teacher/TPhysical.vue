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
        <span>50m短跑</span>
        <el-input v-model="addScoreReq.sprint" class="ipt" />
      </div>

      <div class="row">
        <span>长跑</span>
        <el-input v-model="addScoreReq.run" class="ipt" />
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
      <el-button type="primary" plain @click="add_physical_cmd(addScoreReq.student_id, addScoreReq.semester, addScoreReq.sprint, addScoreReq.run, addScoreReq.sit_up, addScoreReq.jump, addScoreReq.height, addScoreReq.weight)">新增学生体测成绩</el-button>
    </div>

    <hr>

    <!--  成绩查询区  -->
    <div class="query">
      <el-input class="ipt" v-model="queryScoreReq.student_id" placeholder="请输入学生id" />
      <el-input class="ipt" v-model="queryScoreReq.class_id" placeholder="请输入班级id" />
      <el-input class="ipt" v-model="queryScoreReq.semester" placeholder="请输入学期" />
      <el-button class="btn" type="primary" plain @click="score_by_semester()">查询</el-button>
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
import { physical_info_cmd, add_physical_cmd } from "@/composables/physical/physical-cmd.js"


// 添加体测成绩参数
const addScoreReq = ref({
  student_id: 1,
  semester: 4,
  sprint: 7.89,
  run: 4.10,
  sit_up: 30,
  jump: 180,
  height: 156,
  weight: 45
})

// 成绩单查询参数
const queryScoreReq = ref({
  student_id: "",
  class_id: null,
  semester: null,
})

// 成绩列表
const scoreList = ref()
onBeforeMount(()=> {
  // // 查询体测成绩
  // const score_list = async () => {
  //   scoreList.value = await physical_info_cmd()
  // }
  // score_list()
})
// 查询体测成绩
const score_by_semester = async () => {
  scoreList.value = await physical_info_cmd(queryScoreReq.value.student_id, queryScoreReq.value.class_id, queryScoreReq.value.semester)
}

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
    margin-bottom: 10px;
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