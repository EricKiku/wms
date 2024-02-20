<script lang="ts" setup>
import { ref } from "vue"
import { Plus, Close, Search, Refresh } from '@element-plus/icons-vue'
import { color } from "@/config/style/color.js"
import Table from "../../ui/Table/Table.vue"
import message from "@/tools/message"
import { useStore } from "@/stores/store"
import { addWarehouseApi, deleteWarehouseApi } from "@/api/apis"
import WarehouseListTable from "@/components/WarehouseListTable.vue"
import { ElMessage, ElMessageBox } from 'element-plus'
const store = useStore()


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
let warehouselistDom = ref()
// 提交入库
function handleSubmit() {
    // 入库编号，日期时间戳
    let inCode = new Date().getTime() + ''
    let uId = store.getUser.uId
    console.log('提交入库')
    // 判断四个字段是否为空
    if (warehouseName.value === '' || warehouseAddress.value === '' || warehouseCapacity.value === 0) {
        ElMessage({
            type: 'error',
            message: '请填写完整信息',
        })
        return
    }
    ElMessageBox.confirm(
        '确定提交吗?',
        'Warning',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            addWarehouseApi({
                wName: warehouseName.value,
                wAddress: warehouseAddress.value,
                wCapacity: warehouseCapacity.value,
                wState: warehouseStatus.value,
                wCharge: uId,
                wInventory: 0
            }).then(res => {
                if (res.data.code === 200) {
                    message('添加成功', 'success')
                    warehouselistDom.value.refresh()
                    addDialogShow.value = false
                }
            })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消操作',
            })
        })

}
// 点击刷新
function handleRefresh() {
    warehouselistDom.value.refresh()
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
            for (const product of warehouselistDom.value.table.selectedProducts) {
                deleteWarehouseApi(product).then(res => {
                    if (res.data.code === 200) {
                        warehouselistDom.value.refresh()
                        // 清空选中
                        warehouselistDom.value.table.selected = []
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
// 搜索
let searchKey = ref('')
function handleSearch() {
    warehouselistDom.value.search(searchKey.value)
}
</script>

<template>
    <div class="modelIntroduct">
        <div class="modelTitle">
            <img src="/src/assets/icons/kucun.png" alt="">
            <h2>仓库管理</h2>
        </div>
        <p>查看所有仓库，仓库的地址，容量，状态，库存信息</p>
    </div>
    <el-card shadow="never">
        <div class="optionsGroup">
            <el-button @click="handleRefresh" class="item" type="primary" :icon="Refresh">刷新</el-button>
            <el-button @click="addDialogShow = true" class="item" type="primary" :icon="Plus">添加</el-button>
            <el-input @keyup.enter="handleSearch" v-model="searchKey" style="width: 300px;" placeholder="输入关键字回车查询"
                clearable :prefix-icon="Search" />
            <el-button @click="handleDeleteIn" type="danger" text>删除</el-button>
        </div>
        <WarehouseListTable ref="warehouselistDom" />
    </el-card>


    <el-dialog v-model="addDialogShow" width="auto" :show-close="false" align-center>
        <!-- 头部 -->
        <template #header="{ close, titleId, titleClass }">
            <div class="dia-header">
                <h4 :id="titleId" :class="titleClass">
                    创建仓库
                </h4>
                <el-button @click="close" type="danger" plain class="closeBtn" :icon="Close" size="small" />
            </div>
        </template>
        <!-- 内容 -->
        <div class="dia-container">
            <div :style="{ color }" class="title">仓库信息</div>
            <div class="dia-row dia-baseinfo">
                <div class="col">
                    <div class="label"> <span class="require">*</span> 仓库编号</div>
                    <el-input disabled placeholder="自动生成" />
                </div>
                <div class="col">
                    <div class="label"> <span class="require">*</span> 仓库名称</div>
                    <el-input v-model="warehouseName" />
                </div>
            </div>

            <div class="dia-row dia-baseinfo">
                <div class="col">
                    <div class="label"> <span class="require">*</span> 仓库地址</div>
                    <el-input v-model="warehouseAddress" />
                </div>
                <div class="col">
                    <div class="label"> <span class="require">*</span> 仓库容量/立方</div>
                    <el-input-number v-model="warehouseCapacity" :min="1" />
                </div>

            </div>
            <div :style="{ color }" class="title">仓库状态</div>
            <div class="dia-row dia-baseinfo">
                <el-radio-group v-model="warehouseStatus">
                    <el-radio label="true">
                        <el-tag type="success">启用</el-tag>
                    </el-radio>
                    <el-radio label="false">
                        <el-tag type="danger">停用</el-tag>
                    </el-radio>
                </el-radio-group>
            </div>
            <div :style="{ color }" class="title">库管信息</div>
            <div class="dia-row dia-baseinfo">
                <div class="col">
                    <div class="label"> <span class="require">*</span> 库管</div>
                    <el-input :value="store.getUser.uName" disabled />
                </div>

            </div>
        </div>
        <!-- 底部 -->
        <template #footer>
            <div class="footer">
                <el-button @click="handleSubmit" style="width: 100px;" type="primary">提交</el-button>
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