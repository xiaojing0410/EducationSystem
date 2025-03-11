import { defineStore } from 'pinia';

const userinfoConst = {
    ID: "userinfo.id",
    USERNAME: "userinfo.username",
    AUTH: "userinfo.auth",
    EMAIL: "userinfo.email",
    AVATAR: "userinfo.avatar",
}

export const useUserInfoStore = defineStore('userinfo', {
  state: () => ({
      id: localStorage.getItem(userinfoConst.ID) || null,
      username: localStorage.getItem(userinfoConst.USERNAME) || null,
      auth: Number(localStorage.getItem(userinfoConst.AUTH)) || null,
      email: localStorage.getItem(userinfoConst.EMAIL) || null,
      avatar: localStorage.getItem(userinfoConst.AVATAR) || null,
  }),
    actions: {
        update({id, username, auth, email, avatar}) {
            this.id = id
            this.username = username
            this.auth = auth
            this.email = email
            this.avatar = avatar
            localStorage.setItem(userinfoConst.ID, id)
            localStorage.setItem(userinfoConst.USERNAME, username)
            localStorage.setItem(userinfoConst.AUTH, auth)
            localStorage.setItem(userinfoConst.EMAIL, email)
            localStorage.setItem(userinfoConst.AVATAR, avatar)
        },
        clean() {
            localStorage.removeItem(userinfoConst.ID);
            localStorage.removeItem(userinfoConst.USERNAME);
            localStorage.removeItem(userinfoConst.AUTH);
            localStorage.removeItem(userinfoConst.EMAIL);
            localStorage.removeItem(userinfoConst.AVATAR);
        },
    },
});
