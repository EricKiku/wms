<script lang="ts" setup>
import { ref, onMounted } from "vue"
import Table from "@/ui/Table/Table.vue"
import { getRuleApi, getRuleByIdApi } from "@/api/apis.js"
import ProductListTable from "@/components/ProductListTable.vue";
import { useStore } from "@/stores/store"
const store = useStore()
interface Column {
    title: string;
    key: string;
    slot: string;
    width: string;
    colWidth: string;
    sort: boolean;
}
const props = defineProps(['uId'])
let data = ref()
let tempData = ref()
let column = [
    {
        title: "员工ID",
        key: "uId",
        slot: "uId",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
    {
        title: "姓名",
        key: "uName",
        slot: "uName",
        width: "25.5%",
        colWidth: "25.5%",
        sort: true
    },
    {
        title: "账号",
        key: "uAccount",
        slot: "uAccount",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
    {
        title: "密码",
        key: "uPwd",
        slot: "uPwd",
        width: "12.5%",
        colWidth: "12.5%",
        sort: false
    },
    {
        title: "权限",
        key: "uRule",
        slot: "uRule",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    }
]

onMounted(() => {
    if (props.uId) {
        getRuleByIdApi(props.uId).then(res => {
            data.value = res.data.data
            tempData.value = res.data.data
        })
    } else {
        refresh()
    }
})

let loading = ref(false)
// 刷新
function refresh() {
    loading.value = true
    getRuleApi().then(res => {
        data.value = res.data.data
        console.log(data.value);
        tempData.value = res.data.data
        loading.value = false
    })
}


// 产品详情对话框
// 点击的产品id
let pId = ref('')
let dialogTableVisible = ref(false)
function showProductDetail(id) {
    pId.value = id
    dialogTableVisible.value = true
}
// 关闭时清空pid
function close() {
    pId.value = ''
}


// 获取到表格的选中行
let table = ref()

defineExpose({
    refresh,
    table
})
</script>

<template>
    <Table v-loading="loading" ref="table" :columns="column" v-model:dataSource="tempData">
        <template #uId="{ content }">{{ content.uId }}</template>
        <template #uName="{ content }">
            {{ content.uName }}
            <span v-if="store.getUser.uId == content.uId">
                <el-tag type="primary">当前登录</el-tag>
            </span>
        </template>
        <template #uAccount="{ content }">{{ content.uAccount }}</template>
        <template #uPwd="{ content }">{{ content.uPwd }}</template>
        <template #uRule="{ content }">
            <el-tag size="large" v-if="content.uRule == 'admin'" type="success">管理员</el-tag>
            <el-tag size="large" v-else type="info">用户</el-tag>
        </template>
    </Table>
</template>

<style scoped>
.product {}
</style>