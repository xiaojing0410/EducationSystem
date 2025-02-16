import { set_token } from "@/stores/token-local"
import { set_user_info, set_identity, get_identity } from "@/stores/user-local"
import { reg_api, login_api, logout_api, user_info_api, update_user_info_api } from "@/apis/user/user-api"
import { show_message_util } from "@/utils/element-utils"
import { student_self_honor_info_api, apply_honor_api, delete_honor_api, student_honor_info_api} from '@/apis/honor/honor-api'
import router from "@/router"


/**
 * 查询个人荣誉
 * @param {*} 
 */
export function student_self_honor_info_cmd() {
  const student_self_honor_info = async() => {
    const res = await student_self_honor_info_api()
    if (res != null && res.code == 0) {
      return res.data
    }else {
      show_message_util("获取荣誉失败！", "error")
      return 0
    }
  }
  return student_self_honor_info()
}

/**
 * 申请个人荣誉
 * @param {*} 
 */
export function apply_honor_cmd(honor_name, award_date, type) {
  const apply_honor = async() => {
    const res = await apply_honor_api(honor_name, award_date, type)
    if (res != null && res.code == 0) {
      show_message_util("申请荣誉成功！", "success")
      window.location.reload() // 强制刷新页面
      return res.data
    }else {
      show_message_util("申请荣誉失败！", "error")
      return 0
    }
  }
  apply_honor()
}

/**
 * 删除个人荣誉
 * @param {*} 
 */
export function delete_honor_cmd(id) {
  const delete_honor = async() => {
    const res = await delete_honor_api(id)
    if (res != null && res.code == 0) {
      show_message_util("删除荣誉成功！", "success")
      window.location.reload() // 强制刷新页面
      return res.data
    }else {
      show_message_util("删除荣誉失败！", "error")
      return 0
    }
  }
  delete_honor()
}



/**
 * 教室 - 查询学生荣誉
 * @param {*} 
 */
export function student_honor_info_cmd(studentId=null, classId=null) {
  const student_honor_info = async() => {
    const res = await student_honor_info_api(studentId, classId)
    if (res != null && res.code == 0) {
      return res.data
    }else {
      show_message_util("获取荣誉失败！", "error")
      return 0
    }
  }
  return student_honor_info()
}