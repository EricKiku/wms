<script lang="ts" setup>
import { ref } from "vue"
import { Plus, Close, Search, Refresh } from '@element-plus/icons-vue'
import { color } from "@/config/style/color.js"
import Table from "../../ui/Table/Table.vue"
import message from "@/tools/message"
import { useStore } from "@/stores/store"
import { addWarehouseApi, deleteWarehouseApi } from "@/api/apis"
import InventoryListTable from "@/components/InventoryListTable.vue"
import { ElMessage, ElMessageBox } from 'element-plus'
const store = useStore()
import { useRoute, useRouter } from "vue-router"
const router = useRouter()
// 添加对话框
import { CircleCloseFilled } from '@element-plus/icons-vue'
import { getWarehouseApi } from "@/api/apis"
import ProductTable from "@/components/ProductTable.vue"
let addDialogShow = ref(false)
// 对话框数据
// 仓库名称
let warehouseName = ref('')
// 仓库地址
let warehouseAddress = ref('')
// 仓库容量
let warehouseCapacity = ref(0)
// 仓库状态
let warehouseStatus = ref('true')

// dom
let inventoryListDom = ref()
// 点击刷新
function handleRefresh() {
    inventoryListDom.value.refresh()
}

function toPage(name) {
    router.push({ name })
}
// 搜索
let searchKey = ref('')
function handleSearch() {
    inventoryListDom.value.search(searchKey.value)
}
</script>

<template>
    <div class="modelIntroduct">
        <div class="modelTitle">
            <img src="/src/assets/icons/inventory.png" alt="">
            <h2>库存查询</h2>
        </div>
        <p>查询所有仓库的库存情况</p>
    </div>
    <el-card shadow="never">
        <div class="optionsGroup">
            <el-button @click="toPage('in')" class="item" type="primary" :icon="Plus">去入库</el-button>
            <el-button @click="toPage('out')" class="item" type="primary" :icon="Plus">去出库</el-button>
            <el-button @click="handleRefresh" class="item" type="primary" :icon="Refresh">刷新</el-button>
            <el-input @keyup.enter="handleSearch" v-model="searchKey" style="width: 300px;" placeholder="输入关键字回车查询"
                clearable :prefix-icon="Search" />
        </div>
        <InventoryListTable ref="inventoryListDom" />
    </el-card>
</template>

<style scoped lang="less">
.modelIntroduct {
    margin-bottom: 20px;

    .modelTitle {
        display: flex;
        align-items: center;
        margin-bottom: 20px;

        img {
            width: 35px;
            height: 35px;
            margin-right: 10px;

        }
    }

    p {
        margin: 0;
        color: #676E8A;
        line-height: 40px;
    }
}

.optionsGroup {
    display: flex;
    align-items: center;
    margin-bottom: 20px;

    .item {
        margin-right: 10px;
    }
}

.dia-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 2px solid #ccc;
    padding-bottom: 10px;

    h4 {
        margin: 0;
    }

}

.dia-container {
    padding: 10px;
    display: flex;
    flex-direction: column;
    // height: 300px;
    max-height: 500px;
    overflow: auto;

    .dia-row {
        margin: 10px 0px 20px 0px;

    }

    .dia-baseinfo {
        display: flex;
        justify-content: space-between;

        .col {
            flex: 1;
            margin: 0 10px;
        }
    }

    .title {
        border-bottom: 1px dashed #ccc;
        font-weight: 700;
        font-size: 16px;
        font-weight: bold;
    }


}

.footer {
    display: flex;
    justify-content: start;
    border-top: 2px solid #ccc;
    padding-top: 10px;
}
</style>