import axios from 'axios'
import { get_token } from '@/stores/token-local';
// 创建axios实例
const http = axios.create({
  baseURL: '/api',
  timeout: 5000
});

// axios请求拦截器
// 一般会进行token身份验证等
http.interceptors.request.use(config => {
  const token = get_token()
  if(token){
    // config.headers.token = `xxxxxxx`
    config.headers.token = `${token}`
  }
  return config
});

// axios响应式拦截器
// 一般进行错误的统一提示，token失效的处理等
http.interceptors.response.use(
  response => {
    // 处理响应:0、2、5、7、8、40004、40005
    if(response.data.code == '0' || response.data.code == '2' || response.data.code == '5'
      || response.data.code == '7' || response.data.code == '8' 
      || response.data.code == '40004' || response.data.code == '40005'
    ){
      return response.data;
    } else {
      return "sb"
    }
  },
  error => {
    // 请求已经发出了，服务器也响应了，但返回的状态码不在2XX的范围内
    
  }
);
export default http;