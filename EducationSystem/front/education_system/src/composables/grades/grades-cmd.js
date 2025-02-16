import { set_token } from "@/stores/token-local"
import { set_user_info, set_identity, get_identity } from "@/stores/user-local"
import { show_message_util } from "@/utils/element-utils"
import { student_one_grades_api, students_grades_api, grades_warn_api, grades_suggest_api, delete_grades_api, add_grades_api, adds_grades_api, update_grades_api} from '@/apis/grades/grades-api'
import router from "@/router"


/**
 * 查询成绩
 * @param {*} 
 */
export function student_one_grades_cmd(courseName=null, semester=null) {
  const student_one_grades = async() => {
    const res = await student_one_grades_api(courseName, semester)
    if (res != null && res.code == 0) {
      return res.data
    }else {
      show_message_util("获取课程信息失败！", "error")
      return 0
    }
  }
  return student_one_grades()
}

/**
 * 教师查询成绩
 * @param {*} 
 */
export function students_grades_cmd(class_id, course_name, student_id, semester) {
  const students_grades = async() => {
    const res = await students_grades_api(class_id, course_name, student_id, semester)
    if (res != null && res.code == 0) {
      return res.data
    }else {
      show_message_util("获取课程信息失败！", "error")
      return 0
    }
  }
  return students_grades()
}

/**
 * 查询学生学业预警
 * @param {*} 
 */
export function grades_warn_cmd(student_id) {
  const grades_warn = async() => {
    const identity = get_identity()
    const res = await grades_warn_api(student_id, identity)
    if (res != null && res.code == 0) {
      return res.data
    }else {
      show_message_util("获取课程信息失败！", "error")
      return 0
    }
  }
  return grades_warn()
}

/**
 * 生成个性化推荐
 * @param {*} 
 */
export function grades_suggest_cmd(student_id) {
  const grades_suggest = async() => {
    const identity = get_identity()
    const res = await grades_suggest_api(student_id, identity)
    if (res != null && res.code == 0) {
      return res.data
    }else {
      show_message_util("获取课程信息失败！", "error")
      return 0
    }
  }
  return grades_suggest()
}

/**
 * 删除成绩
 * @param {*} 
 */
export function delete_grades_cmd(grades_id) {
  const delete_grades = async() => {
    const res = await delete_grades_api(null, null, null, grades_id)
    if (res != null && res.code == 0) {
      // window.location.reload() // 强制刷新页面
      show_message_util("删除成绩成功！", "success")
      return res.data
    }else {
      show_message_util("删除成绩失败！", "error")
      return 0
    }
  }
  return delete_grades()
}

/**
 * 录入成绩
 * @param {*} 
 */
export function add_grades_cmd(course_id, student_id, semester, score) {
  const add_grades = async() => {
    const res = await add_grades_api(course_id, student_id, semester, score)
    if (res != null && res.code == 0) {
      // window.location.reload() // 强制刷新页面
      show_message_util("添加成绩成功！", "success")
      return res.data
    }else {
      show_message_util("添加成绩失败！", "error")
      return 0
    }
  }
  return add_grades()
}

/**
 * 修改成绩
 * @param {*} 
 */  
export function update_grades_cmd(score, grades_id) {
  const update_grades = async() => {
    const res = await update_grades_api(null, null, null, score, grades_id)
    if (res != null && res.code == 0) {
      // window.location.reload() // 强制刷新页面
      show_message_util("修改成绩成功！", "success")
      return res.data
    }else {
      show_message_util("修改成绩失败！", "error")
      return 0
    }
  }
  return update_grades()
}