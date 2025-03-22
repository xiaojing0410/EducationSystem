import {api} from "../infra/axiosCfg.js";

/**
 * 查询课程信息
 */

export const queryCourseApi = async (
    { course_info }
) => {
    return await api.get(
    "/course/info",
        {
            params: {
                course_info
            }
        }
    )
}

/**
 * 添加课程
 */
export const addCourseApi = async (
    { course_name, type, credit }
) => {
    return await api.post(
        "/course/add",
        {
            course_name, type, credit
        }
    )
}

