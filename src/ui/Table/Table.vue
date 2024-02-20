<script lang="ts" setup>
import { ref, computed, type PropType } from "vue"
import { bgColor, color } from "../../config/style/color"
import Paging from "./Paging.vue";
interface Column {
    title: string;
    key: string;
    slot: string;
    width: string;
    colWidth: string;
    sort: boolean;
}
// 接收参数 columns，dataSource
const props = defineProps({
    columns: {
        type: Array as unknown as PropType<Column[]>,
        required: true,
    },
    width: {
        type: String,
        default: '100%'
    }
})
let dataSource = defineModel("dataSource", {
    type: Array as unknown as PropType<any[]>,
    required: true,
    default: []
})
// 提取slot属性，用于设置具名插槽
let columnSlot = computed(() => {
    const filterColumns = props.columns.length && props.columns.map((v: any) => {
        return v.slot
    })
    return filterColumns
})
// 提取width属性，用于设置表格宽度
let width = computed(() => {
    return props.width
})
// 提取列宽colWidth属性，用于设置列宽
let colWidth = computed(() => {
    const colWidth = props.columns.length && props.columns.map((v: any) => {
        return v.colWidth
    })
    return colWidth
})


// 当前页码，每页显示条数
let currentPage = ref(1)
let pageSize = ref(10)

// 排序
let sorting = ref(false)
let sortKey = ref('')
let sortType = ref('')
function sort(key) {
    if (sortKey.value !== key) {
        sortType.value = ''
    }
    sortKey.value = key
    if (sortType.value === '') {
        sorting.value = true
        sortType.value = 'asc'
    } else if (sortType.value === 'asc') {
        sortType.value = 'desc'
    } else if (sortType.value === 'desc') {
        sortType.value = ''
        sorting.value = false
        sortKey.value = ''
    }

    // 把dataSource的数据按照key排序后，存到tempDataSource
    dataSource.value = dataSource.value.slice().sort((a, b) => {
        if (sortType.value === 'asc') {
            return a[key] - b[key]
        } else if (sortType.value === 'desc') {
            return b[key] - a[key]
        }
    })
}

// 选中标记
let selected = ref([])
// 选中的对象
let selectedProducts = computed(() => {
    return dataSource.value.filter((v: any) => {
        return selected.value.includes(v[props.columns[0]['key']])
    })
})
let allSelected = computed({
    get: () => {
        return dataSource.value.length === selected.value.length
    },
    set: (val) => {
        if (val) {
            selected.value = dataSource.value.map((v: any) => {
                return v[props.columns[0]['key']]
            })
        } else {
            selected.value = []
        }
    }
})

defineExpose({
    selected,
    selectedProducts
})
</script>

<template>
    <div class="table" :style="{ width: width }">
        <div class="table-header" :style="{ backgroundColor: bgColor }">
            <div class="checkbox"><input v-model="allSelected" type="checkbox"></div>
            <div class="header-col" v-for="(column, index) in columns" :key="column.key"
                :style="{ width: colWidth[index], textAlign: 'center' }">
                {{ column.title }}
                <img v-if="column.sort" @click="sort(column.key)"
                    :src="`/src/assets/icons/${sorting ? sortKey == column.key ? sortType == 'asc' ? 'ascsort' : 'descsort' : 'nosort' : 'nosort'}.png`"
                    alt="">
            </div>
        </div>
        <div class="content" v-if="dataSource">
            <div class="row"
                v-for="(row, rowIndex) in dataSource.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
                :key="row[columns[0]['key']]">
                <div class="checkbox"><input v-model="selected" :value="row[columns[0]['key']]" type="checkbox"></div>
                <div v-for="(slotName, index) in columnSlot" :key="index"
                    :style="{ width: colWidth[index], textAlign: 'center' }">
                    <slot :content="row" :row="rowIndex" :key="slotName" :name="slotName" />
                </div>
            </div>
        </div>
        <div v-else>
            没有数据
        </div>
    </div>
    <Paging v-if="dataSource" :total="dataSource.length" v-model:pageSize="pageSize" v-model:currentPage="currentPage" />
</template>

<style scoped lang="less">
.table {
    border: 1px solid #DCDFE8;
    border-radius: 5px;
    overflow: hidden;
    box-sizing: border-box;
    border-top-left-radius: 15px;
    border-top-right-radius: 15px;

    input[type="checkbox"] {
        width: 15px;
        height: 15px;
        margin: 0 0 0 20px;
        display: block;
        position: relative;
        top: 50%;
        transform: translateY(-50%);
        cursor: pointer;
    }

    .table-header {
        display: flex;
        // justify-content: space-around;
        padding: 10px 0;
        border-bottom: 1px solid #e8e8e8;
        font-weight: 700;
        font-size: 18px;
        color: #110A57;
        font-family: "黑体";

        .header-col {
            display: flex;
            justify-content: center;
            align-items: center;

            img {
                width: 15px;
                height: 15px;
                margin-left: 5px;
                cursor: pointer;
            }
        }
    }

    .content {


        .row {
            display: flex;
            // justify-content: space-around;
            padding: 10px 0;
            border-bottom: 1px solid #e8e8e8;
            position: relative;
            transition: all 0.2s;

            &:hover {
                background-color: #f6f6f6;
            }

            &:nth-child(even) {
                background-color: #fd92600b;

                &:hover {
                    background-color: #f6f6f6;
                }
            }
        }
    }
}
</style>