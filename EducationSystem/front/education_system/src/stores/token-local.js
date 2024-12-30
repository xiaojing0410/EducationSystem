export function set_token(token) {
  localStorage.setItem("token", token);
}

export function get_token() {
  return localStorage.getItem("token");
}