<script  setup>
import { ref } from "vue"
import Header from "../components/LayoutComponents/Header.vue"
import Sider from "../components/LayoutComponents/Sider.vue"
import Container from "../components/LayoutComponents/Container.vue"
</script>

<template>
    <div class="layout">
        <div class="left">
            <Sider />
        </div>
        <div class="right">
            <Header />
            <Container />
        </div>
    </div>
</template>

<style scoped lang="less">
.layout {
    display: flex;
    height: 100%;

    .right {
        flex: 1;
        display: flex;
        flex-direction: column;
    }
}
</style>