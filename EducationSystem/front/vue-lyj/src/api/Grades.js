import {api} from "../infra/axiosCfg.js";


/**
 * 查询成绩
 */
export const queryGradesApi = async (
    { class_id, semester, student_id }
) => {
    return await api.get(
        "/grades/info",
        {
            params: {
                class_id, semester, student_id
            }
        }
    )
}

/**
 * 修改考勤
 */
export const updateGradesApi = async (
    { grades_id, score }
) => {
    return await api.post(
        "/grades/update",
        { grades_id, score }
    )
}

