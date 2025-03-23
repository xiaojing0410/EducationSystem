<template>
  <div id="recommend">
    <!-- 操作区 -->
    <div class="actions">
      <div class="row">
        <el-input v-model="aiRecommendCmd.student_id" placeholder="请输入学生id"/>
        <el-button
            @click="aiRecommendHandler"
            type="success"
            style="width: 200px"
            :loading="loading"
        >
          生成AI个性化推荐
        </el-button>
      </div>
    </div>

    <!-- 结果展示区 -->
    <div class="data">
      <div class="content" v-loading="loading">
        <template v-if="!loading">
          <div v-html="recommendHtml"></div>
        </template>
        <el-skeleton :rows="3" animated v-else />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, toRaw, computed } from "vue";
import { aiRecommendApi } from "../api/RecommendApi.js";
import { marked } from "marked";

const recommend = ref("请输入学生id，点击生成按钮，即可分析数据为学生生成个性化推荐~");
const aiRecommendCmd = ref({ student_id: null });
const loading = ref(false);

// 解析 Markdown
const recommendHtml = computed(() => marked(recommend.value));

const aiRecommendHandler = async () => {
  if (!aiRecommendCmd.value.student_id) {
    recommend.value = "请输入学生ID！";
    return;
  }

  loading.value = true;
  try {
    const resp = await aiRecommendApi(toRaw(aiRecommendCmd.value));
    recommend.value = resp.data;
  } catch (error) {
    recommend.value = "获取推荐数据失败，请重试！";
  } finally {
    loading.value = false;
  }
};
</script>

<style lang="scss" scoped>
#recommend {
  width: 100%;
  height: 100%;
}

.actions {
  margin-top: 15px
}

.actions .row {
  display: flex;
  justify-content: left;
  align-items: center;
  margin-top: 10px;

  .ipt {
    width: 200px;
    margin-right: 20px;
  }
  .btn {
    width: 100px;
  }
}


.data {
  width: 100%;
  height: 100%;
  margin-top: 20px;
  overflow-y: auto;
  display: flex;
  justify-content: center; /* 居中内容 */

  .content {
    min-height: 300px;
    max-width: 90%; /* 控制最大宽度 */
    min-width: 80%;
    padding: 20px;
    background: linear-gradient(135deg, #ffffff 0%, #c4c4c4 100%); /* 渐变背景 */
    border-radius: 12px;
    font-size: 16px;
    line-height: 1.8;
    color: #222; /* 深色字体，增强可读性 */
    text-align: left; /* 左对齐，提高可读性 */
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08); /* 柔和阴影 */
    word-wrap: break-word;
    word-break: break-word; /* 处理长单词换行 */
    transition: all 0.3s ease-in-out; /* 添加过渡动画 */

    /* 让 Markdown 样式更现代 */
    h1, h2, h3 {
      color: #333;
      font-weight: bold;
      margin-bottom: 10px;
    }

    p {
      margin-bottom: 10px;
    }

    ul, ol {
      padding-left: 20px;
    }

    blockquote {
      border-left: 4px solid #4CAF50; /* 绿色左边框 */
      padding-left: 15px;
      font-style: italic;
      color: #555;
      background: #f0f0f0;
      border-radius: 4px;
    }

    pre {
      background: #2d2d2d;
      color: #f8f8f2;
      padding: 12px;
      border-radius: 6px;
      font-size: 14px;
      overflow-x: auto;
    }

    code {
      background: #f4f4f4;
      padding: 2px 6px;
      border-radius: 4px;
      font-size: 14px;
      color: #d63384;
    }
  }
}

</style>
