import {api} from "../infra/axiosCfg.js";

/**
 * ai 生成个性化推荐
 */
export const aiRecommendApi = async (
    { student_id }
) => {
    return await api.get(
        "/evaluation/ai/personalized",
        {
            params: {
                student_id
            }
        }
    )
}

