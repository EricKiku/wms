<!-- 产品表格组件 -->
<script lang="ts" setup>
import { ref, computed } from "vue"
import Input from "@/ui/Input/Input.vue";
import { Delete, Link } from '@element-plus/icons-vue'
import InventoryListTable from "./InventoryListTable.vue";
let products: any = defineModel()
// 产品总数
const total = computed(() => {
    // 遍历所有产品，把amount相加
    return products.value.reduce((prev, current) => {
        return prev + current.amount
    }, 0)
})
// 给当前行填充数据
function writeDataToRow(data) {
    console.log('填充:', data);
    products.value[selectedLinkRow.value] = {
        tempId: new Date().getTime(),
        pName: data.pName || '',
        pId: data.pId || '',
        pBrand: data.pBrand || '',
        pModel: data.pModel || '',
        pUnit: data.pUnit || '',
        amount: 1,
        pPrice: data.pPrice || 0,
        pCost: data.pCost || 0
    }
}
// 添加一行，并且临时id为当前时间戳
const addProduct = (data) => {
    products.value.push({
        tempId: new Date().getTime(),
        pName: data.pName || '',
        pId: data.pId || '',
        pBrand: data.pBrand || '',
        pModel: data.pModel || '',
        pUnit: data.pUnit || '',
        amount: 1,
        pPrice: 0,
        pCost: 0
    })
}
// 删除一行
const removeProduct = (index: number) => {
    // 如果是最后一行，则只清空数据
    if (products.value.length === 1) {
        products.value[0] = {
            tempId: new Date().getTime(),
            pName: "",
            pId: "",
            pBrand: "",
            pModel: "",
            pUnit: "",
            amount: 0,
            pPrice: 0,
            pCost: 0
        }
        return
    }
    products.value.splice(index, 1)
}
// 全部删除
const removeAll = () => {
    products.value = [
        {
            tempId: new Date().getTime(),
            pName: "",
            pId: "",
            pBrand: "",
            pModel: "",
            pUnit: "",
            amount: 0,
            pPrice: 0,
            pCost: 0
        }
    ]
}
// 当前鼠标在第几行
let currentRow = ref(-1)


// 对话框
import ProductListTable from "@/components/ProductListTable.vue";
let selectProductDia = ref(false)
// 点击链接的行
let selectedLinkRow = ref(-1)
function handleClickLink(index) {
    selectedLinkRow.value = index
    selectProductDia.value = true
}
// dom
let productList = ref()
// 确定选择产品
function handleSelectProduct() {
    writeDataToRow(productList.value.table.selectedProducts[0])
    for (const selectProduct of productList.value.table.selectedProducts.slice(1)) {
        addProduct(selectProduct)
    }
    selectProductDia.value = false
}

// 显示库存
let inventoryDiaVis = ref(false)
function showInventoryDia() {
    inventoryDiaVis.value = true
}
</script>

<template>
    <div class="product">
        <el-button @click="showInventoryDia" type="danger" link>
            查看库存
        </el-button>
        <table>
            <thead>
                <tr>
                    <th>
                        <el-popover placement="bottom" trigger="click">
                            <div style="display: flex;">
                                <p>确定全部删除吗？</p>
                                <el-button @click="removeAll" style="" type="danger" link>确定</el-button>
                            </div>
                            <template #reference>
                                <el-icon style="cursor: pointer;">
                                    <Delete />
                                </el-icon>
                            </template>
                        </el-popover>


                    </th>
                    <th>产品编码</th>
                    <th>产品名称</th>
                    <th>品牌</th>
                    <th>规格型号</th>
                    <th>单位</th>
                    <th>本次出入库数量</th>
                    <th>销售单价/元</th>
                    <th>成本单价/元</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(product, index) in products" :key="product.tempId">
                    <td class="option" @mouseover="currentRow = index" @mouseleave="currentRow = -1">
                        <div style="display: flex;" v-show="currentRow == index">
                            <el-popover placement="bottom" trigger="click">
                                <div style="display: flex;">
                                    <p>确定删除吗？</p>
                                    <el-button style="" type="danger" link @click="removeProduct(index)">确定</el-button>
                                </div>
                                <template #reference>
                                    <div class="iconBox" style="display: flex;justify-content: space-around;">
                                        <el-icon>
                                            <Delete />
                                        </el-icon>
                                    </div>
                                </template>
                            </el-popover>
                            <el-popover placement="top-start" trigger="hover" content="使用一个已有的产品数据">
                                <template #reference>
                                    <el-icon @click="handleClickLink(index)" style="cursor: pointer;">
                                        <Link />
                                    </el-icon>
                                </template>
                            </el-popover>

                        </div>
                        <div v-show="currentRow != index">
                            {{ index + 1 }}
                        </div>

                    </td>
                    <td>
                        <el-input v-model="products[index].pId" disabled placeholder="自动生成" />
                    </td>
                    <td>
                        <el-input v-model="products[index].pName" />
                    </td>
                    <td>
                        <el-input v-model="products[index].pBrand" />
                    </td>
                    <td>
                        <el-input v-model="products[index].pModel" />
                    </td>
                    <td>
                        <el-input v-model="products[index].pUnit" placeholder="个/件/台" />
                    </td>
                    <td>
                        <el-input-number v-model="products[index].amount" :min="1" />
                    </td>
                    <td>
                        <el-input-number v-model="products[index].pPrice" :min="0" />
                    </td>
                    <td>
                        <el-input-number v-model="products[index].pCost" :min="0" />
                    </td>
                </tr>

                <!-- <tr v-for="product in products" :key="product.id">
                    <td>{{ product.name }}</td>
                    <td>{{ product.price }}</td>
                    <td>{{ product.quantity }}</td>
                    <td>
                        <button @click="removeProduct(product.id)">删除</button>
                    </td>
                </tr> -->
            </tbody>
            <el-button @click="addProduct" type="primary" link>
                添加下一行
            </el-button>

        </table>
        <div>
            选择产品总数：<span style="display: inline-block;width: 50px;background-color: #ebebeb;text-align: center;">{{ total
            }}</span>
        </div>
    </div>

    <!-- 选择产品对话框 -->
    <el-dialog v-model="selectProductDia" title="选择一个产品" align-center>
        <ProductListTable ref="productList" />
        <el-button @click="handleSelectProduct" type="primary">确定</el-button>
    </el-dialog>
    <!-- 库存对话框 -->
    <el-dialog v-model="inventoryDiaVis" title="库存" align-center>
        <InventoryListTable />
    </el-dialog>
</template>

<style scoped lang="less">
.product {
    // max-height: 300px;
    // overflow: auto;
}

table {
    border: 1px solid #ccc;
    border-radius: 5px;
    max-height: 300px;

    tbody {
        max-height: 200px;
        overflow: hidden;

    }

    th {
        background-color: #f5f5f5;
        border: 1px solid #ccc;
        padding: 5px 0px;
    }

    td {
        width: 100px;
        height: 34px;
    }

    .option {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 34px;
        border: 1px solid #ccc;
        border-radius: 5px;

        .iconBox {
            i {
                cursor: pointer;

                &:nth-child(1) {
                    margin-right: 5px;
                }

                &:nth-child(2) {
                    margin-left: 5px;
                }
            }
        }
    }
}
</style>