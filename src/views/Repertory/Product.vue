<!-- 产品管理 -->
<script lang="ts" setup>
import { ref } from "vue"
import { Plus, Close, Search, Refresh } from '@element-plus/icons-vue'
import ProductListTable from "@/components/ProductListTable.vue"
import { addProductApi, deleteProductApi } from "@/api/apis"
import { ElMessage, ElMessageBox } from 'element-plus'
import { color } from "@/config/style/color.js"
// 添加对话框
let addDialogShow = ref(false)
// 产品对象
let product = ref({
    pId: '',
    pProp: '',
    pType: '',
    pName: '',
    pBrand: '',
    pModel: '',
    pUnit: '',
    pPrice: '',
    pCost: ''
})

// 表格dom
let productList = ref()

// 添加产品
function addProduct() {
    ElMessageBox.confirm(
        '确定要提交以上数据吗?',
        '提示',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            addProductApi(product.value).then(res => {
                console.log(res)
                if (res.data.code === 200) {
                    addDialogShow.value = false
                    productList.value.refresh()
                    // 清空数据
                    product.value = {
                        pId: '',
                        pProp: '',
                        pType: '',
                        pName: '',
                        pBrand: '',
                        pModel: '',
                        pUnit: '',
                        pPrice: '',
                        pCost: ''
                    }
                    ElMessage({
                        type: 'success',
                        message: '提交成功',
                    })
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
    productList.value.refresh()
}
// 删除产品
function deleteProduct() {
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
            // 遍历选中的产品，依次删除
            for (const pid of productList.value.table.selected) {
                deleteProductApi(pid).then(res => {
                    if (res.data.code === 200) {
                        productList.value.refresh()
                        // 清空选中
                        productList.value.table.selected = []
                        ElMessage({
                            type: 'success',
                            message: '删除成功',
                        })
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
    productList.value.search(searchKey.value)
}
</script>

<template>
    <div class="modelIntroduct">
        <div class="modelTitle">
            <img src="/src/assets/icons/product.png" alt="">
            <h2>产品管理</h2>
        </div>
        <p>对所有出入库的产品管理，有添加，编辑，搜索，删除功能</p>
    </div>
    <el-card shadow="never">
        <div class="optionsGroup">
            <el-button @click="handleRefresh" class="item" type="primary" :icon="Refresh">刷新</el-button>
            <el-button @click="addDialogShow = true" class="item" type="primary" :icon="Plus">添加</el-button>
            <el-input @keyup.enter="handleSearch" v-model="searchKey" style="width: 300px;" placeholder="输入关键字回车查询"
                clearable :prefix-icon="Search" />
            <el-button @click="deleteProduct" type="danger" text>删除</el-button>
        </div>
        <!-- 表格 -->
        <ProductListTable ref="productList" />
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
            <div :style="{ color }" class="title">产品编码</div>
            <div class="dia-row dia-baseinfo">
                <div class="col">
                    <div class="label"> <span class="require">*</span> 产品编号</div>
                    <el-input disabled placeholder="自动生成" />
                </div>
            </div>
            <div :style="{ color }" class="title">产品信息</div>
            <div class="dia-row dia-baseinfo">
                <div class="col">
                    <div class="label"> <span class="require">*</span> 产品属性</div>
                    <el-select v-model="product.pProp" class="m-2" style="width: 240px" placeholder="">
                        <el-option label="成品" value="成品" />
                        <el-option label="零件" value="零件" />
                    </el-select>
                </div>
                <div class="col">
                    <div class="label"> <span class="require">*</span> 产品类型</div>
                    <el-input v-model="product.pType" />
                </div>
                <div class="col">
                    <div class="label"> <span class="require">*</span> 产品名称</div>
                    <el-input v-model="product.pName" />
                </div>
            </div>
            <div class="dia-row dia-baseinfo">
                <div class="col">
                    <div class="label"> <span class="require">*</span> 品牌</div>
                    <el-input v-model="product.pBrand" />
                </div>
                <div class="col">
                    <div class="label"> <span class="require">*</span> 规格型号</div>
                    <el-input v-model="product.pModel" />
                </div>
                <div class="col">
                    <div class="label"> <span class="require">*</span> 单位</div>
                    <el-select v-model="product.pUnit" class="m-2" style="width: 240px" placeholder="">
                        <el-option label="台" value="台" />
                        <el-option label="套" value="套" />
                        <el-option label="件" value="件" />
                        <el-option label="个" value="个" />
                        <el-option label="盒" value="盒" />
                        <el-option label="箱" value="箱" />
                    </el-select>
                </div>
            </div>
            <div class="dia-row dia-baseinfo">
                <div class="col">
                    <div class="label"> <span class="require">*</span> 成本单价/元</div>
                    <el-input-number v-model="product.pCost" :min="1" />
                </div>
                <div class="col">
                    <div class="label"> <span class="require">*</span> 销售单价/元</div>
                    <el-input-number v-model="product.pPrice" :min="1" />
                </div>
            </div>
        </div>
        <!-- 底部 -->
        <template #footer>
            <div class="footer">
                <el-button @click="addProduct" style="width: 100px;" type="primary">提交</el-button>
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