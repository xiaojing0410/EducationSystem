import axios from 'axios'
import {ElMessage} from "element-plus";

//构建统一 axios 实例
export const ax = axios.create({
    baseURL: "http://100.98.120.108:8080",
    timeout: 30000, // 30s等待时间(主要是因为有 ai)
    withCredentials: true // 允许携带 cookies（包括 sessionID）
});

// 请求拦截器
ax.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("token"); // 获取 token

        if (token) {
            config.headers["token"] = token; // 直接在请求头中加上 token
        }

        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

ax.interceptors.response.use(
    resp => {
        if (resp.data.code !== 0) {
            ElMessage.error(resp.data.msg)
            throw new Error(resp.data.msg)
        }
        return resp.data;
    },
    error => {
        return Promise.reject(error);
    }
);
