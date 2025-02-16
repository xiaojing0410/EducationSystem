<template>
  <div id="honor">
    <!--  查询区  -->
    <div class="query">
      <el-input class="ipt" v-model="queryHonorReq.studentId" placeholder="请输入学号" />
      <el-input class="ipt" v-model="queryHonorReq.classId" placeholder="请输入班级id" />
      <el-button class="btn" type="primary" plain @click="get_honor_list()">查询</el-button>
    </div>

    <hr>

    <!--  荣誉列表  -->
    <div class="list">
      <el-table :data="honorList" style="width: 100%" height="600">
        <el-table-column prop="id" label="荣誉id" />
        <el-table-column prop="student_id" label="学生id" />
        <el-table-column prop="honor_name" label="荣誉名称" />
        <el-table-column prop="award_date" label="获奖日期" width="200" />
        <el-table-column prop="type" label="类型(1奖学金 2证书 0其他荣誉)" />
        <el-table-column prop="state" label="状态(0待审核 1通过 -1不通过)" />
      </el-table>
    </div>
  </div>
</template>

<script setup>
import { student_honor_info_cmd } from "@/composables/honor/honor-cmd.js"

// 荣誉查询参数
const queryHonorReq = ref({
  studentId: null,
  classId: null,
})

// 荣誉列表
const honorList = ref()

onBeforeMount(()=> {
  // 查询荣誉
  const student_self_honor_info = async () => {
    honorList.value = await student_honor_info_cmd(queryHonorReq.value.studentId, queryHonorReq.value.classId)
  }
  student_self_honor_info()
})

const get_honor_list = async() => {
  honorList.value = await student_honor_info_cmd(queryHonorReq.value.studentId, queryHonorReq.value.classId)
}
</script>

<style lang="scss" scoped>
#honor {
  width: 1200px;
  margin: 0 auto;
}

#honor .opt {
  width: 1200px;
  padding: 20px;
}

#honor .query {
  width: 1200px;
  padding-top: 20px;
  padding-bottom: 20px;
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