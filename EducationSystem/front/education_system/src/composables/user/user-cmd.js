import { set_token } from "@/stores/token-local"
import { set_user_info } from "@/stores/user-local"
import { reg_api, login_api, user_info_api, logout_api } from "@/apis/user/user-api"
import { show_message_util } from "@/utils/element-utils"
import router from "@/router"
/**
 * 登录接口
 * @param {*} user: id password :学号、密码
 */
export function login_cmd(idIpt, passwordIpt, identityIpt) {
  const login = async() => {
    const res = await login_api(idIpt, passwordIpt, identityIpt)
    if (res != null && res.code == 0) {
      set_token(res.data);
      show_message_util("登录成功！", "success")
      if(identityIpt === 1){
        router.push('/student') 
      } else if (identityIpt === 2){
        router.push('/teacher') 
      } else {
        console.log("参数有误")
      }
      // 请求个人信息 - 后续常用 提前备着
      const userInfo = await user_info_api(identityIpt)
      if(userInfo != null && userInfo.code == 0){
        set_user_info(userInfo.data)
      } else {
        show_message_util("用户信息获取失败！", "error")
      }
      return
    }else {
      show_message_util("登录失败！", "error")
    }
  }
  login()
}


/**
 * 注册接口
 * @param {*} usernameIpt, passwordIpt :用户名、密码
 */
export function reg_cmd(usernameIpt, passwordIpt, identity) {
  const reg = async() => {
    const res = await reg_api(usernameIpt, passwordIpt, identity)
    if (res != null && res.code == 0) {
      show_message_util("注册成功！", "success")
      router.push('/login') 
      return
    }else {
      show_message_util("注册失败！", "error")
    }
  }
  reg()
}


/**
 * 注销接口
 */
export function logout_cmd(){
  const logout = async() => {
    const res = await logout_api()
    if (res != null && res.code == 0) {
      // 清空登录信息
      set_token("")
      set_user_info("")
      show_message_util("退出成功！", "success")
      router.push('/login') 
      return
    }else {
      show_message_util("退出失败！", "error")
    }
  }
  logout()
}

/**
 * 
 */
