import { id } from "element-plus/es/locale/index.mjs"
import http from "../..//utils/http"

// 注册
export function reg_api(username, password, identity){
  return http.post("/user/reg",{
    username: username,
    password: password,
    identity: identity
  })
}

// 登录
export function login_api(student_id,password, identity){
  return http.post("/user/login",{
      id: student_id,
      password: password,
      identity: identity
  })
}

// 退出登录
export function logout_api(){
  return http.post("/user/logout")
}

// 获取当前用户信息
export function user_info_api(identity){
  return http.get("/user/getUserInfo",{
    params:{
      identity: identity
    }
  })
}


// 修改用户信息
export function update_user_info(oldpassword, password, email, phone, identity){
  return http.post("/user/update",{
    oldpassword: oldpassword,
    password: oldpassword,
    email: email,
    phone: phone,
    identity: identity
  })
}
