<template>
  <div id="physical">
    <!-- 操作区 -->
    <div class="actions" >
      <div class="row">
        <el-input class="ipt" placeholder="学号" />
        <el-button class="btn" type="primary">查询</el-button>
      </div>

      <div class="row">
        <el-input class="ipt" placeholder="班级id" />
        <el-input class="ipt" placeholder="学年" />
        <el-button class="btn" type="primary">查询</el-button>
      </div>
    </div>

    <!-- 数据区 -->
    <div class="data">
      <el-table :data="physicalTable" border style="width: 100%">
        <el-table-column prop="student_id" label="学号" />
        <el-table-column prop="username" label="姓名" />

        <!-- 体测信息 -->
        <el-table-column label="体测状态">
          <template #default="{ row }">
            <el-tag :type="row.physical.state === 1 ? 'success' : 'warning'">
              {{ row.physical.state === 1 ? "已测试" : "未测试" }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="physical.sprint" label="短跑 (s)" />
        <el-table-column prop="physical.run" label="长跑 (min)" />
        <el-table-column prop="physical.sit_up" label="仰卧起坐 (次)" />
        <el-table-column prop="physical.jump" label="立定跳远 (cm)" />
        <el-table-column prop="physical.height" label="身高 (cm)" />
        <el-table-column prop="physical.weight" label="体重 (kg)" />

        <!-- 班级信息 -->
        <el-table-column prop="classInfo.class_id" label="班级ID" />
        <el-table-column prop="classInfo.college" label="学院" />
        <el-table-column prop="classInfo.major" label="专业" />
        <el-table-column prop="classInfo.grade" label="年级" />
        <el-table-column prop="classInfo.year" label="学制 (年)" />
      </el-table>
    </div>

  </div>
</template>

<script setup>
// 数据区
const physicalTable = ref([
  {
    "student_id": 10000,
    "username": "龙洋静",
    "physical": {
      "student_id": 10000,
      "year": 1,
      "state": 1, // 0表示未测试，1表示已测试
      "sprint": 9.8,
      "run": 3.55,
      "sit_up": 38,
      "jump": 186,
      "height": 150,
      "weight": 40
    },
    "classInfo": {
      "class_id": 1,
      "college": "信息工程学院",
      "major": "软件工程",
      "grade": 1,
      "year": 4
    }
  }
])

</script>

<style lang="scss" scoped>
#physical {
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
