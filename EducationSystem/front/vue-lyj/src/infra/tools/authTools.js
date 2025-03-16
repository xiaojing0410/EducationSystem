
export const getAuthName = (auth) => {
    const authMap = {
        0: "管理员",
        1: "教师",
        2: "学生",
        3: "家长"
    };
    return authMap[auth] || "未知";
}
