<template>
  <div id="score">
    <!-- 查询区  -->
    <div class="query">
      <el-input class="ipt" v-model="queryScoreReq.semester" placeholder="请输入学期号" />
      <el-button class="btn" type="primary" plain @click="score_by_semester(queryScoreReq.semester)">查询</el-button>
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
import { physical_info_cmd } from "@/composables/physical/physical-cmd.js"
import { onBeforeMount } from "vue";
//成绩查询参数
const queryScoreReq = ref({
  semester: null
})

// 体测成绩列表
const scoreList = ref()

onBeforeMount(()=> {
  // 查询体测成绩
  const score_list = async () => {
    scoreList.value = await physical_info_cmd()
  }
  score_list()
})

// 按学期查询体测成绩
const score_by_semester = async (semester) => {
  scoreList.value = await physical_info_cmd(null,null,semester)
}
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