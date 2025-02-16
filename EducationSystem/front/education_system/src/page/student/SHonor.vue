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
        <el-input class="ipt" type="text" v-model="applyHonorReq.type" placeholder="奖学金填1，证书填2，其他填0" />
      </div>
      <el-button type="primary" plain @click="apply_honor_cmd(applyHonorReq.honor_name,applyHonorReq.award_date,applyHonorReq.type)">申请荣誉</el-button>
    </div>
<div style="height:20px"></div>
<el-button>你的荣誉列表</el-button>
    <hr>
    <hr>
    <!--  荣誉列表  -->
    <div class="list">
      <el-table :data="honorList" style="width: 100%" height="600">
        <el-table-column prop="id" label="荣誉id" />
        <el-table-column prop="student_id" label="学生id" />
        <el-table-column prop="honor_name" label="荣誉名称" />
        <el-table-column prop="award_date" label="获奖日期" width="200" />
        <el-table-column label="类型">
          <template v-slot="scope">
            <!-- 如果 type 为 1，显示“证书”，否则显示“奖学金” -->
            {{ scope.row.type === 2 ? '证书' : '奖学金' }}
          </template>
        </el-table-column>
        <el-table-column label="状态">
          <template v-slot="scope">
            {{ scope.row.state === 1 ? '通过' : '待审核' }}
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template v-slot="scope">
            <el-button @click="delete_honor_cmd(scope.row.id)" type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script setup>
import { ElMessage } from "element-plus";
import { onBeforeMount } from "vue";
import { student_self_honor_info_cmd, apply_honor_cmd , delete_honor_cmd} from "@/composables/honor/honor-cmd.js"
// 申请荣誉请求
const applyHonorReq = ref({
  honor_name: "",
  award_date: "",
  type: null
})

// 荣誉列表
const honorList = ref()

onBeforeMount(()=> {
  // 查询荣誉
  const student_self_honor_info = async () => {
    honorList.value = await student_self_honor_info_cmd()
  }
  student_self_honor_info()
  // 设置荣誉的默认时间
  applyHonorReq.value.award_date = getCurrentDate()
})


// 获取当前日期
const getCurrentDate = () => {
  const now = new Date();
  
  const year = now.getFullYear();
  const month = String(now.getMonth() + 1).padStart(2, '0'); // 月份是从 0 开始的，所以要加 1
  const day = String(now.getDate()).padStart(2, '0'); // 保证是两位数字
  
  return `${year}-${month}-${day}`; // 格式化为 yyyy-MM-dd
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
#honor .opt .row {
  margin-bottom: 20px; /* 为每一行设置间距 */
}

#honor .opt .row span {
  display: block;  /* 确保 span 元素占据一整行 */
  margin-bottom: 10px;  /* 控制 span 和 input 之间的间距 */
}

</style>