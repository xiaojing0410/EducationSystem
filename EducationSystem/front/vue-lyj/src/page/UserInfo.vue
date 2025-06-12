<template>
  <div id="userinfo">
    <!-- 操作区 -->
    <div class="actions" >
      <div class="row">
        <el-select v-model="value" clearable placeholder="Select" class="ipt">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
        <!-- TODO  -->
        <el-autocomplete v-if="value == 1"  clearable
          v-model="queryUserInfoCmd.admin_id" class="ipt" placeholder="请输入管理员id"
          :fetch-suggestions="queryUserBasicinfoHandler"
          @select="handleSelectAdmin"
          :hide-loading="true" style="width: 150px; margin-right: 20px;"
        />
        <el-autocomplete v-if="value == 2"  clearable
          v-model="queryUserInfoCmd.teacher_id" class="ipt" placeholder="请输入教师id"
          :fetch-suggestions="queryUserBasicinfoHandler"
          @select="handleSelectTeacher"
          :hide-loading="true" style="width: 150px; margin-right: 20px;"
        />
        <el-autocomplete v-if="value == 3"  clearable
          v-model="queryUserInfoCmd.student_id" class="ipt" placeholder="请输入学号"
          :fetch-suggestions="queryUserBasicinfoHandler"
          @select="handleSelectStudent"
          :hide-loading="true" style="width: 150px; margin-right: 20px;"
        />
        <el-autocomplete v-if="value == 3"  clearable
          v-model="classInfo" class="ipt" placeholder="请输入班级id"
          :fetch-suggestions="queryClassBasicinfoHandler"
          @select="handleSelectClassId"
          :hide-loading="true" style="width: 150px; margin-right: 20px;"
        />
        <el-input v-if="value == 3" clearable
            v-model="queryUserInfoCmd.major" class="ipt" placeholder="请输入专业" />
        <!-- <el-input v-if="value == 1"  clearable
                  v-model="queryUserInfoCmd.admin_id" class="ipt" placeholder="请输入管理员id"
                  :value="queryUserInfoCmd.admin_id === 0 ? '' : queryUserInfoCmd.admin_id"  />
        <el-input v-if="value == 2"  clearable
                  v-model="queryUserInfoCmd.teacher_id" class="ipt" placeholder="请输入教师id"
                  :value="queryUserInfoCmd.teacher_id === 0 ? '' : queryUserInfoCmd.teacher_id"  />
        <el-input v-if="value == 3"  clearable
                  v-model="queryUserInfoCmd.student_id" class="ipt" placeholder="请输入学生id"
                  :value="queryUserInfoCmd.student_id === 0 ? '' : queryUserInfoCmd.student_id"  />
        <el-input v-if="value == 3" clearable
            v-model="queryUserInfoCmd.class_id" class="ipt" placeholder="请输入班级id" />
         -->
        <el-button class="btn" type="primary" @click="queryUserinfoHandler">查询</el-button>
        <el-button class="btn" type="primary" @click="reg">注册新用户</el-button>
      </div>
    </div>

    <!-- 数据区 -->
    <div class="data">
      <el-table :data="userTable" border style="width: 100%">
        <el-table-column prop="user_id" label="用户id" />
        <el-table-column prop="username" label="姓名" />
        <el-table-column prop="gender" label="性别" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="phone" label="手机号" />
        <el-table-column v-if="value == 3" prop="classInfo.class_id" label="班级" />
        <el-table-column v-if="value == 3" prop="classInfo.college" label="学院" />
        <el-table-column v-if="value == 3" prop="classInfo.major" label="专业" />
        <el-table-column v-if="value == 3" prop="classInfo.grade" label="班级" />
        <el-table-column v-if="value == 3" prop="classInfo.year" label="学年" />
        <el-table-column v-if="value == 3" prop="current_state" label="状态">
          <template #default="{ row }">
            <el-tag v-if="row.current_state === -2" type="success">毕业</el-tag>
            <el-tag v-else-if="row.current_state === -1" type="warning">预报到</el-tag>
            <el-tag v-else-if="row.current_state === 0" type="primary">就读</el-tag>
            <el-tag v-else-if="row.current_state === 1" type="danger">休学中</el-tag>
            <el-tag v-else type="info">未知状态</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="card_id" label="身份证号" />
        <el-table-column v-if="value == 3" prop="dorm_id" label="宿舍号" />
        <el-table-column v-if="value == 3" prop="create_time" label="入学时间" />
      </el-table>
    </div>


  </div>
</template>

<script setup>
// 数据区
import {queryUserinfoApi, queryUserBasicinfoApi} from "../api/UserinfoApi.js";
import {queryClassBasicInfoApi} from "../api/ClassApi.js";
import {isAdmin, isParent, isStudent, isTeacher} from "../infra/tools/authTools.js";
import {useUserInfoStore} from "../infra/store/userinfoStore.js";
import {ElMessage} from "element-plus";
import { ref } from "vue";
// import { it } from "element-plus/es/locale/index.js";

const userinfo = useUserInfoStore()
const auth = userinfo.getAuth()
const userTable = ref([
  //   {
  //   "user_id": 10003,
  //   "username": "小静",
  //   "gender": "女",
  //   "email": "2234",
  //   "phone": "1237",
  //   "classInfo": {
  //     "class_id": 1,
  //     "college": "信息工程学院",
  //     "major": "软件工程",
  //     "grade": 1,
  //     "year": 4
  //   },
  //   "current_state": -1,  //-2毕业 -1预报到 0就读 1表示休学中
  //   "card_id": "610902200107128841",
  //   "dorm_id": "10-207",
  //   "student_array": null,
  //   "create_time": "2025-03-02",
  // }
])
const userBasicList = ref([])
/**
 * 查询相关
 */
const value = ref('0')
const options = ref([
  {
    value: '0',
    label: '查询本人',
  }
])
const suggestions = ref([])
/**
 * 查询用户列表
 */
const queryUserInfoCmd = ref({
  class_id: null,
  major: null,
  student_id: null,
  admin_id: null,
  teacher_id: null
})
const classInfo = ref('')
const resetQueryUserInfoCmd = async () => {
  if(queryUserInfoCmd.value.admin_id == 0){
    queryUserInfoCmd.value.admin_id = null
  }
  if(queryUserInfoCmd.value.teacher_id == 0){
    queryUserInfoCmd.value.teacher_id = null
  }
  if(queryUserInfoCmd.value.student_id < 3){
    queryUserInfoCmd.value.student_id = null
  }
}
const queryUserinfoHandler = async () => {
  if(value.value == 1 && queryUserInfoCmd.value.admin_id == null){
    queryUserInfoCmd.value.admin_id = 0
  } else if(value.value == 2 && queryUserInfoCmd.value.teacher_id == null){
    queryUserInfoCmd.value.teacher_id = 0
  } else if(value.value == 3 && queryUserInfoCmd.value.student_id == null){
    if(queryUserInfoCmd.value.major != null){
      queryUserInfoCmd.value.student_id = 2
    } else if(queryUserInfoCmd.value.class_id != null){
      queryUserInfoCmd.value.student_id = 1
    } else {
      queryUserInfoCmd.value.student_id = 0
    }
  }
  const resp = await queryUserinfoApi({
    class_id: queryUserInfoCmd.value.class_id,
    major: queryUserInfoCmd.value.major,
    student_id: queryUserInfoCmd.value.student_id,
    admin_id: queryUserInfoCmd.value.admin_id,
    teacher_id: queryUserInfoCmd.value.teacher_id
  })
  resetQueryUserInfoCmd()
  userTable.value = resp.data
}
const queryUserBasicinfoHandler = async (query, cb) => {
  const info = ref()
  if(value.value == 1){
    info.value = queryUserInfoCmd.value.admin_id
  } else if(value.value == 2){
    info.value = queryUserInfoCmd.value.teacher_id
  } else if(value.value == 3){
    info.value = queryUserInfoCmd.value.student_id
  }
  if(value.value == ''){
    suggestions.value = []
    return;
  }
  if (!query) {
    cb([])
    return
  }
  const resp = await queryUserBasicinfoApi({
    info: info.value,
    type: value.value
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
const queryClassBasicinfoHandler = async (query, cb) => {
  if (!query) {
    cb([])
    return
  }
  const resp = await queryClassBasicInfoApi({
    info: classInfo.value
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
const handleSelectAdmin = (item) => {
  queryUserInfoCmd.value.admin_id = item.label.split('|').map(s => s.trim())[1]
  console.log('Selected:', queryUserInfoCmd.value.admin_id)
}
const handleSelectTeacher = (item) => {
  queryUserInfoCmd.value.teacher_id = item.label.split('|').map(s => s.trim())[1]
  console.log('Selected:', queryUserInfoCmd.value.teacher_id)
}
const handleSelectStudent = (item) => {
  queryUserInfoCmd.value.student_id = item.label.split('|').map(s => s.trim())[1]
  console.log('Selected:', queryUserInfoCmd.value.student_id)
}
const handleSelectClassId = (item) => {
  queryUserInfoCmd.value.class_id = item.label.split('|').map(s => s.trim())[0]
  console.log('Selected:', queryUserInfoCmd.value.class_id)
}
onMounted(async () => {
  await queryUserinfoHandler()
  if(isAdmin(userinfo.getAuth())){
    options.value = [
      {
        value: '0',
        label: '查询本人',
      },
      {
        value: '1',
        label: '查询管理员',
      },
      {
        value: '2',
        label: '查询教师',
      },
      {
        value: '3',
        label: '查询学生',
      }
    ]
  } else if(isTeacher(userinfo.getAuth())){
    options.value = [
      {
        value: '0',
        label: '查询本人',
      },
      {
        value: '2',
        label: '查询教师',
      },
      {
        value: '3',
        label: '查询学生',
      }
    ]
  }if(isStudent(userinfo.getAuth())){
    options.value = [
      {
        value: '0',
        label: '查询本人',
      }
    ]
  }if(isParent(userinfo.getAuth())){
    options.value = [
      {
        value: '0',
        label: '查询本人',
      },
      {
        value: '3',
        label: '查询学生',
      }
    ]
  }
})

const reg = ()=>{
  window.location.href = '/reg';
}
</script>

<style lang="scss" scoped>
#userinfo {
  // width: 100%;
  height: 100%;
}

#userinfo .actions {
  margin-top: 15px
}

.actions .row {
  display: flex;
  justify-content: left;
  align-items: center;
  margin-top: 10px;


  .btn {
    width: 100px;
  }
}
.ipt {
  width: 130px;
  margin-right: 20px;
}

#userinfo .data {
  // width: 100%;
  height: 100%;
  // max-height: 100vh;
  max-width: 100vw;
  margin-top: 20px;
  overflow-y: auto;
  overflow-x: auto;
}

</style>
