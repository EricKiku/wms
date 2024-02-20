<script lang="ts" setup>
import { ref } from "vue" 
</script>

<template>
    <div class="card">
        <slot></slot>
    </div>
</template>

<style scoped>
.card {
    background-color: white;
    border-radius: 10px;
    padding: 20px;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
    transition: all 0.3s;
    /* cursor: pointer; */
    margin-top: 5px;
    overflow: hidden;

    &:hover {
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    }
}
</style>