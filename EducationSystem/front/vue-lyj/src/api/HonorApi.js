import {api} from "../infra/axiosCfg.js";

/**
 * 查询荣誉信息
 */
export const queryHonorApi = async (
    { student_id, class_id }
) => {
    return await api.get(
        "/honor/info",
        {
            params: {
                student_id, class_id
            }
        }
    )
}

/**
 * 申请荣誉
 */
export const applyHonorApi = async (
    { honor_name, type }
) => {
    return await api.post(
        "/honor/apply",
        {
            honor_name, type
        }
    )
}

/**
 * 删除荣誉
 */
export const delHonorApi = async (
    { honor_id }
) => {
    return await api.post(
        "/honor/del",
        {
            honor_id
        }
    )
}

/**
 * 修改荣誉信息(审核)
 */
export const updateHonorApi = async (
    { honor_id, state}
) => {
    return await api.post(
        "/honor/update",
        {
            honor_id, state
        }
    )
}


