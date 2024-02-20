<script lang="ts" setup>
import { ref, onMounted } from "vue"
import Table from "@/ui/Table/Table.vue"
import { getInventoryApi, getInByIdApi } from "@/api/apis.js"
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
// iAmount
// iId
// pId
// wId
let column = [
    {
        title: "库存ID",
        key: "i_id",
        slot: "i_id",
        width: "25%",
        colWidth: "25%",
        sort: true
    },
    {
        title: "仓库",
        key: "w_name",
        slot: "w_name",
        width: "25%",
        colWidth: "25%",
        sort: true
    },
    {
        title: "产品",
        key: "p_name",
        slot: "p_name",
        width: "25%",
        colWidth: "25%",
        sort: true
    },
    {
        title: "产品型号",
        key: "p_model",
        slot: "p_model",
        width: "25%",
        colWidth: "25%",
        sort: true
    },
    {
        title: "数量",
        key: "i_amount",
        slot: "i_amount",
        width: "25%",
        colWidth: "25%",
        sort: true
    }
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
    getInventoryApi().then(res => {
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

// 仓库详情对话框
// 点击的仓库id
let wId = ref('')
let wareDialogTableVisible = ref(false)
function showWarehouseDetail(id) {
    console.log('wid:', id);
    wId.value = id
    wareDialogTableVisible.value = true
}
// 关闭时清空数据
function close() {
    pId.value = ''
    wId.value = ''
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
            <template #i_id="{ content }">{{ content.i_id }}</template>
            <!-- <template #w_name="{ content }">{{ content.w_name }}</template> -->
            <template #p_name="{ content }">
                <el-button @click="showProductDetail(content.p_id)" :icon="Link" type="success" plain>{{
                    content.p_name
                }}</el-button>
            </template>
            <template #p_model="{ content }">{{ content.p_model }}</template>
            <template #w_name="{ content }">
                <div class="product">
                    <el-button @click="showWarehouseDetail(content.w_id)" :icon="Link" type="success" plain>{{
                        content.w_name
                    }}</el-button>
                </div>
            </template>
            <template #i_amount="{ content }">
                <span
                    :style="{ 'font-size': '20px', color: content.i_amount == 0 ? 'red' : 'dodgerblue', 'font-weight': '700' }">{{
                        content.i_amount }}</span>
            </template>
        </Table>
    </div>

    <el-dialog v-model="dialogTableVisible" title="产品详情" align-center @close="close">
        <ProductListTable v-if="pId" :p-id="pId" />
    </el-dialog>
    <el-dialog v-model="wareDialogTableVisible" title="产品详情" align-center @close="close">
        <WarehouseListTable v-if="wId" :w-id="wId" />
    </el-dialog>
</template>

<style scoped>
.product {}
</style>