<script lang="ts" setup>
import { ref, onMounted, computed } from "vue"
import { getInSteamApi, getOutSteamApi } from "@/api/apis"
import InKuListTable from "./InKuListTable.vue";
import OutKuListTable from "./OutKuListTable.vue";
const emits = defineEmits(['handleLoad'])
// 入库流
const inSteam = ref([])
// 出库流
const outSteam = ref([])
// 对话框
let inId = ref('')
let outId = ref('')
let detailDiaVis = ref(false)
function handleClickItem(id) {
    inId.value = id
    detailDiaVis.value = true
}
function handleOutSteamItem(id) {
    outId.value = id
    detailDiaVis.value = true
}
function close() {
    inId.value = ''
    outId.value = ''
}

onMounted(() => {
    getInSteamApi().then(res => {
        inSteam.value = res.data.data.reverse()
        load()
    })
    getOutSteamApi().then(res => {
        outSteam.value = res.data.data.reverse()
        load()
    })
})

let loadNum = ref(0)
// 处理是否全部加载完
function load() {
    loadNum.value++
    if (loadNum.value === 2) {
        emits('handleLoad')
    }
}

let inkuNumber = computed(() => {
    return inSteam.value.length
})
let outkuNumber = computed(() => {
    return outSteam.value.length
})
let inkuProductAmount = computed(() => {
    return inSteam.value.reduce((pre, cur) => {
        return pre + cur.in_amount
    }, 0)
})
let outkuProductAmount = computed(() => {
    return outSteam.value.reduce((pre, cur) => {
        return pre + cur.out_amount
    }, 0)
})
let inkuProductPrice = computed(() => {
    // 千位分隔符
    return inSteam.value.reduce((pre, cur) => {
        return pre + cur.in_amount * cur.p_price
    }, 0).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')
})
let outkuProductPrice = computed(() => {
    return outSteam.value.reduce((pre, cur) => {
        return pre + cur.out_amount * cur.p_price
    }, 0).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')
})
</script>

<template>
    <div class="steam">
        <el-card class="box-card">
            <div class="box">
                <h3>入库流</h3>
                <div class="list">
                    <div class="list_item" v-for="item in inSteam" :key="item.inId" @click="handleClickItem(item.in_id)">
                        <el-tooltip class="box-item" effect="light"
                            :content="item.w_name + '-' + item.in_type + '-' + item.in_amount" placement="right">
                            <span>
                                {{ item.w_name }}-{{ item.in_type }}-{{ item.in_amount }}
                            </span>
                        </el-tooltip>
                    </div>
                </div>
            </div>
        </el-card>
        <el-card class="box-card">
            <div class="box">
                <h3>仓库简报</h3>
                <div class="row">
                    <div class="item">
                        <h4>入库单数</h4>
                        <div class="text">
                            <span>{{ inkuNumber }}</span> 单
                        </div>
                    </div>
                    <div class="item">
                        <h4>入库产品数</h4>
                        <div class="text">
                            <span>{{ inkuProductAmount }}</span> 件
                        </div>
                    </div>
                    <div class="item">
                        <h4>入库产品价值/元</h4>
                        <div class="text">
                            <span>{{ inkuProductPrice }}</span> 元
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="item">
                        <h4>出库单数</h4>
                        <div class="text">
                            <span>{{ outkuNumber }}</span> 单
                        </div>
                    </div>
                    <div class="item">
                        <h4>出库产品数</h4>
                        <div class="text">
                            <span>{{ outkuProductAmount }}</span> 件
                        </div>
                    </div>
                    <div class="item">
                        <h4>出库产品价值/元</h4>
                        <div class="text">
                            <span>{{ outkuProductPrice }}</span> 元
                        </div>
                    </div>
                </div>
            </div>
        </el-card>
        <el-card class="box-card">
            <div class="box">
                <h3>出库流</h3>
                <div class="list">
                    <div class="list_item" v-for="item in outSteam" :key="item.inId"
                        @click="handleOutSteamItem(item.out_id)">
                        <el-tooltip class="box-item" effect="light"
                            :content="item.w_name + '-' + item.out_type + '-' + item.out_amount" placement="right">
                            <span>
                                {{ item.w_name }}-{{ item.out_type }}-{{ item.out_amount }}
                            </span>
                        </el-tooltip>
                    </div>
                </div>
            </div>
        </el-card>
    </div>

    <el-dialog v-model="detailDiaVis" title="流动详情" align-center @close="close">
        <InKuListTable v-if="inId" :in-id="inId" />
        <OutKuListTable v-if="outId" :out-id="outId" />
    </el-dialog>
</template>

<style scoped lang="less">
.steam {
    padding: 20px;
    background-color: white;
    display: flex;
    // flex-direction: row;
    justify-content: space-around;
    margin-top: 20px;
    box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.12);
    box-sizing: border-box;

    .box-card {
        flex: 1;
        box-sizing: border-box;

        &:nth-child(2) {
            flex: 3;
            margin: 0px 20px;
        }
    }

    .box {
        h3 {
            font-weight: 600;
        }

        .row {
            display: flex;
            justify-content: space-around;
            margin-top: 20px;

            .item {
                width: 30%;
                height: 200px;
                background-color: #f5f7fa;
                border-radius: 5px;
                text-align: center;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;

                h4 {
                    margin-top: 20px;
                }

                .text {
                    flex: 1;
                    display: flex;
                    // flex-direction: column;
                    justify-content: center;
                    align-items: center;
                    color: #4A96FE;
                    font-size: 18px;

                    span {
                        font-size: 30px;
                        font-weight: 700;
                        margin-right: 10px;
                    }
                }

                &:hover {
                    background-color: #ebeef5;
                    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
                }
            }

        }

        .list {
            height: 400px;
            overflow: auto;

            .list_item {
                padding: 10px;
                border-bottom: 1px solid #ebeef5;
                // text-align: center;
                cursor: pointer;

                &:hover {
                    color: #4A96FE;
                }
            }
        }
    }
}
</style>