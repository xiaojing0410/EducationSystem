import http from "../../utils/http"

// 查看体侧成绩
export function physical_info_api(student_id, class_id, semester, identity){
  return http.get("/physical/info",{
    params:{
      student_id: student_id,
      class_id: class_id,
      semester: semester,
      identity: identity
    }
  })
}

// 教师 - 添加体测成绩
export function add_physical(student_id, semester, sprint, run, sit_up, jump, height, weight){
  return http.post("/physical/add",{
    student_id: student_id,
    semester: semester,
    sprint: sprint,
    run: run,
    sit_up: sit_up,
    jump: jump,
    height: height, 
    weight: weight
  })
}


// 教师 - 批量添加体测成绩
export function add_physical(student_id, semester, sprint, run, sit_up, jump, height, weight){
  return http.post("/physical/adds",{
    student_id: student_id,
    semester: semester,
    sprint: sprint,
    run: run,
    sit_up: sit_up,
    jump: jump,
    height: height, 
    weight: weight
  })
}
