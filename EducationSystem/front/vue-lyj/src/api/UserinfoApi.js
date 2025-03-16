import {ax} from "../infra/axiosCfg.js";


/**
 * 注册
 */
const regApi = async (
    { identity, username, password, gender, card_id, dorm_id, class_id, student_array }
) => {
    return await ax.post(
        "/user/reg",
        {
            identity, username, password, gender, card_id, dorm_id, class_id, student_array
        }
    )
}

/**
 * 登录
 */
const loginApi = async (
    { user_id, password }
) => {
    return await ax.post(
        "/user/login",
        { user_id, password }
    )
}

/**
 * 查询用户信息
 */
const queryUserinfoApi = async (
    { class_id, major, student_id, admin_id, teacher_id }
) => {
    return await ax.get(
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
const updateUserinfoApi = async (
    { oldpassword, password, email, phone, identity }
) => {
    return await ax.post(
        "/user/update",
        { oldpassword, password, email, phone, identity }
    )
}

