export function set_user_info(userinfo) {
  localStorage.setItem("userinfo", JSON.stringify(userinfo));
}

export function get_user_info() {
  return localStorage.getItem("userinfo");
}

export function set_identity(identity) {
  localStorage.setItem("identity", identity);
}

export function get_identity() {
  return localStorage.getItem("identity");
}