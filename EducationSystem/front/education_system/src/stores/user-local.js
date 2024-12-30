export function set_user_info(userinfo) {
  console.log(userinfo)
  localStorage.setItem("userinfo", JSON.stringify(userinfo));
}

export function get_user_info() {
  return localStorage.getItem("userinfo");
}