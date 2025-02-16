import http from "../../utils/http"

// 教师 - 查看学生基础信息
export function student_info_api(studentId, class_id){
  return http.get("/student/info",{
    params:{
      studentId:studentId,
      class_id:class_id
    }
  })
}

