import http from "../../utils.http"


// 学生 - 查询学生成绩
export function student_one_grades_api(courseName, semester){
  return http.get("/grades/student",{
    params:{
      courseName: courseName,
      semester: semester
    }
  })
}

// 教师 - 删除成绩
export function delete_grades_api(user_id, class_id, course_id){
  return http.post("/grades/delete",{
    user_id: user_id,
    class_id: class_id,
    course_id: course_id
  })
}

// 教师 - 录入成绩
export function add_grades_api(course_id, student_id, semester, score){
  return http.post("/grades/add",{
    course_id: course_id,
    student_id: student_id,
    semester: semester,
    score: score
  })
}

// 教师 - 批量录入成绩
export function adds_grades_api(course_id, semester, score, student_id){
  return http.post("/grades/adds",{
    course_id: course_id,
    semester: semester,
    score: score,
    student_id: student_id
  })
}

// 教师 - 修改成绩
export function update_grades_api(course_id, student_id, semester, score){
  return http.post("/grades/update",{
    course_id: course_id,
    semester: semester,
    score: score,
    student_id: student_id
  })
}

// 查看学生的学业预警 - 
export function grades_warn_api(student_id, identity){
  return http.get("/grades/warning",{
    params:{
      student_id: student_id,
      identity: identity
    }
  })
}

// 个性化推荐
export function grades_suggest_api(student_id, identity){
  return http.get("/grades/suggest",{
    params:{
      student_id: student_id,
      identity: identity
    }
  })
}