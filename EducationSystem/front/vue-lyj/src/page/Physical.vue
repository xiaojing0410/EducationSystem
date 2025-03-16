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
        <el-table-column prop="physical.physical_id" label="体测id" />
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
        <el-table-column label="操作" >
          <template #default="{ row }">
            <el-button type="warning" @click="openUpdatePhysicalDialog(row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog v-model="updatePhysicalCmd.isShow" title="修改体测成绩" width="500px">
      <el-form :model="updatePhysicalCmd" label-width="80px">
        <el-form-item label="体测id">
          <el-input v-model.number="updatePhysicalCmd.physical_id" disabled />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="updatePhysicalCmd.state">
            <el-option :value="0" label="未测试" />
            <el-option :value="1" label="已测试" />
          </el-select>
        </el-form-item>
        <el-form-item label="短跑 (s)">
          <el-input-number v-model="updatePhysicalCmd.sprint" :min="5" :max="20" :step="0.1" />
        </el-form-item>
        <el-form-item label="长跑 (min)">
          <el-input-number v-model="updatePhysicalCmd.run" :min="2" :max="10" :step="0.01" />
        </el-form-item>
        <el-form-item label="仰卧起坐 (次)">
          <el-input-number v-model="updatePhysicalCmd.sit_up" :min="0" :max="100" />
        </el-form-item>
        <el-form-item label="跳远 (cm)">
          <el-input-number v-model="updatePhysicalCmd.jump" :min="100" :max="300" />
        </el-form-item>
        <el-form-item label="身高 (cm)">
          <el-input-number v-model="updatePhysicalCmd.height" :min="100" :max="250" />
        </el-form-item>
        <el-form-item label="体重 (kg)">
          <el-input-number v-model="updatePhysicalCmd.weight" :min="20" :max="200" :step="0.1" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="updatePhysicalCmd.isShow = false">取消</el-button>
        <el-button type="primary" @click="">保存</el-button>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
// 数据区
const physicalTable = ref([
  {
    student_id: 10000,
    username: "龙洋静",
    physical: {
      physical_id: 1,
      student_id: 10000,
      year: 1,
      state: 1, // 0表示未测试，1表示已测试
      sprint: 9.8,
      run: 3.55,
      sit_up: 38,
      jump: 186,
      height: 150,
      weight: 40
    },
    classInfo: {
      class_id: 1,
      college: "信息工程学院",
      major: "软件工程",
      grade: 1,
      year: 4
    }
  }
])

const updatePhysicalCmd = ref({
  isShow: false,
  physical_id: null,
  sprint: null,
  run: null,
  sit_up: null,
  jump: null,
  height: null,
  weight: null,
})
const openUpdatePhysicalDialog = (row) => {
  updatePhysicalCmd.value = {
    isShow: true,
    physical_id: row.physical.physical_id,
    sprint: row.physical.sprint,
    run: row.physical.run,
    sit_up: row.physical.sit_up,
    jump: row.physical.jump,
    height: row.physical.height,
    weight: row.physical.weight,
  }
}

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
