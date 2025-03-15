<template>
  <div id="evaluation">
    <el-card class="evaluation-card">
      <h2>学生评测</h2>
      <hr style="margin-bottom: 20px;">
      <el-form>
        <el-form-item label="学生 ID">
          <el-input v-model="studentId" placeholder="请输入学生 ID"></el-input>
        </el-form-item>
      </el-form>
      <el-table :data="criteria" border>
        <el-table-column prop="label" label="指标"></el-table-column>
        <el-table-column label="评分">
          <template #default="{ row, $index }">
            <el-rate v-model="row.score" :max="5" show-score :score-template="scoreLabels[row.score]" />
          </template>
        </el-table-column>
        <el-table-column label="AI 建议">
          <template #default="{ row, $index }">
            <el-button color="#626aef" plain @click="fetchAISuggestion($index)">获取 AI 建议</el-button>
            <p v-if="row.suggestion">AI 建议: {{ row.suggestion }}</p>
          </template>
        </el-table-column>
      </el-table>

      <el-divider />

      <el-form>
        <el-form-item label="教师反馈">
          <el-input v-model="feedback" type="textarea" rows="4" placeholder="请输入教师反馈"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button color="#626aef" plain @click="fetchAIFeedback">获取 AI 反馈建议</el-button>
          <p v-if="feedbackSuggestion">AI 反馈建议: {{ feedbackSuggestion }}</p>
        </el-form-item>
      </el-form>
      <el-button type="primary">保存反馈</el-button>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';

const studentId = ref('');

const criteria = ref([
  { key: 'honor', label: '学生荣誉信息', score: 0, suggestion: '' },
  { key: 'attendance', label: '考勤信息', score: 0, suggestion: '' },
  { key: 'grades', label: '成绩信息', score: 0, suggestion: '' },
  { key: 'physical', label: '体测信息', score: 0, suggestion: '' },
]);

const feedback = ref('');
const feedbackSuggestion = ref('');

const scoreLabels = ['很差', '差', '一般', '良好', '优秀', '非常优秀'];

const fetchAISuggestion = async (index) => {
  try {
    // const response = await axios.get(`/api/ai-score?criteria=${criteria.value[index].key}`);
    // criteria.value[index].suggestion = response.data.suggestion;
    // criteria.value[index].score = response.data.score;
    ElMessage.success('AI 评分建议已更新');
  } catch (error) {
    ElMessage.error('获取 AI 评分建议失败');
  }
};

const fetchAIFeedback = async () => {
  try {
    const response = await axios.get('/api/ai-feedback');
    feedbackSuggestion.value = response.data.suggestion;
    ElMessage.success('AI 反馈建议已更新');
  } catch (error) {
    ElMessage.error('获取 AI 反馈建议失败');
  }
};
</script>

<style lang="scss" scoped>

</style>