import axios from "axios";
import {api} from "../infra/axiosCfg.js";

/**
 * 查询体测成绩
 */
export const queryPhysicalApi = async (
    { student_id, class_id, year }
) => {
    return await api.get(
        "/physical/info",
        {
            params: {
                student_id, class_id, year
            }
        }
    )
}

/**
 * 修改体测成绩
 */
export const updatePhysicalApi = async (
    { physical_id, sprint, run, sit_up, jump, height, weight }
) => {
    return await api.post(
        "/physical/update",
        { physical_id, sprint, run, sit_up, jump, height, weight }
    )
}


