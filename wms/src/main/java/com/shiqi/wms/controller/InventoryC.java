package com.shiqi.wms.controller;

import com.shiqi.wms.mapper.InventoryM;
import com.shiqi.wms.tools.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryC {
    @Autowired
    private InventoryM inventoryM;

    // 获取库存
    @RequestMapping("/all")
    public Res all() {
        return new Res(200, "success", inventoryM.selectInventoryList());
    }

}
