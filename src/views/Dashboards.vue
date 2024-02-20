<script lang="ts" setup>
import { ref, onMounted } from "vue"
import Card from "../ui/Container/Card.vue"
import { useRouter, useRoute } from "vue-router"
import InoutSteam from "@/components/InoutSteam.vue"
import message from "@/tools/message"
const router = useRouter()
const route = useRoute()
function toPage(name) {
    router.push({
        name
    })
}
let loading = ref(false)
onMounted(() => {
    loading.value = true
    setTimeout(() => {
        if (loading.value) {
            loading.value = false
            message('加载超时,请检查网络后重试', 'error')
        }
    }, 8000);
})
// 全部内容加载完成后
function handleLoad() {
    loading.value = false
}
</script>

<template>
    <div class="data" v-loading="loading">
        <el-card>
            <div class="quick_access">
                <div class="access" @click="toPage('in')">
                    <img src="/src/assets/icons/inku_access.png" alt="">
                    <span>入库单</span>
                </div>
                <div class="access" @click="toPage('out')">
                    <img src="/src/assets/icons/outku_access.png" alt="">
                    <span>出库单</span>
                </div>
                <div class="access" @click="toPage('product')">
                    <img src="/src/assets/icons/product_access.png" alt="">
                    <span>产品统计</span>
                </div>
                <div class="access" @click="toPage('inventory')">
                    <img src="/src/assets/icons/inventory_access.png" alt="">
                    <span>库存统计</span>
                </div>
            </div>
        </el-card>
        <InoutSteam @handleLoad="handleLoad" />
    </div>
</template>

<style scoped lang="less">
.quick_access {
    display: flex;
    justify-content: space-around;
    font-size: 20px;
    font-weight: 600;
    color: #333;

    .access {
        cursor: pointer;
        height: 100px;
        width: 140px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        border: 1px solid #ebeef5;
        border-radius: 5px;
        transition: all 0.3s;

        &:hover {
            color: #409EFF;
            //阴影
            box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.1);
        }
    }
}
</style>