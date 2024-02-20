<script lang="ts" setup>
import { ref } from "vue"
const props = defineProps({
    width: {
        type: String,
        default: "50%"
    },
    padding: {
        type: String,
        default: "0 10px"
    },
    radius: {
        type: String,
        default: "5px"
    }
})
let model = defineModel()
</script>

<template>
    <input :style="{ width: props.width, padding: props.padding, borderRadius: props.radius }" v-model="model" type="text"
        placeholder="123" />
</template>

<style scoped>
input {
    height: 40px;
    margin: 10px 0;
    border: 1px solid #ccc;
    box-sizing: border-box;
    outline: none;
    transition: all 0.3s;

    &:focus {
        border-color: #FD9260;
    }
}
</style>