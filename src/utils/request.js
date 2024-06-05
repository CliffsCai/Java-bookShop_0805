import axios from 'axios'
import router from "@/router"
import Cookies from 'js-cookie'

const request = axios.create({
    baseURL: 'http://localhost:9091',     //  todo    先不加/api 会有问题
    timeout : 5000
})

request.interceptors.request.use( config=> {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    // const admin = Cookies.get('admin')
    // if(!admin && router.currentRoute.path !== '/login'){      //若不加&& router.currentRoute.path !== '/login' 只有在当前不在登录页时才进行导航操作。这样就避免了重复导航到当前页面的错误。
    //     router.push('/login')
    // }

    return config


}, error => {
    return Promise.reject(error)
});

request.interceptors.response.use(
    response =>{
        let res = response.data;

        if (typeof res ==='string'){
            res = res? JSON.parse(res): res
        }
        return res;
    },
    error =>{
        console.log('err'+ error)
        return Promise.reject(error)
    }
)


export  default request