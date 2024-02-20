<script lang="ts" setup>
import { ref, onMounted } from "vue"
import { bgColor, unSelectedColor, color } from "../config/style/color"
import Input from "../ui/Input/Input.vue"
import Button from "../ui/Button/Button.vue"
import Checkbox from "../ui/Checkbox/Checkbox.vue"
import lottie from "lottie-web"
import right from "../assets/json/job.json"
// 加载lottie动画
let container_right = ref(null)
onMounted(() => {
    //在这个生命周期渲染

    // console.log(import.meta.env)
    lottie.loadAnimation({
        container: container_right.value, //选择渲染dom
        renderer: "svg", //渲染格式
        loop: false, //循环播放
        autoplay: true, //是否i自动播放,
        animationData: right, //渲染动效json
    });
})

// 登录请求
import { loginApi } from "../api/apis"
import message from "../tools/message"
import { useRouter } from "vue-router"
import { useStore } from "../stores/store"
const router = useRouter()
const store = useStore()
// 登录变量
let account = ref("admin")
let password = ref("admin")
let remember = ref(false)

let loading = ref(false)
function handleClick() {
    loading.value = true
    loginApi(account.value, password.value).then((res) => {
        if (res.data.code === 200) {
            message("登录成功,正在跳转")
            // 如果用户选择了记住我，则存储用户信息
            if (remember.value) {
                localStorage.setItem("account", JSON.stringify(res.data.data))
            } else {
                localStorage.removeItem("account")
            }
            // 存储用户信息
            store.setUser(res.data.data)
            // 登录成功后跳转到首页
            router.push("/")
        } else {
            message(res.data.msg, 'error')
        }
        loading.value = false
    }).catch((err) => {
        message('错误', 'error')
        loading.value = false
    })
}
</script>

<template>
    <div class="login">
        <div class="container" :style="{ border: `1px solid ${bgColor}` }">
            <div class="container_left">
                <h1>Sign In</h1>
                <p :style="{ color: unSelectedColor }">登录以使用系统</p>
                <Input v-model="account" placeholder="用户名" width="100%" />
                <Input v-model="password" placeholder="密码" width="100%" />
                <div style="display: flex;justify-content: space-between;height: 40px;line-height: 40px;">
                    <Checkbox v-model="remember" label="记住我" />
                    <!-- <span :style="{ color, cursor: 'pointer' }">忘记密码?</span> -->
                </div>
                <Button :loading="loading" @onClick="handleClick" label="登录" type="default" width="200px"
                    :textStyle="{ 'letter-spacing': '5px', 'font-size': '14px' }" />
            </div>
            <div ref="container_right" class="container_right"></div>
        </div>
    </div>
</template>

<style scoped lang="less">
.login {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-image: url("/src/assets/img/loginBg.jpg");
    /* 背景图垂直、水平均居中 */
    background-position: center center;
    /* 背景图不平铺 */
    background-repeat: no-repeat;
    /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
    background-attachment: fixed;
    /* 让背景图基于容器大小伸缩 */
    background-size: cover;
    /* 设置背景颜色，背景图加载过程中会显示背景色 */
    background-color: white;

    .container {
        width: 800px;
        // height: 500px;
        border-radius: 10px;
        box-shadow: rgba(60, 64, 67, 0.3) 0px 1px 2px 0px, rgba(60, 64, 67, 0.15) 0px 1px 3px 1px;
        box-sizing: border-box;
        padding: 40px 20px;
        display: flex;

        .container_left {
            flex: 1;
            display: flex;
            flex-direction: column;

            h1 {
                font-weight: 700;
            }

            p {
                margin-top: 10px;
            }
        }

        .container_right {
            flex: 1;
        }

    }


}
</style>