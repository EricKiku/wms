<script lang="ts" setup>
import { ref, computed } from "vue"
import { useRoute, useRouter } from "vue-router"
import { useStore } from "../../stores/store"
import { color } from "../../config/style/color"
const route = useRoute()
const router = useRouter()
const store = useStore()
function toLogin() {
    router.push({ name: 'login' })
}

// 用户详情显示
let showDetail = ref(false)
// 用户名
let username = computed(() => store.getUser.uName)
let rule = computed(() => store.getUser.uRule)

// 退出登录
function signOut() {
    // 清除用户信息
    localStorage.removeItem("account")
    store.setUser({})
    router.push({ name: 'login' })
}
</script>

<template>
    <div class="header">
        <!-- <button @click="toLogin">Sign In</button> -->
        <div class="title">
            <h1>仓库管理系统</h1>
        </div>
        <div class="user">
            <el-popover placement="bottom" trigger="click">
                <template #reference>
                    <img src="/src/assets/img/avatar.png" alt="">
                </template>
                <div class="detail">
                    <div class="row"><span class="label">用户名:</span> <span :style="{ color }" class="text">{{ username
                    }}</span>
                    </div>
                    <div class="row"><span class="label">权限:</span> <span :style="{ color }" class="text">{{ rule }}</span>
                    </div>
                    <el-button @click="signOut" type="danger" plain>退出用户</el-button>
                </div>
            </el-popover>


        </div>
    </div>
</template>

<style scoped lang="less">
.header {
    z-index: 999;
    width: 100%;
    height: 75px;
    display: flex;
    justify-content: right;
    align-items: center;
    box-shadow: rgba(0, 0, 0, 0.1) 0px 10px 15px -3px, rgba(0, 0, 0, 0.05) 0px 4px 6px -2px;
    box-sizing: border-box;
    padding: 0px 20px 0px 0px;
    background: rgb(253, 146, 96);
    background: linear-gradient(180deg, rgba(253, 146, 96, 1) 0%, rgba(255, 198, 171, 1) 39%, rgba(250, 250, 250, 1) 75%, rgba(247, 247, 247, 1) 100%);

    .title {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;

        h1 {
            font-family: "黑体";
            color: #466a9d;
            font-size: 24px;
            font-weight: 600;
        }
    }

    .user {
        width: 50px;
        height: 50px;
        border-radius: 50%;
        background-color: #f0f0f0;
        position: relative;

        img {
            width: 100%;
            height: 100%;
            border-radius: 50%;
            cursor: pointer;
        }
    }

    .detail {
        width: 200px;
        // height: 200px;
        position: absolute;
        background-color: white;
        right: 0;
        top: 100%;
        border-radius: 5px;
        box-shadow: rgba(0, 0, 0, 0.25) 0px 0.0625em 0.0625em, rgba(0, 0, 0, 0.25) 0px 0.125em 0.5em, rgba(255, 255, 255, 0.1) 0px 0px 0px 1px inset;
        transition: opacity 0.3s ease-in-out;
        box-sizing: border-box;
        padding: 20px;
        display: flex;
        flex-direction: column;

        .row {
            display: flex;
            justify-content: space-around;

            .label {
                width: 50px;
                text-align: right;
            }

            .text {
                flex: 1;
                text-align: center;
            }
        }

        .sign_out {
            margin-top: 20px;
            text-align: center;
            cursor: pointer;
            color: red;

            &:hover {
                color: #ff0000;
            }
        }
    }
}
</style>