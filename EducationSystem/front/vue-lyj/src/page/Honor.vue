<template>
  <div id="honor">
    <!-- 操作区 -->
    <div class="actions" >
      <div class="row">
        <el-input v-if="!isStudent(userinfo.getAuth())" v-model="queryHonorCmd.student_id" class="ipt" placeholder="请输入学生id" />
        <el-input v-if="!isStudent(userinfo.getAuth())" v-model="queryHonorCmd.class_id" class="ipt" placeholder="请输入班级id" />
        <el-button v-if="!isStudent(userinfo.getAuth())" @click="queryHonorHandler" class="btn" type="primary">查询</el-button>
        <el-button v-if="isStudent(userinfo.getAuth())" class="btn" type="warning" @click="applyHonorCmd.dialogVisible = true">申请荣誉</el-button>
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
        <el-table-column label="操作" v-if="!isParent(userinfo.getAuth())">
          <template #default="{ row }">
            <div style="display: flex; flex-direction: column; align-items: center; gap: 10px;">
              <el-button
                  v-if="!isStudent(userinfo.getAuth())"
                  type="success"
                  plain
                  @click="reviewHonorHandler(row.honor.honor_id, 1)"
              >
                通过
              </el-button>
              <el-button
                  v-if="!isStudent(userinfo.getAuth())"
                  type="warning"
                  plain
                  @click="reviewHonorHandler(row.honor.honor_id, -1)"
              >
                不通过
              </el-button>
              <el-button
                  v-if="row.honor.state === -1"
                  type="danger"
                  plain
                  @click="removeHonorHandler(row.honor.honor_id)"
              >
                删除
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog v-model="applyHonorCmd.dialogVisible" title="申请荣誉" width="500px">
      <el-form :model="applyHonorCmd" label-width="100px">
        <el-form-item label="荣誉名称">
          <el-input v-model="applyHonorCmd.honor_name" placeholder="请输入荣誉名称" />
        </el-form-item>
        <el-form-item label="荣誉类型">
          <el-radio-group v-model="applyHonorCmd.type">
            <el-radio :label="0">其他</el-radio>
            <el-radio :label="1">奖学金</el-radio>
            <el-radio :label="2">证书</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="applyHonorCmd.dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="applyHonorHandler">提交</el-button>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
// 数据区
import {applyHonorApi, delHonorApi, queryHonorApi, updateHonorApi} from "../api/HonorApi.js";
import {ElMessage} from "element-plus";
import {isParent, isStudent} from "../infra/tools/authTools.js";
import {useUserInfoStore} from "../infra/store/userinfoStore.js";

const userinfo = useUserInfoStore()
const honorTable = ref([
  // {
  //   "student_id": 10000,
  //   "username": "龙洋静",
  //   "honor": {
  //     "honor_id": 1,
  //     "student_id": 10000,
  //     "honor_name": "校级奖学金一等奖",
  //     "award_date": "2025-03-12",
  //     "type": 1,
  //     "state": -1
  //   },
  //   "classInfo": {
  //     "class_id": 1,
  //     "college": "信息工程学院",
  //     "major": "软件工程",
  //     "grade": 1,
  //     "year": 4
  //   }
  // },
])

/**
 * 查询荣誉
 */
const queryHonorCmd = ref({
  student_id: null,
  class_id: null,
})
const queryHonorHandler = async () => {
  const resp = await queryHonorApi(toRaw(queryHonorCmd.value))
  honorTable.value = resp.data
}

/**
 * 申请荣誉
 */
const applyHonorCmd = ref({
  dialogVisible: false,
  honor_name: null,
  type: null,
})
const applyHonorHandler = async () => {
  const resp = await applyHonorApi(toRaw(applyHonorCmd.value))
  ElMessage.success("申请成功，请等待耐心审核~")
  applyHonorCmd.value.dialogVisible = false
  await queryHonorHandler()
}

/**
 * 荣誉审核(实际上就是修改审核的 state)
 * @param state 1通过 -1不通过
 */
const reviewHonorHandler = async (honor_id, state) => {
  await updateHonorApi({
    honor_id: honor_id,
    state: state
  })
  await queryHonorHandler()
  ElMessage.success("操作成功")
}

/**
 * 删除荣誉
 */
const removeHonorHandler = async (honor_id) => {
  await delHonorApi({honor_id: honor_id})
  await queryHonorHandler()
  ElMessage.success("删除成功")
}

onMounted(async () => {
  const auth = userinfo.getAuth()
  if (isStudent(auth)) {
    queryHonorCmd.value.student_id = userinfo.id
    await queryHonorHandler()
  }
})

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
