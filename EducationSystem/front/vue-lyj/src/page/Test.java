
// 评价表，一个学生只能有一个评价
class Evalution(
    Integer id, // 主键自增id
    Integer studentId, // 学生id
    String feedback, // 教师反馈
    String aiFeedback, // ai 反馈(默认 "")
)

// 评价子表
class SubEvalution(
    Integer evalutionId, // 评价表id
    Integer type, // 评价类型(1荣誉信息 2考勤信息 3成绩信息 4体测信息)
    Integer score, // 分数，从0~5分别是['很差', '差', '一般', '良好', '优秀', '非常优秀'],
    String aiSuggestion, // ai 建议(默认 "")
)

// 逻辑: 学生只能查询自己的评价; 老师可以根据学生id指定学生然后进行评级