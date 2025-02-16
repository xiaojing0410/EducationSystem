import { set_token } from "@/stores/token-local"
import { set_user_info, set_identity, get_identity } from "@/stores/user-local"
import { reg_api, login_api, logout_api, user_info_api, update_user_info_api } from "@/apis/user/user-api"
import { show_message_util } from "@/utils/element-utils"
import router from "@/router"
/**
 * 登录接口
 * @param {*} user: id password :学号、密码
 */
export function login_cmd(idIpt, passwordIpt) {
  const login = async() => {
    const identityIpt = idIpt >= 1000 ? 2 : 1;  // 根据条件直接给 identityIpt 赋值
    const res = await login_api(idIpt, passwordIpt, identityIpt)
    if (res != null && res.code == 0) {
      set_token(res.data);
      set_identity(identityIpt)
      show_message_util("登录成功！", "success")

      // 请求个人信息 - 后续常用 提前备着
      const userInfo = await user_info_api(identityIpt)
      if(userInfo != null && userInfo.code == 0){
        set_user_info(userInfo.data)
      } else {
        show_message_util("用户信息获取失败！", "error")
      }

      if(identityIpt === 1){
        router.push('/student/info') 
      } else if (identityIpt === 2){
        router.push('/teacher/info') 
      } else {
        console.log("参数有误")
      }
      // window.location.reload() // 强制刷新页面
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
      set_identity("")
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

/**
 * 修改个人基础信息接口
 * @param {*} 
 */
export function update_user_info_cmd(oldpassword, password, email, phone) {
  const user_info = async() => {
    const identity = get_identity()
    const res = await update_user_info_api(oldpassword, password, email, phone, identity)
    if (res != null && res.code == 0) {
      show_message_util("修改成功！", "success")
      // 请求个人信息 - 后续常用 提前备着
      const userInfo = await user_info_api(identity)
      if(userInfo != null && userInfo.code == 0){
        set_user_info(userInfo.data)
      } else {
        show_message_util("用户信息获取失败！", "error")
      }
      return res.data
    }else {
      show_message_util("修改个人信息失败！", "error")
      return
    }
  }
  user_info()
}
