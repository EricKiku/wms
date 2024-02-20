<script lang="ts" setup>
import { ref, computed } from "vue"
// 引入权限判断
import { useStore } from "../../stores/store.js"
import MenuItem from "../../ui/Menu/MenuItem.vue";
import { useRoute, useRouter } from "vue-router"
const route = useRoute()
const router = useRouter()
const store = useStore()
// 处理路由跳转
function jump(path: string) {
    router.push({
        name: path
    })
}
// 获取菜单
const menus = computed(() => {
    return store.getMenu('admin')
})
</script>

<template>
    <div class="sider">
        <div class="logo">
            <img src="/src/assets/icons/仓库.png" alt="">
            <span> <span class="first">W</span> MS</span>
        </div>
        <MenuItem v-for="(menu) in menus" :key="menu.mid" :icons="menu.icon" :title="menu.title"
            :selected="route.path == menu.url" @click.stop="jump(menu.name)">
        <div class="children" v-for="(child) in menu.children" :key="child.mid">
            <MenuItem style="flex: 1;" :icons="child.icon" :title="child.title" :selected="route.path == child.url"
                @click.stop="jump(child.name)">
            </MenuItem>
        </div>
        </MenuItem>
        <img style="width: 240px;" src="/src/assets/img/element.png" alt="">
    </div>
</template>

<style scoped lang="less">
.sider {
    width: 250px;
    padding: 10px;
    border-right: 1px solid #e0e0e0;
    height: 100%;
    box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em, rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
    transition: all 0.3s;
    align-items: center;
    box-sizing: border-box;

    .logo {
        height: 75px;
        display: flex;
        align-items: center;
        justify-content: left;
        padding-bottom: 5px;
        border-bottom: 1px solid #e0e0e0;

        >span {
            font-size: 20px;
            font-weight: 600;
            margin-left: 10px;
            font-family: "Arial Black", Gadget, sans-serif;

            .first {
                color: #409EFF;
                font-family: "STXingkai";
                font-size: 50px;
            }
        }
    }

    // >div {
    //     &:nth-last-child(1) {
    //         border-bottom: 3px solid #ccc;
    //     }
    // }

    .children {
        display: flex;
        justify-content: center;
        align-items: center;

        img {
            width: 20px;
            height: 20px;
            margin-left: 10px;
        }
    }
}
</style>