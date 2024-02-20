<script lang="ts" setup>
import { ref, computed } from "vue"
const props = defineProps({
    textStyle: {
        type: Object,
        default: "default",
    },
    label: {
        type: String,
        default: "Button",
        required: true,
    },
    width: {
        type: String,
        default: "100px",
    },
    type: {
        type: String,
        default: "default",
    },
    disabled: {
        type: Boolean,
        default: false,
    },
    loading: {
        type: Boolean,
        default: false,
    },
    icon: {
        type: String,
        default: "",
    },
    iconPosition: {
        type: String,
        default: "left",
    },
    bgColor: {
        type: String,
        default: "#FD9260",
    },
    textColor: {
        type: String,
        default: "#fff",
    },
})
const emits = defineEmits(["onClick"])
// type类型:default,plain,round,circle
const backgroundColor = computed(() => {
    switch (props.type) {
        case "default":
            return "#FD9260"
        case "plain":
            return "#fff"
        case "round":
            return "#FD9260"
        case "circle":
            return "#FD9260"
        default:
            return "#FD9260"
    }
})
const textColor = computed(() => {
    switch (props.type) {
        case "default":
            return "#fff"
        case "plain":
            return "#FD9260"
        case "round":
            return "#fff"
        case "circle":
            return "#fff"
        default:
            return "#fff"
    }
})
const border = computed(() => {
    switch (props.type) {
        case "default":
            return "1px solid #FD9260"
        case "plain":
            return "1px solid #FD9260"
        case "round":
            return "1px solid #FD9260"
        case "circle":
            return "1px solid #FD9260"
        default:
            return "1px solid #FD9260"
    }
})

function handleClick() {
    if (props.loading || props.disabled) return
    emits("onClick")
}

const style = computed(() => {
    let result = {
        backgroundColor: backgroundColor.value,
        color: textColor.value,
        border: border.value,
        width: props.width,
        opacity: props.loading ? "0.7" : "1",
    }
    // console.log("props.style:", props.style);
    // for (const key in props.style) {
    //     if (Object.prototype.hasOwnProperty.call(props.style, key)) {
    //         const element = props.style[key];
    //         result[key] = element
    //     }
    // }
    // console.log("style:", result);
    return result
})
</script>

<template>
    <div :class="['btn', `btn-${props.type}`, { disabled: props.disabled || props.loading }]" :style="style"
        :disabled="props.disabled || props.loading" @click="handleClick">


        <span v-if="props.icon && props.iconPosition === 'left'" class="icon">
            <i :class="props.icon"></i>
        </span>
        <span v-if="props.loading" class="loading"></span>
        <span :style="props.textStyle">{{ props.label }}</span>
        <span v-if="props.icon && props.iconPosition === 'right'" class="icon">
            <i :class="props.icon"></i>
        </span>

    </div>
</template>

<style scoped lang="less">
.btn {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    user-select: none;
    transition: all 0.3s;
    position: relative;
    margin: 10px 0px;
}

.loading {
    // position: absolute;
    // left: 50%;
    // top: 50%;
    // transform: translate(-50%, -50%);
    margin: 0px 5px;
    width: 18px;
    height: 18px;
    border: 2px solid #fff;
    border-top: 2px solid #FD9260;
    border-radius: 50%;
    animation: loading 1s linear infinite;
}

.icon {
    margin-right: 5px;

    i {
        font-size: 16px;
    }
}

.btn-default {
    &:hover {
        opacity: 0.7 !important;
    }
}

.btn-plain {
    &:hover {
        background-color: #FFFAF5 !important;
    }
}

.btn-round {
    border-radius: 20px;

    &:hover {
        opacity: 0.7 !important;
    }
}

.btn-circle {
    border-radius: 50%;
    width: 40px !important;
    height: 40px !important;
    display: flex;
    align-items: center;
    justify-content: center;

    &:hover {
        opacity: 0.7 !important;
    }
}

.disabled {
    cursor: not-allowed;
    opacity: 0.6 !important;

}

@keyframes loading {
    0% {
        transform: rotate(0deg);
    }

    100% {
        transform: rotate(360deg);
    }
}
</style>