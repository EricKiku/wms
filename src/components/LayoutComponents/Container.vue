<script lang="ts" setup>
import { ref } from "vue" 
</script>

<template>
    <div class="container">
        <router-view></router-view>
    </div>
</template>

<style scoped>
.container {
    flex: 1;
    padding: 15px;
    box-sizing: border-box;
    overflow: auto;
    background-image: url("/src/assets/img/containerBg.jpg");
    /* 背景图垂直、水平均居中 */
    background-position: center center;
    /* 背景图不平铺 */
    background-repeat: no-repeat;
    /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
    background-attachment: fixed;
    /* 让背景图基于容器大小伸缩 */
    background-size: cover;
    /* 设置背景颜色，背景图加载过程中会显示背景色 */
    background-color: white;
}
</style>