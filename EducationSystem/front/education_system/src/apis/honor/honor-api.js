import http from "../../utils/http"


// 学生 - 查询学生荣誉
export function student_self_honor_info_api(){
  return http.get("/honor/student")
}

// 学生申请荣誉
export function apply_honor_api(honor_name, award_date, type){
  return http.post("/honor/apply",{
    honor_name: honor_name,
    award_date: award_date,
    type: type
  })
}

// 学生 - 删除荣誉
export function delete_honor_api(id){
  return http.post("/honor/delete",{
    id: id
  })
}

// 教师 - 查询学生荣誉
export function student_honor_info_api(studentId, classId){
  return http.get("/honor/students",{
    params:{
      studentId: studentId,
      classId: classId
    }
  })
}