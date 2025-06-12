<template>
  <div id="physical">
    <!-- 操作区 -->
    <div class="actions" >
      <div class="row">
        <!-- 填学号；填班级+学期 -->
        <el-autocomplete v-if="!isStudent(userinfo.getAuth())" clearable
          v-model="queryPhysicalCmd.student_id" class="ipt" placeholder="请输入学号"
          :fetch-suggestions="queryUserBasicinfoHandler"
          @select="handleSelectStudent"
          :hide-loading="true" style="width: 200px; margin-right: 20px;"
        />
        <el-autocomplete v-if="isAdmin(userinfo.getAuth()) || isTeacher(userinfo.getAuth())" clearable  placeholder="班级id"
          v-model="queryPhysicalCmd.class_id" class="ipt"
          :fetch-suggestions="queryClassBasicinfoHandler"
          @select="handleSelectClassId"
          :hide-loading="true" style="width: 200px; margin-right: 20px;"
        />
        <el-input v-if="isAdmin(userinfo.getAuth()) || isTeacher(userinfo.getAuth())" v-model="queryPhysicalCmd.year" class="ipt" placeholder="学年" />
        <el-button v-if="!isStudent(userinfo.getAuth())" @click="queryPhysicalHandler" class="btn" type="primary">查询</el-button>
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
        <el-table-column label="操作" v-if="isAdmin(userinfo.getAuth()) || isTeacher(userinfo.getAuth())">
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
        <el-button @click="updatePhysicalHandler" type="primary" >保存</el-button>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
// 数据区
import {queryPhysicalApi, updatePhysicalApi} from "../api/PhysicalApi.js";
import {ElMessage} from "element-plus";
import {queryClassBasicInfoApi} from "../api/ClassApi.js";
import {useUserInfoStore} from "../infra/store/userinfoStore.js";
import {isAdmin, isStudent, isTeacher} from "../infra/tools/authTools.js";
import {queryUserinfoApi, queryUserBasicinfoApi} from "../api/UserinfoApi.js";
const userinfo = useUserInfoStore()
const physicalTable = ref([
  // {
  //   student_id: 10000,
  //   username: "龙洋静",
  //   physical: {
  //     physical_id: 1,
  //     student_id: 10000,
  //     year: 1,
  //     state: 1, // 0表示未测试，1表示已测试
  //     sprint: 9.8,
  //     run: 3.55,
  //     sit_up: 38,
  //     jump: 186,
  //     height: 150,
  //     weight: 40
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
 * 查询体测成绩
 */
const queryPhysicalCmd = ref({
  student_id: null,
  class_id: null,
  year: null,
})
const queryPhysicalHandler = async () => {
  const resp = await queryPhysicalApi(toRaw(queryPhysicalCmd.value))
  physicalTable.value = resp.data
}


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
const updatePhysicalHandler = async () => {
  await updatePhysicalApi(toRaw(updatePhysicalCmd.value))
  await queryPhysicalHandler()
  updatePhysicalCmd.value.isShow = false
  ElMessage.success("修改成功")
}

onMounted(async () => {
  if (isStudent(userinfo.getAuth())) {
    queryPhysicalCmd.value.student_id = userinfo.id
    await queryPhysicalHandler()
  }
})
// 学生 - 查询
const queryUserBasicinfoHandler = async (query, cb) => {
  if (!query) {
    cb([])
    return
  }
  const resp = await queryUserBasicinfoApi({
    info: queryPhysicalCmd.value.student_id,
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
// 班级 - 查询
const queryClassBasicinfoHandler = async (query, cb) => {
  if (!query) {
    cb([])
    return
  }
  const resp = await queryClassBasicInfoApi({
    info: queryPhysicalCmd.value.class_id
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
  queryPhysicalCmd.value.student_id = item.label.split('|').map(s => s.trim())[1]
  console.log('Selected:', queryPhysicalCmd.value.student_id)
}
const handleSelectClassId = (item) => {
  queryPhysicalCmd.value.class_id = item.label.split('|').map(s => s.trim())[0]
  console.log('Selected:', queryPhysicalCmd.value.class_id)
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
