import { request } from "./request"

// 登录
export const loginApi = (uAccount, uPwd) => {
    return request({
        url: "/login",
        method: "get",
        params: {
            uAccount,
            uPwd
        },
    })
}

// 仓库
// 获取所有仓库
export const getWarehouseApi = () => {
    return request({
        url: "/warehouse/all",
        method: "get",
    })
}
// 根据wId查询仓库
export const getWarehouseByIdApi = (wId) => {
    return request({
        url: "/warehouse/selectById",
        method: "get",
        params: {
            wId
        }
    })
}

// 添加一个仓库
export const addWarehouseApi = (data) => {
    return request({
        url: "/warehouse/add",
        method: "post",
        data,
    })
}
//删除仓库
export const deleteWarehouseApi = (data) => {
    return request({
        url: "/warehouse/delete",
        method: "post",
        data
    })
}


// 入库
// 入库一个记录
export const addInApi = (data) => {
    return request({
        url: "/in/add",
        method: "post",
        data,
    })
}
// 获取所有入库记录 
export const getInApi = () => {
    return request({
        url: "/in/all",
        method: "get",
    })
}
// 根据id查询入库记录
export const getInByIdApi = (inId) => {
    return request({
        url: "/in/get",
        method: "get",
        params: {
            inId
        }
    })
}
// 删除一个入库记录
export const deleteInApi = (id) => {
    return request({
        url: "/in/delete",
        method: "post",
        data: {
            id
        }
    })
}

// 出库
// 出库一个记录
export const addOutApi = (data) => {
    return request({
        url: "/out/add",
        method: "post",
        data,
    })
}
// 所有出库记录
export const getOutApi = () => {
    return request({
        url: "/out/all",
        method: "get",
    })
}
// 根据id查询出库记录
export const getOutByIdApi = (outId) => {
    return request({
        url: "/out/get",
        method: "get",
        params: {
            outId
        }
    })
}
// 删除一个出库记录
export const deleteOutApi = (id) => {
    return request({
        url: "/out/delete",
        method: "post",
        data: {
            id
        }
    })
}


// 产品
// 获取所有产品
export const getProductApi = () => {
    return request({
        url: "/product/all",
        method: "get",
    })
}
// 添加一个产品
export const addProductApi = (data) => {
    return request({
        url: "/product/add",
        method: "post",
        data,
    })
}
// 删除一个产品
export const deleteProductApi = (id) => {
    return request({
        url: "/product/delete",
        method: "post",
        data: {
            id
        }
    })
}
// 更新一个产品
export const updateProductApi = (data) => {
    return request({
        url: "/product/update",
        method: "post",
        data,
    })
}
// 根据pId查询产品
export const getProductByIdApi = (pId) => {
    return request({
        url: "/product/selectById",
        method: "get",
        params: {
            pId
        }
    })
}



// rule
// 获取所有rule
export const getRuleApi = () => {
    return request({
        url: "/rule/all",
        method: "get",
    })
}
// 添加一个rule
export const addRuleApi = (data) => {
    return request({
        url: "/rule/create",
        method: "post",
        data,
    })
}
// 删除一个rule
export const deleteRuleApi = (data) => {
    return request({
        url: "/rule/delete",
        method: "post",
        data
    }
    )
}
// 根据rId查询rule
export const getRuleByIdApi = (uId) => {
    return request({
        url: "/rule/selectById",
        method: "get",
        params: {
            uId
        }
    })
}


// 库存
// 查询所有库存
export const getInventoryApi = () => {
    return request({
        url: "/inventory/all",
        method: "get",
    })
}




// 统计
// 获取inSteam
export const getInSteamApi = () => {
    return request({
        url: "/tj/inSteam",
        method: "get",
    })
}
// 获取outSteam
export const getOutSteamApi = () => {
    return request({
        url: "/tj/outSteam",
        method: "get",
    })
}