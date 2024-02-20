<script lang="ts" setup>
import { ref, onMounted } from "vue"
import Table from "@/ui/Table/Table.vue"
import { getWarehouseApi, getWarehouseByIdApi } from "@/api/apis.js"
import ProductListTable from "@/components/ProductListTable.vue";
interface Column {
    title: string;
    key: string;
    slot: string;
    width: string;
    colWidth: string;
    sort: boolean;
}
const props = defineProps(['wId'])
let data = ref()
let tempData = ref()
let column = [
    {
        title: "仓库ID",
        key: "wId",
        slot: "wId",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
    {
        title: "仓库名",
        key: "wName",
        slot: "wName",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
    {
        title: "仓库地址",
        key: "wAddress",
        slot: "wAddress",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
    {
        title: "仓库容量/立方",
        key: "wCapacity",
        slot: "wCapacity",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
    {
        title: "仓库状态",
        key: "wState",
        slot: "wState",
        width: "12.5%",
        colWidth: "12.5%",
        sort: false
    },
    {
        title: "仓库管理员ID",
        key: "wCharge",
        slot: "wCharge",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
    {
        title: "仓库库存",
        key: "wInventory",
        slot: "wInventory",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
]

onMounted(() => {
    if (props.wId) {
        getWarehouseByIdApi(props.wId).then(res => {
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
    getWarehouseApi().then(res => {
        data.value = res.data.data
        console.log(data.value);
        tempData.value = res.data.data
        loading.value = false
    })
}
// 搜索
function search(value) {
    console.log('value:', value);
    // 对tempData的所有字段进行搜索
    tempData.value = data.value.filter(item => {
        for (let key in item) {
            // 如果item[key]为null或者undefined，就不进行搜索
            if (item[key] === null || item[key] === undefined) {
                continue
            }
            if (item[key].toString().includes(value)) {
                return true
            }

        }
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
    table,
    search
})
</script>

<template>
    <div v-loading="loading">
        <Table ref="table" :columns="column" v-model:dataSource="tempData">
            <template #wId="{ content }">{{ content.wId }}</template>
            <template #wName="{ content }">{{ content.wName }}</template>
            <template #wAddress="{ content }">{{ content.wAddress }}</template>
            <template #wCapacity="{ content }">{{ content.wCapacity }}</template>
            <template #wState="{ content }">
                <el-tag size="large" v-if="content.wState == 'true'" type="success">正常</el-tag>
                <el-tag size="large" v-else type="danger">异常</el-tag>
            </template>
            <template #wCharge="{ content }">{{ content.wCharge }}</template>
            <template #wInventory="{ content }">{{ content.wInventory }}</template>
        </Table>
    </div>
</template>

<style scoped>
.product {}
</style>