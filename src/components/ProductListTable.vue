<script lang="ts" setup>
import { ref, onMounted } from "vue"
import Table from "@/ui/Table/Table.vue"
import { getProductApi, getProductByIdApi, updateProductApi } from "@/api/apis.js"
import { Edit, Close } from '@element-plus/icons-vue'
import message from "@/tools/message"
interface Column {
    title: string;
    key: string;
    slot: string;
    width: string;
    colWidth: string;
    sort: boolean;
}
const props = defineProps(['pId'])
let data = ref()
let tempData = ref()
let column = [
    {
        title: "产品编号",
        key: "pId",
        slot: "pId",
        width: "20%",
        colWidth: "20%",
        sort: true
    },
    {
        title: "产品属性",
        key: "pProp",
        slot: "pProp",
        width: "10%",
        colWidth: "10%",
        sort: false
    },
    {
        title: "产品类型",
        key: "pType",
        slot: "pType",
        width: "10%",
        colWidth: "10%",
        sort: false
    },
    {
        title: "产品名称",
        key: "pName",
        slot: "pName",
        width: "10%",
        colWidth: "10%",
        sort: false
    },
    {
        title: "产品品牌",
        key: "pBrand",
        slot: "pBrand",
        width: "10%",
        colWidth: "10%",
        sort: false
    },
    {
        title: "产品型号",
        key: "pModel",
        slot: "pModel",
        width: "10%",
        colWidth: "10%",
        sort: false
    },
    {
        title: "产品单位",
        key: "pUnit",
        slot: "pUnit",
        width: "10%",
        colWidth: "10%",
        sort: false
    },
    {
        title: "成本单价/元",
        key: "pCost",
        slot: "pCost",
        width: "20%",
        colWidth: "20%",
        sort: true
    },
    {
        title: "销售单价/元",
        key: "pPrice",
        slot: "pPrice",
        width: "20%",
        colWidth: "20%",
        sort: true
    },
]

onMounted(() => {
    if (props.pId) {
        getProductByIdApi(props.pId).then(res => {
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
    getProductApi().then(res => {
        data.value = res.data.data
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

// 编辑
//编辑内容
let editNewValue = ref("")
// 点击编辑所在的行，和所用的key
let editColRow = ref(-1)
let editColKey = ref('')
function handleEditLocal(row, key, value) {
    editColRow.value = row
    editColKey.value = key
    editNewValue.value = value
}
function handleCancelEdit() {
    editColRow.value = -1
    editColKey.value = ''

}
// 编辑提示样式
let editTipTheme = ref('dark')
let editTipLocal = ref('top')
// 回车确认编辑
function handleEnterEdit(content, key) {
    let contentTemp = content
    contentTemp[key] = editNewValue.value
    updateProductApi(contentTemp).then(res => {
        if (res.data.code === 200) {
            refresh()
            editColRow.value = -1
            editColKey.value = ''
            message('编辑成功', 'success')
        } else {
            message('编辑失败', 'error')
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
            <template #pId="{ content }">
                <span>{{ content.pId }}</span>
            </template>
            <template #pProp="{ content }">
                <span>{{ content.pProp }}</span>
            </template>
            <template #pType="{ content, row, key }">
                <el-input @keyup.enter="handleEnterEdit(content, key)" v-if="editColRow == row && editColKey == key"
                    v-model="editNewValue">
                    <template #append>
                        <el-button @click="handleCancelEdit" type="danger" :icon="Close" circle />
                    </template>
                </el-input>
                <div v-else>
                    <el-tooltip class="box-item" :effect="editTipTheme" content="点击可编辑该内容" :placement="editTipLocal">
                        <span @click="handleEditLocal(row, key, content.pType)">
                            {{ content.pType }}
                            <el-icon class="icon">
                                <Edit />
                            </el-icon>
                        </span>
                    </el-tooltip>
                </div>
            </template>
            <template #pName="{ content, row, key }">
                <el-input @keyup.enter="handleEnterEdit(content, key)" v-if="editColRow == row && editColKey == key"
                    v-model="editNewValue">
                    <template #append>
                        <el-button @click="handleCancelEdit" type="danger" :icon="Close" circle />
                    </template>
                </el-input>
                <div v-else>
                    <el-tooltip class="box-item" :effect="editTipTheme" content="点击可编辑该内容" :placement="editTipLocal">
                        <span @click="handleEditLocal(row, key, content.pName)">
                            {{ content.pName }}
                            <el-icon class="icon">
                                <Edit />
                            </el-icon>
                        </span>
                    </el-tooltip>
                </div>
            </template>
            <template #pBrand="{ content, row, key }">
                <el-input @keyup.enter="handleEnterEdit(content, key)" v-if="editColRow == row && editColKey == key"
                    v-model="editNewValue">
                    <template #append>
                        <el-button @click="handleCancelEdit" type="danger" :icon="Close" circle />
                    </template>
                </el-input>
                <div v-else>
                    <el-tooltip class="box-item" :effect="editTipTheme" content="点击可编辑该内容" :placement="editTipLocal">
                        <span @click="handleEditLocal(row, key, content.pBrand)">
                            {{ content.pBrand }}
                            <el-icon class="icon">
                                <Edit />
                            </el-icon>
                        </span>
                    </el-tooltip>
                </div>
            </template>
            <template #pModel="{ content, row, key }">
                <el-input @keyup.enter="handleEnterEdit(content, key)" v-if="editColRow == row && editColKey == key"
                    v-model="editNewValue">
                    <template #append>
                        <el-button @click="handleCancelEdit" type="danger" :icon="Close" circle />
                    </template>
                </el-input>
                <div v-else>
                    <el-tooltip class="box-item" :effect="editTipTheme" content="点击可编辑该内容" :placement="editTipLocal">
                        <span @click="handleEditLocal(row, key, content.pModel)">
                            {{ content.pModel }}
                            <el-icon class="icon">
                                <Edit />
                            </el-icon>
                        </span>
                    </el-tooltip>
                </div>
            </template>
            <template #pUnit="{ content }">
                <span>{{ content.pUnit }}</span>
            </template>
            <template #pCost="{ content, row, key }">
                <el-input @keyup.enter="handleEnterEdit(content, key)" v-if="editColRow == row && editColKey == key"
                    v-model="editNewValue">
                    <template #append>
                        <el-button @click="handleCancelEdit" type="danger" :icon="Close" circle />
                    </template>
                </el-input>
                <div v-else>
                    <el-tooltip class="box-item" :effect="editTipTheme" content="点击可编辑该内容" :placement="editTipLocal">
                        <span @click="handleEditLocal(row, key, content.pCost)">
                            {{ content.pCost }}
                            <el-icon class="icon">
                                <Edit />
                            </el-icon>
                        </span>
                    </el-tooltip>
                </div>
            </template>
            <template #pPrice="{ content, row, key }">
                <el-input @keyup.enter="handleEnterEdit(content, key)" v-if="editColRow == row && editColKey == key"
                    v-model="editNewValue">
                    <template #append>
                        <el-button @click="handleCancelEdit" type="danger" :icon="Close" circle />
                    </template>
                </el-input>
                <div v-else>
                    <el-tooltip class="box-item" :effect="editTipTheme" content="点击可编辑该内容" :placement="editTipLocal">
                        <span @click="handleEditLocal(row, key, content.pPrice)">
                            {{ content.pPrice }}
                            <el-icon class="icon">
                                <Edit />
                            </el-icon>
                        </span>
                    </el-tooltip>
                </div>
            </template>
        </Table>
    </div>
</template>

<style scoped lang="less">
span {
    transition: all 0.3s;
    cursor: pointer;

    &:hover {
        .icon {
            display: inline-block;
        }
    }

    .icon {
        display: none;
        cursor: pointer;
    }
}
</style>