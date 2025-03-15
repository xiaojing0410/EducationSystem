<template>
  <div id="honor">
    <!-- 操作区 -->
    <div class="actions" >
      <div class="row">
        <el-input class="ipt" placeholder="请输入学生id" />
        <el-input class="ipt" placeholder="请输入班级id" />
        <el-button class="btn" type="primary">查询</el-button>
      </div>
    </div>

    <!-- 数据区 -->
    <div class="data">
      <el-table :data="honorTable" border style="width: 100%">
        <el-table-column prop="student_id" label="学生id" />
        <el-table-column prop="username" label="用户名" />
        <el-table-column prop="honor.honor_id" label="荣誉id" />
        <el-table-column prop="honor.honor_name" label="荣誉颁发时间" />
        <el-table-column prop="honor.type" label="荣誉类型">
          <template #default="{ row }">
            <el-tag v-if="row.honor.type === 0" type="info">其他</el-tag>
            <el-tag v-else-if="row.honor.type === 1" type="success">奖学金</el-tag>
            <el-tag v-else-if="row.honor.type === 2" type="warning">证书</el-tag>
            <el-tag v-else type="danger">未知</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="honor.state" label="状态">
          <template #default="{ row }">
            <el-tag v-if="row.honor.state === 0" type="warning">待审核</el-tag>
            <el-tag v-else-if="row.honor.state === 1" type="success">审核通过</el-tag>
            <el-tag v-else-if="row.honor.state === -1" type="danger">审核不通过</el-tag>
            <el-tag v-else type="info">未知状态</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="classInfo.class_id" label="班级id" />
        <el-table-column prop="classInfo.college" label="学院" />
        <el-table-column prop="classInfo.major" label="专业" />
        <el-table-column prop="classInfo.grade" label="班级" />
        <el-table-column prop="classInfo.year" label="学年" />
      </el-table>

    </div>

  </div>
</template>

<script setup>
// 数据区
const honorTable = ref([
  {
    "student_id": 10000,
    "username": "龙洋静",
    "honor": {
      "honor_id": 1,
      "student_id": 10000,
      "honor_name": "校级奖学金一等奖",
      "award_date": "2025-03-12",
      "type": 1,
      "state": 1
    },
    "classInfo": {
      "class_id": 1,
      "college": "信息工程学院",
      "major": "软件工程",
      "grade": 1,
      "year": 4
    }
  },
])

</script>

<style lang="scss" scoped>
#honor {
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
