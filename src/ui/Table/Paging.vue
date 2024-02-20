<script lang="ts" setup>
import { ref, computed } from "vue"
import { color } from "../../config/style/color"
const props = defineProps({
    total: {
        type: Number,
        required: true
    },
})
// v-model的当前页
const currentPage = defineModel('currentPage', {
    type: Number,
    default: 1
})
// v-model的每页显示条数
const pageSize = defineModel('pageSize', {
    type: Number,
    default: 10
})
// 计算分页总数
let totalPage = computed(() => {
    return Math.ceil(props.total / pageSize.value)
})
// 翻页按钮
const pre_next = (type) => {
    if (type === 'pre') {
        if (currentPage.value > 1) {
            currentPage.value--
        }
    } else {
        if (currentPage.value < totalPage.value) {
            currentPage.value++
        }
    }
}
</script>

<template>
    <div class="paging">
        <div class="item paging-left">
            <span>共 <span :style="{ borderBottom: `1px solid ${color}` }">{{ total }}</span> 条--</span>
            <span>每页
                <select v-model="pageSize" class="pageSizeSelect">
                    <option value="10">10</option>
                    <option value="20">20</option>
                    <option value="50">50</option>
                </select>
                条</span>
        </div>
        <div class="item paging-mid">
            <div class="pre" @click="pre_next('pre')">上一页</div>
            <div class="pagintBtn" v-for="(pag, index) in totalPage" :key="index" @click="currentPage = pag"
                :class="{ 'pagintBtn-s': currentPage == pag }">
                {{ pag }}
            </div>
            <div class="next" @click="pre_next('next')">下一页</div>
        </div>
    </div>
</template>

<style scoped lang="less">
.paging {
    display: flex;
    font-size: 16px;
    color: #666;
    margin-top: 20px;

    .item {
        margin-right: 10px;
    }

    .pageSizeSelect {
        border: 1px solid #DCDFE8;
        border-radius: 5px;
        padding: 5px;
        margin: 0px 10px;
    }

    .paging-mid {
        display: flex;
        justify-content: center;

        .pre,
        .next {
            margin: 0px 10px;
            cursor: pointer;
            transition: all 0.2s;

            &:hover {
                color: #FD9260;
            }
        }

        .pagintBtn-s {
            background-color: #FD9260;
            color: white;
        }

        .pagintBtn {
            width: 30px;
            height: 30px;
            line-height: 30px;
            text-align: center;
            border: 1px solid #DCDFE8;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 5px;
            transition: all 0.2s;

            &:hover {
                background-color: #FD9260;
                color: white;
            }
        }
    }

}
</style>