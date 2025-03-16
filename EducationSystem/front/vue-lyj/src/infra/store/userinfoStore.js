import { defineStore } from 'pinia';

const userinfoConst = {
    ID: "userinfo.id",
    USERNAME: "userinfo.username",
    AUTH: "userinfo.auth",
}
export const useUserInfoStore = defineStore('userinfo', {
  state: () => ({
      id: localStorage.getItem(userinfoConst.ID) || null,
      username: localStorage.getItem(userinfoConst.USERNAME) || null,
      auth: Number(localStorage.getItem(userinfoConst.AUTH)) || null,
  }),
    actions: {
        update({ id, username }) {
            this.id = id;
            this.username = username;

            if (id >= 1 && id <= 1999) {
                this.auth = 0 //"管理员"
            } else if (id >= 2000 && id <= 4999) {
                this.auth = 1 // "老师";
            } else if (id >= 5000 && id <= 9999) {
                this.auth = 3 // "家长";
            } else if (id >= 10000) {
                this.auth = 2 // "学生";
            } else {
                this.auth = "未知";
            }
            localStorage.setItem(userinfoConst.ID, id);
            localStorage.setItem(userinfoConst.USERNAME, username);
            localStorage.setItem(userinfoConst.AUTH, this.auth);
        },
        clean() {
            localStorage.removeItem(userinfoConst.ID);
            localStorage.removeItem(userinfoConst.USERNAME);
            localStorage.removeItem(userinfoConst.AUTH);
        },
    },
});
