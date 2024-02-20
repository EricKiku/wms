<script lang="ts" setup>
import { ref } from "vue"
import { Plus, Close } from '@element-plus/icons-vue'
import { color } from "@/config/style/color.js"
import Table from "@/ui/Table/Table.vue"
import message from "@/tools/message"
import { useStore } from "@/stores/store"
import { addRuleApi, deleteRuleApi } from "@/api/apis"
import EmployeeListTable from "@/components/EmployeeListTable.vue"
import { ElMessage, ElMessageBox } from 'element-plus'
const store = useStore()


// 添加对话框
import { CircleCloseFilled } from '@element-plus/icons-vue'
import { getWarehouseApi } from "@/api/apis"
import ProductTable from "@/components/ProductTable.vue"
let addDialogShow = ref(false)
// 对话框数据
// 打开对话框方法
function handleShowDia() {
    if (store.getUser.uRule == 'user') {
        ElMessage({
            type: 'error',
            message: '权限不足',
        })
        return
    }
    addDialogShow.value = true
}
// 角色姓名
let uName = ref('')
// 角色账号
let uAccount = ref('')
// 角色密码
let uPwd = ref('')
// 角色权限
let uRule = ref('user')
// dom
let employeeListDom = ref()
// 创建角色
function handleSubmit() {
    // 判断四个字段是否为空
    if (uName.value === '' || uAccount.value === '' || uPwd.value === '') {
        ElMessage({
            type: 'error',
            message: '请填写完整信息',
        })
        return
    }
    ElMessageBox.confirm(
        '确定提交吗?',
        'Warning',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            addRuleApi({
                uName: uName.value,
                uAccount: uAccount.value,
                uPwd: uPwd.value,
                uRule: uRule.value,
                uMenu: uRule.value == 'admin' ? '12345' : '1235'
            }).then(res => {
                if (res.data.code === 200) {
                    message('添加成功', 'success')
                    employeeListDom.value.refresh()
                    addDialogShow.value = false
                }
            })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消操作',
            })
        })

}


// 删除入库记录
function handleDeleteIn() {
    if (store.getUser.uRule == 'user') {
        ElMessage({
            type: 'error',
            message: '权限不足',
        })
        return
    }
    ElMessageBox.confirm(
        '确定删除选中的数据吗?',
        'Warning',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            for (const rule of employeeListDom.value.table.selectedProducts) {
                deleteRuleApi(rule).then(res => {
                    if (res.data.code === 200) {
                        employeeListDom.value.refresh()
                        // 清空选中
                        employeeListDom.value.table.selected = []
                        message('删除成功', 'success')
                    }
                })
            }
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消操作',
            })
        })

}

</script>

<template>
    <div class="modelIntroduct">
        <div class="modelTitle">
            <img src="/src/assets/icons/rule.png" alt="">
            <h2>角色管理</h2>
        </div>
        <p>对员工的权限，账号，菜单进行管理，由管理员添加用户</p>
    </div>
    <el-card shadow="never">
        <div class="optionsGroup">
            <el-button @click="handleShowDia" class="item" type="primary" :icon="Plus">添加</el-button>
            <el-button @click="handleDeleteIn" type="danger" text>删除</el-button>
        </div>
        <EmployeeListTable ref="employeeListDom" />
    </el-card>


    <el-dialog v-model="addDialogShow" width="auto" :show-close="false" align-center destroy-on-close>
        <!-- 头部 -->
        <template #header="{ close, titleId, titleClass }">
            <div class="dia-header">
                <h4 :id="titleId" :class="titleClass">
                    创建角色
                </h4>
                <el-button @click="close" type="danger" plain class="closeBtn" :icon="Close" size="small" />
            </div>
        </template>
        <!-- 内容 -->
        <div class="dia-container">
            <div :style="{ color }" class="title">基本信息</div>
            <div class="dia-row dia-baseinfo">
                <div class="col">
                    <div class="label"> <span class="require">*</span> 角色编号</div>
                    <el-input disabled placeholder="自动生成" />
                </div>
                <div class="col">
                    <div class="label"> <span class="require">*</span> 角色姓名</div>
                    <el-input v-model="uName" />
                </div>
            </div>
            <div :style="{ color }" class="title">账号信息</div>
            <div class="dia-row dia-baseinfo">

                <div class="col">
                    <div class="label"> <span class="require">*</span> 账号</div>
                    <el-input v-model="uAccount" />
                </div>
                <div class="col">
                    <div class="label"> <span class="require">*</span> 密码</div>
                    <el-input v-model="uPwd" />
                </div>

            </div>
            <div :style="{ color }" class="title">角色权限</div>
            <div class="dia-row dia-baseinfo">
                <el-radio-group v-model="uRule">
                    <el-radio label="admin">
                        <el-tag type="success">管理员</el-tag>
                    </el-radio>
                    <el-radio label="user">
                        <el-tag type="danger">用户</el-tag>
                    </el-radio>
                </el-radio-group>
            </div>
        </div>
        <!-- 底部 -->
        <template #footer>
            <div class="footer">
                <el-button @click="handleSubmit" style="width: 100px;" type="primary">提交</el-button>
            </div>
        </template>
    </el-dialog>
</template>

<style scoped lang="less">
.modelIntroduct {
    margin-bottom: 20px;

    .modelTitle {
        display: flex;
        align-items: center;
        margin-bottom: 20px;

        img {
            width: 35px;
            height: 35px;
            margin-right: 10px;

        }
    }

    p {
        margin: 0;
        color: #676E8A;
        line-height: 40px;
    }
}

.optionsGroup {
    display: flex;
    align-items: center;
    margin-bottom: 20px;

    .item {
        margin-right: 10px;
    }
}

.dia-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 2px solid #ccc;
    padding-bottom: 10px;

    h4 {
        margin: 0;
    }

}

.dia-container {
    padding: 10px;
    display: flex;
    flex-direction: column;
    // height: 300px;
    max-height: 500px;
    overflow: auto;

    .dia-row {
        margin: 10px 0px 20px 0px;

    }

    .dia-baseinfo {
        display: flex;
        justify-content: space-between;

        .col {
            flex: 1;
            margin: 0 10px;
        }
    }

    .title {
        border-bottom: 1px dashed #ccc;
        font-weight: 700;
        font-size: 16px;
        font-weight: bold;
    }


}

.footer {
    display: flex;
    justify-content: start;
    border-top: 2px solid #ccc;
    padding-top: 10px;
}
</style>