import axios from "axios"

export const request = axios.create({
    baseURL: "/api",
    timeout: 5000,
})

// 请求拦截器
request.interceptors.request.use(
    (config) => {
        console.log('发送请求成功:', config);
        return config
    },
    (error) => {
        console.log('发送请求失败:', error);
        return Promise.reject(error)
    }
)
// 响应拦截器
request.interceptors.response.use(
    (response) => {
        console.log('响应成功:', response);
        return response
    },
    (error) => {
        console.log('响应失败:', error);
        return Promise.reject(error)
    }
)