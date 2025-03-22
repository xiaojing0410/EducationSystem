import {api} from "../infra/axiosCfg.js";


/**
 * 查询课程安排
 */
export const queryCoursePlanApi = async (
    { class_id, semester, student_id }
) => {
    return await api.get(
        "/course/schedule/info",
        {
            params: {
                class_id, semester, student_id
            }
        }
    )
}

/**
 * 添加课程安排信息
 */
export const addCoursePlanApi = async (
    { course_code_id, semester, teacher_id, class_id, time, loc }
) => {
    return await api.post(
        "/course/schedule/add",
        {
            course_code_id, semester, teacher_id, class_id, time, loc
        }
    )
}

