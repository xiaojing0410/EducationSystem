export function show_message_util(msg, type){
  const show_message = async() => {
    ElMessage({
      // showClose: true,
      message: msg,
      type: type,
      duration: 3000,  // 默认3s后自动关闭
    })
  }
  show_message()
}