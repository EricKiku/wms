<script lang="ts" setup>
import { ref, onMounted } from "vue"
import Table from "@/ui/Table/Table.vue"
import { getInApi, getInByIdApi } from "@/api/apis.js"
import ProductListTable from "@/components/ProductListTable.vue";
import WarehouseListTable from "./WarehouseListTable.vue";
import EmployeeListTable from "./EmployeeListTable.vue";
import { Link } from '@element-plus/icons-vue'
interface Column {
    title: string;
    key: string;
    slot: string;
    width: string;
    colWidth: string;
    sort: boolean;
}
const props = defineProps(['inId'])
let data = ref()
let tempData = ref()
let column = [
    {
        title: "入库ID",
        key: "inId",
        slot: "inId",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
    {
        title: "入库编号",
        key: "inCode",
        slot: "inCode",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
    {
        title: "入库类型",
        key: "inType",
        slot: "inType",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
    {
        title: "入库仓库ID",
        key: "inWarehouse",
        slot: "inWarehouse",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
    {
        title: "入库产品ID",
        key: "pId",
        slot: "pId",
        width: "12.5%",
        colWidth: "12.5%",
        sort: false
    },
    {
        title: "本次入库数量",
        key: "inAmount",
        slot: "inAmount",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
    {
        title: "入库日期",
        key: "inDate",
        slot: "inDate",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
    {
        title: "入库人员",
        key: "uId",
        slot: "uId",
        width: "12.5%",
        colWidth: "12.5%",
        sort: true
    },
]

onMounted(() => {
    if (props.inId) {
        getInByIdApi(props.inId).then(res => {
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
    getInApi().then(res => {
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

// 仓库详情对话框
// 点击的仓库id
let wId = ref('')
let wareDialogTableVisible = ref(false)
function showWarehouseDetail(id) {
    wId.value = id
    wareDialogTableVisible.value = true
}
// 角色详情对话框
// 点击的角色id
let uId = ref('')
let employeeDialogTableVisible = ref(false)
function showemployeehouseDetail(id) {
    uId.value = id
    employeeDialogTableVisible.value = true
}

// 关闭时清空数据
function close() {
    pId.value = ''
    wId.value = ''
    uId.value = ''
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
            <template #inId="{ content }">{{ content.inId }}</template>
            <template #inCode="{ content }">{{ content.inCode }}</template>
            <template #inType="{ content }">{{ content.inType }}</template>
            <template #inWarehouse="{ content }">
                <div class="product">
                    <el-button @click="showWarehouseDetail(content.inWarehouse)" :icon="Link" type="success" plain>{{
                        content.inWarehouse
                    }}</el-button>
                </div>
            </template>
            <template #pId="{ content }">
                <div class="product">
                    <el-button @click="showProductDetail(content.pId)" :icon="Link" type="success" plain>{{
                        content.pId
                    }}</el-button>
                </div>
            </template>
            <template #inAmount="{ content }">{{ content.inAmount }}</template>
            <template #inDate="{ content }">{{ content.inDate }}</template>
            <template #uId="{ content }">
                <el-button @click="showemployeehouseDetail(content.uId)" :icon="Link" type="success" plain>{{
                    content.uId
                }}</el-button>
            </template>
        </Table>
    </div>


    <el-dialog v-model="dialogTableVisible" title="产品详情" align-center @close="close">
        <ProductListTable v-if="pId" :p-id="pId" />
    </el-dialog>
    <el-dialog v-model="wareDialogTableVisible" title="产品详情" align-center @close="close">
        <WarehouseListTable v-if="wId" :w-id="wId" />
    </el-dialog>
    <el-dialog v-model="employeeDialogTableVisible" title="角色详情" align-center @close="close">
        <EmployeeListTable v-if="uId" :u-id="uId" />
    </el-dialog>
</template>

<style scoped>
.product {}
</style>