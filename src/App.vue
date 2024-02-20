<script setup>
import { onMounted } from "vue"
import Layout from './layout/Layout.vue'
import { color } from "@/config/style/color.js"

</script>

<template>
    <router-view></router-view>
</template>

<style lang="less">
.el-button,
button {
    position: relative;
    transition: top 0.1s ease-in-out;

    &:active {
        top: 2px;
    }
}


.el-button--primary {
    background-color: #FD9260;
    border: none;
    // color: white;
    outline: none;

    &:hover {
        background-color: #FD9260;
        opacity: 0.8;
    }

    &:focus {
        background-color: #FD9260;
    }
}

.el-button--primary[disabled] {
    background-color: v-bind(color);
    opacity: 0.6;
    cursor: not-allowed;

    &:hover {
        background-color: v-bind(color);
        opacity: 0.6;
    }

    &:focus {
        background-color: v-bind(color);
    }
}

.el-dialog {
    border-radius: 10px;
}

.require {
    color: red;
}
</style>
