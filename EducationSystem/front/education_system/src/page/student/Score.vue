<template>
  <div id="score">
    <!--  学业预警 & 个性化推荐  -->
    <el-descriptions
        title="学业预警"
        direction="vertical"
        border
        style="margin-top: 20px"
        class="warn"
    >
      <el-descriptions-item label="警告1">
        {{ scoreWarn[1] ?? '暂无' }}
      </el-descriptions-item>

      <el-descriptions-item label="警告2">
       {{ scoreWarn[2] ?? '暂无' }}
      </el-descriptions-item>

      <el-descriptions-item label="个性化推荐">
        {{ scoreSuggest[1] ?? '暂无' }}
      </el-descriptions-item>

    </el-descriptions>

    <hr>

    <!--  成绩列表  -->
    <div class="list">
      <el-table :data="scoreList" style="width: 100%" height="600">
        <el-table-column prop="id" label="成绩id" />
        <el-table-column prop="course_id" label="课程id" />
        <el-table-column prop="score" label="成绩" />
        <el-table-column prop="semester" label="学期" width="200" />
      </el-table>
    </div>
  </div>
</template>

<script setup>

// 学业预警
const scoreWarn = ref({
  1: "总平均分低于60",
  // 2: "及格率低于50%"
})

// 个性化推荐
const scoreSuggest = ref({
  1: "需要及时联系老师补考或重修了哦"
})

// 成绩列表
const scoreList = ref([
    {
      id: 1,
      course_id: 1,
      score: 90.0,
      semester: 1
    }
])

</script>

<style lang="scss" scoped>
#score {
  width: 1200px;
  margin: 0 auto;
}

#score .warn {
  margin: 20px auto;
}

#score .list {
  margin: 20px auto;
}

</style>