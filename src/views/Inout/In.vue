<script lang="ts" setup>
import { ref } from "vue"
import { Plus, Close, Search, Refresh } from '@element-plus/icons-vue'
import { color } from "@/config/style/color.js"
import Table from "../../ui/Table/Table.vue"
import message from "@/tools/message"
import { useStore } from "@/stores/store"
import { addInApi, deleteInApi } from "@/api/apis"
import InKuListTable from "@/components/InKuListTable.vue"
import { ElMessage, ElMessageBox } from 'element-plus'
const store = useStore()


// 添加对话框
import { CircleCloseFilled } from '@element-plus/icons-vue'
import { getWarehouseApi } from "@/api/apis"
import ProductTable from "@/components/ProductTable.vue"
let addDialogShow = ref(false)
// 对话框数据
// 入库类型
let inType = ref('')
// 入库仓库
let inWarehouse = ref()
// 产品
let products = ref([
    {
        tempId: 1,
        pName: "",
        pId: "",
        pBrand: "",
        pModel: "",
        pUnit: "",
        amount: 0,
        pPrice: 0,
        pCost: 0
    }
])
// 入库时间
let inTime = ref('')
// dom
let inkulist = ref()
// 提交入库
function handleSubmitIn() {
    // 入库编号，日期时间戳
    let inCode = new Date().getTime() + ''
    let uId = store.getUser.uId
    console.log('提交入库')
    for (const product of products.value) {
        if (!inType.value || !inWarehouse.value || !uId) {
            message('产品信息不完整', 'error')
            return
        }
        addInApi({
            inCode,
            inType: inType.value,
            inWarehouse: inWarehouse.value,
            pId: product.pId,
            inAmount: product.amount,
            inDate: inTime.value,
            uId,
        }).then(res => {
            message('入库成功', 'success')
            inkulist.value.refresh()
            addDialogShow.value = false
        })
    }


}
// 点击刷新
function handleRefresh() {
    inkulist.value.refresh()
}

// 删除入库记录
function handleDeleteIn() {
    ElMessageBox.confirm(
        '确定删除选中的数据吗?',
        'Warning',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            for (const product of inkulist.value.table.selectedProducts) {
                deleteInApi(product.inId).then(res => {
                    if (res.data.code === 200) {
                        inkulist.value.refresh()
                        // 清空选中
                        inkulist.value.table.selected = []
                        message('删除成功', 'success')
                    }
                })
            }
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消操作',
            })
        })

}


// 可入库仓库
let warehouseList = ref([])
// 获取仓库方法
let getWarehouseLoading = ref(false)
function handleWarehouse() {
    getWarehouseLoading.value = true
    getWarehouseApi().then(res => {
        warehouseList.value = res.data.data
        getWarehouseLoading.value = false
    }).catch(err => {
        getWarehouseLoading.value = false
        message('获取仓库失败', 'error')
    })
}


// 搜索
let searchKey = ref('')
function handleSearch() {
    inkulist.value.search(searchKey.value)
}
</script>

<template>
    <div class="modelIntroduct">
        <div class="modelTitle">
            <img src="/src/assets/icons/inku.png" alt="">
            <h2>入库管理</h2>
        </div>
        <p>用于对入库产品的记录管理，有展示入库记录，添加入库记录，搜索和删除的功能</p>
    </div>
    <el-card shadow="never">
        <div class="optionsGroup">
            <el-button @click="handleRefresh" class="item" type="primary" :icon="Refresh">刷新</el-button>
            <el-button @click="addDialogShow = true" class="item" type="primary" :icon="Plus">添加</el-button>
            <el-input @keyup.enter="handleSearch" v-model="searchKey" style="width: 300px;" placeholder="输入关键字回车查询"
                clearable :prefix-icon="Search" />
            <el-button @click="handleDeleteIn" type="danger" text>删除</el-button>
        </div>
        <InKuListTable ref="inkulist" />
    </el-card>


    <el-dialog v-model="addDialogShow" width="auto" :show-close="false" align-center>
        <!-- 头部 -->
        <template #header="{ close, titleId, titleClass }">
            <div class="dia-header">
                <h4 :id="titleId" :class="titleClass">
                    入库清单
                </h4>
                <el-button @click="close" type="danger" plain class="closeBtn" :icon="Close" size="small" />
            </div>
        </template>
        <!-- 内容 -->
        <div class="dia-container">
            <div :style="{ color }" class="title">基本信息</div>
            <div class="dia-row dia-baseinfo">
                <div class="col">
                    <div class="label"> <span class="require">*</span> 入库编号</div>
                    <el-input disabled placeholder="自动生成" />
                </div>
                <div class="col">
                    <div class="label"> <span class="require">*</span> 入库类型</div>
                    <el-select v-model="inType" class="m-2" placeholder="入库类型" style="width: 240px">
                        <el-option label="采购入库" value="采购入库" />
                        <el-option label="借入入库" value="借入入库" />
                        <el-option label="销售退货入库" value="销售退货入库" />
                        <el-option label="其他" value="其他" />
                    </el-select>
                </div>
                <div class="col">
                    <div class="label"> <span class="require">*</span> 入库仓库</div>
                    <el-select :loading="getWarehouseLoading" loading-text="加载中..." @visible-change="handleWarehouse"
                        v-model="inWarehouse" class="m-2" placeholder="Select" style="width: 240px">
                        <el-option v-for="(warehouse) in warehouseList" :key="warehouse.wId" :label="warehouse.wName"
                            :value="warehouse.wId" />
                    </el-select>
                </div>
            </div>
            <div :style="{ color }" class="title">入库产品信息</div>
            <div class="dia-row dia-baseinfo">
                <ProductTable v-model="products" />
            </div>
            <div :style="{ color }" class="title">入库确认</div>
            <div class="dia-row dia-baseinfo">
                <div class="col">
                    <div class="label"> <span class="require">*</span> 入库时间</div>
                    <el-date-picker v-model="inTime" type="date" placeholder="Pick a Date" format="YYYY/MM/DD"
                        value-format="YYYY-MM-DD" />
                </div>
                <div class="col">
                    <div class="label"> <span class="require">*</span> 入库人</div>
                    <el-input disabled :value="store.getUser.uName" style="width: 200px;" placeholder="入库人" />
                </div>
            </div>
        </div>
        <!-- 底部 -->
        <template #footer>
            <div class="footer">
                <el-button @click="handleSubmitIn" style="width: 100px;" type="primary">提交</el-button>
            </div>
        </template>
    </el-dialog>
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