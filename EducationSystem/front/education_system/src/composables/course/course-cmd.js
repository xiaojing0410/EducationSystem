import { set_token } from "@/stores/token-local"
import { set_user_info, set_identity, get_identity } from "@/stores/user-local"
import { show_message_util } from "@/utils/element-utils"
import { course_info_api, add_course_api} from '@/apis/course/course-api'
import router from "@/router"


/**
 * 查询课程列表
 * @param {*} 
 */
export function course_info_cmd(class_id=null) {
  const course_info = async() => {
    const identity = get_identity()
    const res = await course_info_api(identity, class_id)
    if (res != null && res.code == 0) {
      return res.data
    }else {
      show_message_util("获取课程信息失败！", "error")
      return 0
    }
  }
  return course_info()
}


/**
 * 新增课程
 * @param {*} 
 */
export function add_course_cmd(course_name, teacher_id, class_id) {
  const add_course = async() => {
    const identity = get_identity()
    const res = await add_course_api(course_name, teacher_id, class_id)
    if (res != null && res.code == 0) {
      show_message_util("新增课程成功！", "success")
      return res.data
    }else {
      show_message_util("新增课程失败！", "error")
      return 0
    }
  }
  return add_course()
}
