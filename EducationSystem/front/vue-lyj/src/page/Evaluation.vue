<template>
  <div id="evaluation">
    <el-card class="evaluation-card">
      <h2>学生评测</h2>
      <hr style="margin-bottom: 20px;">
      <el-form>
        <el-form-item label="学生 ID" v-if="isAdmin(userinfo.getAuth()) || isTeacher(userinfo.getAuth())">
          <el-autocomplete clearable
            v-model="queryEvaluationCmd.student_id" class="ipt" placeholder="请输入学号"
            :fetch-suggestions="queryUserBasicinfoHandler"
            @select="handleSelectStudent"
            :hide-loading="true" style="width: 200px; margin-right: 20px;"
          />
          <el-input  v-model="mainTable.user.username" style="width: 200px" disabled/>
          <el-button  @click="queryEvaluationHandler" type="primary">查询</el-button>
        </el-form-item>
      </el-form>

      <el-table :data="mainTable.evaluationSub" border>
        <el-table-column label="指标">
          <template #default="{ row }">
            <div>
              <!-- 根据不同的type显示不同的指标名称 -->
              <div v-if="row.type === 1">荣誉信息</div>
              <div v-if="row.type === 2">考勤信息</div>
              <div v-if="row.type === 3">成绩信息</div>
              <div v-if="row.type === 4">体测信息</div>
            </div>
          </template>
        </el-table-column>

        <el-table-column label="评分">
          <template #default="{ row }">
            <el-rate v-model="row.score" :max="5" show-score :score-template="scoreLabels[row.score]" />
          </template>
        </el-table-column>

        <el-table-column label="AI 建议">
          <template #default="{ row }">
            <el-button color="#626aef" plain @click="aiFeedbackHandler(row.type)">获取 AI 建议</el-button>
            <p v-if="row.ai_suggestion">AI 建议: {{ row.ai_suggestion }}</p>
          </template>
        </el-table-column>
      </el-table>

      <el-divider />

      <el-row :gutter="20">
        <!-- 左侧：教师反馈输入 -->
        <el-col :span="12">
          <el-form>
            <el-form-item label="教师反馈">
              <el-input v-model="mainTable.evaluation.feedback" type="textarea" rows="6" placeholder="请输入教师反馈"></el-input>
            </el-form-item>
          </el-form>
        </el-col>

        <!-- 右侧：AI 反馈建议 -->
        <el-col :span="12">
          <div class="ai-feedback-box">
            <p v-if="mainTable.evaluation.ai_feedback" class="ai-feedback-text">
              <strong>AI 反馈建议:</strong> {{ mainTable.evaluation.ai_feedback }}
            </p>
            <p v-else class="ai-feedback-placeholder">点击按钮以获取 AI 反馈建议...</p>
          </div>
          <el-button color="#626aef" plain @click="aiFeedbackHandler(0)" style="width: 100%">获取 AI 反馈建议</el-button>
        </el-col>
      </el-row>

      <el-button v-if="!isStudent(auth)" class="save-btn" type="primary" @click="addEvaluationHandler">保存反馈</el-button>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import {ElLoading, ElMessage} from 'element-plus';
import {addEvaluationApi, aiFeedbackApi, queryEvaluationApi} from "../api/EvaluationApi.js";
import {useUserInfoStore} from "../infra/store/userinfoStore.js";
import {isAdmin, isStudent, isTeacher} from "../infra/tools/authTools.js";
import {queryUserinfoApi, queryUserBasicinfoApi} from "../api/UserinfoApi.js";
const userinfo = useUserInfoStore()
const auth = userinfo.getAuth()
const mainTable = ref({
  user: {
    id: null,
    username: null,
    teacher_id: null
  },
  evaluation: {
    evaluation_id: null,
    student_id: null,
    feedback: "",
    ai_feedback: ""
  },
  evaluationSub: []
})
const scoreLabels = ['很差', '差', '一般', '良好', '优秀', '非常优秀'];

/**
 * 查询反馈信息
 */
const queryEvaluationCmd = ref({
  student_id: null,
})
const queryEvaluationHandler = async () => {
  const resp = await queryEvaluationApi(toRaw(queryEvaluationCmd.value));
  // 获取响应数据中的 evaluationSub
  let evaluationSub = resp.data.evaluationSub || [];
  // 获取所有可能的 type
  const allTypes = [1, 2, 3, 4];
  // 提取现有的 type
  const existingTypes = evaluationSub.map(sub => sub.type);
  // 找出缺少的 type
  const missingTypes = allTypes.filter(type => !existingTypes.includes(type));
  // 对于缺少的类型，补充默认值
  missingTypes.forEach(type => {
    evaluationSub.push({
      evaluation_sub_id: null,
      type: type,
      student_id: 10000, // 根据实际情况设置学生ID
      score: 0,
      ai_suggestion: null,
    });
  });
  evaluationSub = evaluationSub.sort((a, b) => a.type - b.type);
  resp.data.evaluationSub = evaluationSub;
  // 这里前端做了很多牺牲。。。
  mainTable.value = resp.data;
  if (mainTable.value.evaluation == null) {
    mainTable.value.evaluation = {
      evaluation_id: null,
          student_id: null,
          feedback: "",
          ai_feedback: ""
    }
  }
}

/**
 * 获取 ai 反馈
 */
const aiFeedbackHandler = async (type) => {
  const loading = ElLoading.service({
    text: '评测中...',  // 加载时的提示文本
    background: 'rgba(0, 0, 0, 0.7)'  // 背景透明度
  });

  try {
    const resp = await aiFeedbackApi({
      student_id: mainTable.value.user.id,
      type: type
    });
    if (type === 0) { // 0 的情况比较特殊，这里隐式约定为整体评价反馈
      mainTable.value.evaluation.ai_feedback = resp.data
      return
    }
    const target = mainTable.value.evaluationSub.find(item => item.type === type);
    if (target) {
      target.ai_suggestion = resp.data;
    }
  } finally {
    loading.close();
  }
}

/**
 * 添加评价信息
 */
const addEvaluationHandler = async () => {
  const resp = await addEvaluationApi({
    student_id: mainTable.value.user.id,
    feedback: mainTable.value.evaluation.feedback,
    ai_feedback: mainTable.value.evaluation.ai_feedback,
    addEvaluationSubs: mainTable.value.evaluationSub
  })
  ElMessage.success("保存成功")
}
// 学生 - 查询
const queryUserBasicinfoHandler = async (query, cb) => {
  if (!query) {
    cb([])
    return
  }
  const resp = await queryUserBasicinfoApi({
    info: queryEvaluationCmd.value.student_id,
    type: 3
  })
  // 判断返回的数据是否存在
  if (resp && resp.data) {
    const suggestions = resp.data.map(item => ({
      value: item, // 显示 user_id 字段
      label: item,
    }))
    cb(suggestions)
  } else {
    cb([])
  }
}
const handleSelectStudent = (item) => {
  queryEvaluationCmd.value.student_id = item.label.split('|').map(s => s.trim())[1]
  console.log('Selected:', queryEvaluationCmd.value.student_id)
}

onMounted(async () => {
  // 如果是学生，就直接查询自己的
  if (isStudent(userinfo.auth)) {
    queryEvaluationCmd.value.student_id = userinfo.id
    await queryEvaluationHandler()
  }
})
</script>

<style lang="scss" scoped>
#evaluation{
  height: 100%;
  width: 100%;
  max-height: 100vh;
  overflow-y: auto;
}
.ai-feedback-box {
  background: #f8f9fa;
  border-radius: 8px;
  padding: 15px;
  min-height: 120px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  font-size: 16px;
  line-height: 1.6;
  color: #333;
  
}

.ai-feedback-text {
  color: #222;
}

.ai-feedback-placeholder {
  color: #888;
  font-style: italic;
}

</style>