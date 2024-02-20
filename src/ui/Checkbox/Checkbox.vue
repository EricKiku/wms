<script lang="ts" setup>
import { ref } from "vue"
const props = defineProps({
    label: {
        type: String,
        default: ""
    }
})
let model = defineModel()
// 切换状态
function toggle() {
    console.log("?");
    model.value = !model.value
}
</script>

<template>
    <div class="checkbox">
        <div class="cicle" :style="{ backgroundColor: model ? '#FD9260' : '', border: model ? '0px' : '1px solid #ccc' }"
            @click="toggle">
        </div>
        <label @click="toggle">{{ props.label }}</label>
    </div>
</template>

<style scoped lang="less">
.checkbox {
    display: flex;
    align-items: center;
    margin: 10px 0;
    width: fit-content;
    cursor: pointer;

    .active {
        background-color: #FD9260;
    }

    .cicle {
        width: 20px;
        height: 20px;
        border-radius: 50%;
        margin-right: 10px;
        transition: all 0.3s;
    }
}
</style>