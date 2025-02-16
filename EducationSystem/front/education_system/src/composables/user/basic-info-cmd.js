import { student_info_api } from "@/apis/basic-info/student-info-api"
import { show_message_util } from "@/utils/element-utils"
import router from "@/router"
/**
 * 查询学生接口
 * @param {*} 
 */
export function student_info_cmd(studentId=null, class_id=null) {
  const student_info = async() => {
    const res = await student_info_api(studentId, class_id)
    if (res != null && res.code == 0) {
      // show_message_util("成功！", "success")
      return res.data
    }else {
      show_message_util("查询学生信息失败！", "error")
    }
  }
  return student_info()
}
