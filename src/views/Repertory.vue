<script lang="ts" setup>
import { ref } from "vue"
import { useRoute, useRouter } from "vue-router"
const route = useRoute()
const router = useRouter()
</script>

<template>
    <div v-if="route.path == '/repertory'">

    </div>
    <router-view></router-view>
</template>

<style scoped></style>