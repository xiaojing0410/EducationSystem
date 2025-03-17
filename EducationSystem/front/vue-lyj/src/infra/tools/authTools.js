
export const getAuthName = (auth) => {
    const authMap = {
        0: "管理员",
        1: "教师",
        2: "学生",
        3: "家长"
    };
    return authMap[auth] || "未知";
}

export const isAdmin = (auth) => auth === 0;
export const isTeacher = (auth) => auth === 1;
export const isStudent = (auth) => auth === 2;
export const isParent = (auth) => auth === 3;
