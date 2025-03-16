import {api} from "../infra/axiosCfg.js";

/**
 * 查询班级信息
 */
export const queryClassInfoApi = async () => {
    return await api.get(
        "/class/info",
    )
}

/**
 * 修改班级信息(添加学生到班级)
 */
export const updateClassInfoApi = async (
    { student_ids, class_id }
) => {
    return await api.post(
        "/class/update/students",
    { student_ids, class_id }
    )
}

/**
 * 添加班级
 */
export const addClassInfoApi = async (
    { college, major, grade, year }
) => {
    return await api.post(
        "/class/add",
        { college, major, grade, year }
    )
}


