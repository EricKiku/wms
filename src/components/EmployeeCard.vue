<script lang="ts" setup>
import { ref } from "vue"
import { useStore } from "@/stores/store"
const store = useStore()
</script>

<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span>Admin</span>
                <el-button class="button" text>编辑</el-button>
            </div>
        </template>
        <div class="container">
            <div class="name">
                <span>{{ store.getUser.uName }}</span>
            </div>
        </div>
        <template #footer>Footer content</template>
    </el-card>
</template>

<style scoped lang="less">
.el-card {
    padding: 10px;
    border: 1px solid #ccc;

    &:hover {
        border: 1px solid #409EFF;
    }
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
</style>