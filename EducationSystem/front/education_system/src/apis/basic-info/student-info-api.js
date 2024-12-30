import http from "@/utils/http";
// 教师 - 查看学生基础信息
export function student_info_api(identity, class_id){
  return http.get("/course/info",{
    params:{
      identity:identity,
      class_id:class_id
    }
  })
}

// 新增课程
export function add_course(course_name, teacher_id, class_id){
  return http.post("/course/add",{
    course_name: course_name,
    teacher_id: teacher_id,
    class_id: class_id
  })
}