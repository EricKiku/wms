import { ElMessage } from 'element-plus'

export default function message(text, type = "success") {
    ElMessage({
        message: text,
        type,
        duration: 2000,
        center: true
    })
}