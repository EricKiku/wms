<script lang="ts" setup>
import { ref } from "vue"
import { selectedColor, unSelectedColor, bgColor } from "../../config/style/color"
const props = defineProps({
    title: {
        type: String,
        required: true
    },
    icons: {
        default: ['', '']
    },
    padding: {
        type: String,
        default: '10px 15px'
    },
    unSelectedColor: {
        type: String,
        default: unSelectedColor
    },
    selectedColor: {
        type: String,
        default: selectedColor
    },
    selected: {
        type: Boolean,
        default: false
    }
})
// 是否悬浮
const hover = ref(false)
</script>

<template>
    <div>
        <div class="menu-item"
            :style="{ display: 'flex', padding, color: selected ? selectedColor : unSelectedColor, backgroundColor: hover || selected ? bgColor : 'white' }"
            @mouseover="hover = true" @mouseleave="hover = false">
            <div class="icon" v-if="icons[0] && !selected">
                <img :src="icons[0]" alt="ui">
            </div>
            <div class="icon" v-if="icons[1] && selected">
                <img :src="icons[1]" alt="i">
            </div>
            <div class="txt">
                {{ title }}
            </div>
        </div>
        <div class="childrenItem">
            <slot></slot>
        </div>
    </div>
</template>

<style scoped lang="less">
.menu-item {
    transition: all 0.3s;
    border-radius: 10px;
    cursor: pointer;
    margin-top: 5px;
    overflow: hidden;
    position: relative;

    .icon {
        width: 20px;
        height: 20px;
        margin: 5px 10px 0px 5px;

        img {
            width: 100%;
            height: 100%;
        }
    }

    .txt {
        height: 30px;
        line-height: 30px;
        font-size: 16px;
    }
}

.childrenItem {
    // display: none;
    box-sizing: border-box;
    padding-left: 40px;
    transition: all 0.3s;

    .txt {
        height: 30px;
        line-height: 30px;
        font-size: 16px;
    }
}
</style>