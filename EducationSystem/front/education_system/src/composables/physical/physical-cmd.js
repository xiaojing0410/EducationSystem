import { set_user_info, set_identity, get_identity } from "@/stores/user-local"
import { physical_info_api, add_physical_api, adds_physical_api} from "@/apis/physical/physical-api"
import { show_message_util } from "@/utils/element-utils"
import router from "@/router"


/**
 * 查询个人体测成绩
 * @param {*} 
 */
export function physical_info_cmd(student_id=null, class_id=null, semester=null) {
  const physical_info = async() => {
    const identity = get_identity()
    const res = await physical_info_api(student_id, class_id, semester, identity)
    if (res != null && res.code == 0) {
      return res.data
    }else {
      show_message_util("获取体测成绩失败！", "error")
      return 0
    }
  }
  return physical_info()
}

/**
 * 教师新增体测成绩
 */
export function add_physical_cmd(student_id, semester, sprint, run, sit_up, jump, height, weight){
  const add_physical = async() => {
    const res = await add_physical_api(student_id, semester, sprint, run, sit_up, jump, height, weight)
    if (res != null && res.code == 0) {
      show_message_util("新增体测成绩成功！", "success")
      window.location.reload() // 强制刷新页面
      return res.data
    }else {
      show_message_util("新增体测成绩失败！", "error")
      return 0
    }
  }
  add_physical()
}
