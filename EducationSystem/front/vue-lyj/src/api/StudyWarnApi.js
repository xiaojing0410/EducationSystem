import {api} from "../infra/axiosCfg.js";

/**
 * 查询学业预警信息
 */
export const queryStudyWarnApi = async (
    { student_id }
) => {
    return await api.get(
        "/precaution/info",
        {
            params: {
                student_id
            }
        }
    )
}