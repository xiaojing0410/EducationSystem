<template>
  <div id="study-warn">
    <!-- 操作区 -->
    <div class="actions" >
      <div class="row">
        <el-input v-model="queryWarnCmd.student_id" class="ipt" placeholder="请输入学生id" />
        <el-button class="btn" type="primary" @click="queryWarnHandler">查询</el-button>
      </div>
    </div>

    <!-- 数据区 -->
    <div class="data">
      <el-table :data="warnTable" border style="width: 100%">
        <el-table-column prop="student_id" label="学号" width="120" />
        <el-table-column prop="username" label="姓名" width="120" />
        <el-table-column label="预警信息">
          <el-table-column prop="precaution.precaution_id" label="预警 ID" width="100" />
          <el-table-column label="类型" width="200">
            <template #default="scope">
              <el-tag type="warning">{{ getPrecautionType(scope.row.precaution.type) }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="内容" width="250">
            <template #default="scope">
              <span>{{ getPrecautionContent(scope.row.precaution.type, scope.row.precaution.content) }}</span>
            </template>
          </el-table-column>
        </el-table-column>
        <el-table-column label="班级信息">
          <el-table-column prop="classInfo.class_id" label="班级 ID" width="100" />
          <el-table-column prop="classInfo.college" label="学院" width="150" />
          <el-table-column prop="classInfo.major" label="专业" width="150" />
          <el-table-column prop="classInfo.grade" label="年级" width="100" />
          <el-table-column prop="classInfo.year" label="学年" width="100" />
        </el-table-column>
      </el-table>
    </div>

  </div>
</template>

<script setup>
import {queryStudyWarnApi} from "../api/StudyWarnApi.js";

const warnTable = ref([
  // {
  //   student_id: 10000,
  //   username: "龙洋静",
  //   precaution: {
  //     precaution_id: 1,
  //     student_id: 10000,
  //     type: 0,
  //     content: 1
  //   },
  //   classInfo: {
  //     class_id: 1,
  //     college: "信息工程学院",
  //     major: "软件工程",
  //     grade: 1,
  //     year: 4
  //   }
  // }
])

/**
 * 显示时进行类型转化
 */
const getPrecautionType = (type) => {
  const types = {
    0: "课程预警",
    1: "学分预警",
    2: "退学预警",
    3: "延毕预警",
    4: "学位预警"
  };
  return types[type] || "未知类型";
};
const getPrecautionContent = (type, content) => {
  const descriptions = {
    0: `课程缺勤超过三分之一（${content} 门考勤不通过）`,
    1: `必修课程不及格达到8学分以上（不及格 ${content} 学分）`,
    2: `不及格课程达到5门及以上（不及格 ${content} 门）`,
    3: `该重修的课程在大四还没学完（重修 ${content} 门）`,
    4: `考试作弊（${content} 次）`
  };
  return descriptions[type] || "未知内容";
};

/**
 * 查询学业预警信息
 */
const queryWarnCmd = ref({
  student_id: null,
})
const queryWarnHandler = async () => {
  const resp = await queryStudyWarnApi({
    student_id: queryWarnCmd.value.student_id
  })
  warnTable.value = resp.data
}

</script>

<style lang="scss" scoped>
#study-warn {
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
}

</style>
