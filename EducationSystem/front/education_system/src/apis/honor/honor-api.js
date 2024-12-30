import http from "../../utils/http"


// 学生 - 查询学生荣誉
export function student_self_honor_info(){
  return http.get("/honor/student")
}

// 学生申请荣誉
export function apply_honor(){
  return http.post("/honor/apply",{
    honor_name: honor_name,
    type: type
  })
}

// 学生 - 删除荣誉
export function delete_honor(id){
  return http.post("/honor/delete",{
    id: id
  })
}

// 教师 - 查询学生荣誉
export function student_honor_info(studentId, classId){
  return http.get("/honor/students",{
    params:{
      studentId: studentId,
      classId: classId
    }
  })
}