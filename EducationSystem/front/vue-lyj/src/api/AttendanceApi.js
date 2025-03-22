import {api} from "../infra/axiosCfg.js";

/**
 * 查询考勤信息
 */
export const queryAttendanceApi = async (
    { semester, course_id, student_id }
) => {
    return await api.get(
        "/attendance/info",
        {
            params: {
                semester, course_id, student_id
            }
        }
    )
}

/**
 * 添加考勤
 */
export const addAttendanceApi = async (
    { course_id }
) => {
    return await api.post(
        "/attendance/update/total",
        {
            course_id
        }
    )
}

/**
 * 修改考勤信息
 */
export const updateAttendanceApi = async (
    { attendance_id }
) => {
    return await api.post(
        "/attendance/update",
        {
            attendance_id
        }
    )
}

