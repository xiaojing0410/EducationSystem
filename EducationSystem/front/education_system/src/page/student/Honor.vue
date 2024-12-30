<template>
  <div id="honor">
    <!-- 操作区  -->
    <div class="opt">
      <div class="row">
        <span>荣誉名称</span>
        <el-input class="ipt" type="text" v-model="applyHonorReq.honor_name" />
      </div>

      <div class="row">
        <span>获奖日期</span>
        <el-input class="ipt" type="text" v-model="applyHonorReq.award_date" />
      </div>

      <div class="row">
        <span>获奖类型</span>
        <el-input class="ipt" type="text" v-model="applyHonorReq.type" />
      </div>
      <el-button type="primary" plain>申请荣誉</el-button>
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

        <el-table-column label="操作">
          <template v-slot="scope">
            <el-button @click="removeHonor(scope.row.id)" type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script setup>

// 申请荣誉请求
import { ElMessage } from "element-plus";

const applyHonorReq = ref({
  honor_name: "",
  award_date: "",
  type: null
})

// 荣誉列表
const honorList = ref([
  {
    id: 8,
    student_id: 1,
    honor_name: "心理证书",
    award_date: "2024-12-26",
    type: 2,
    state: null
  }
])

// 删除荣誉
const removeHonor = (id) => {
  // TODO
  ElMessage.success("删除成功")
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

</style>