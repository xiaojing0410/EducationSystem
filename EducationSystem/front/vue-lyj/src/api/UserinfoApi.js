import {api} from "../infra/axiosCfg.js";


/**
 * 注册
 */
export const regApi = async (
    { identity, username, password, gender, card_id, dorm_id, class_id, student_array }
) => {
    return await api.post(
        "/user/reg",
        {
            identity, username, password, gender, card_id, dorm_id, class_id, student_array
        }
    )
}

/**
 * 登录
 */
export const loginApi = async (
    { user_id, password }
) => {
    return await api.post(
        "/user/login",
        { user_id, password }
    )
}

/**
 * 查询用户信息
 */
export const queryUserinfoApi = async (
    { class_id, major, student_id, admin_id, teacher_id }
) => {
    return await api.get(
        "/user/userinfo",
        {
            params: {
                class_id, major, student_id, admin_id, teacher_id
            }
        }
    )
}

/**
 * 修改用户信息
 */
export const updateUserinfoApi = async (
    { oldpassword, password, email, phone }
) => {
    return await api.post(
        "/user/update",
        { oldpassword, password, email, phone }
    )
}



