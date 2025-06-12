import {api} from "../infra/axiosCfg.js";

/**
 * 查询重修成绩
 */
export const queryRelearnApi = async (
    { student_id, semester }
) => {
    return await api.get(
        "/relearn/info",
        {
            params: {
                student_id, semester
            }
        }
    )
}

/**
 * 添加重修记录
 */
export const addRelearnApi = async (
    { student_id, teacher_id, course_code_id, credit, score_old, semester, reason, state }
) => {
    return await api.post(
        "/relearn/add",
        {
            student_id, teacher_id, course_code_id, credit, score_old, semester, reason, state
        }
    )
}

/**
 * 修改重修记录
 */
export const updateRelearnApi = async (
    { relearn_id, score_new }
) => {
    return await api.post(
        "/relearn/update",
        {
            relearn_id, score_new
        }
    )
}