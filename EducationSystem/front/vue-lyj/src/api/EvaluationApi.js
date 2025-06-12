import {api} from "../infra/axiosCfg.js";


/**
 * ai 反馈
 * @param type 0是整体评价 1荣誉信息 2考勤信息 3成绩信息 4体测信息
 */
export const aiFeedbackApi = async (
    { student_id, type }
) => {
    return await api.get(
        "/evaluation/ai/feed",
        {
            params: {
                student_id, type
            }
        }
    )
}


// "addEvaluationSubs": [
// {
//     "type": 3,
//     "score": 5
// }]
/**
 * 添加评价
 */
export const addEvaluationApi = async (
    { student_id, feedback, ai_feedback, addEvaluationSubs }
) => {
    return await api.post(
        "/evaluation/add",
        {
            student_id, feedback, ai_feedback, addEvaluationSubs
        }
    )
}


/**
 * 查询反馈
 */
export const queryEvaluationApi = async (
    {  student_id }
) => {
    return await api.get(
        "/evaluation/info",
        {
            params: {
                student_id
            }
        }
    )
}



