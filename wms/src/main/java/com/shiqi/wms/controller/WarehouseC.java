package com.shiqi.wms.controller;

import com.shiqi.wms.mapper.WarehouseM;
import com.shiqi.wms.pojo.Warehouse;
import com.shiqi.wms.tools.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouse")
public class WarehouseC {
    @Autowired
    private WarehouseM warehouseM;

    // 返回所有仓库
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Res all() {
        return new Res(200, "success", warehouseM.selectAll());
    }

    //根据wId返回仓库
    @RequestMapping(value = "/selectById",method = RequestMethod.GET)
    public Res selectById(int wId) {
        return new Res(200, "success", warehouseM.selectWarehouseById(wId));
    }

    // 新建一个仓库
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Res add(@RequestBody Warehouse wName) {
        int insert = warehouseM.insert(wName);
        if (insert == 0) {
            return  new Res(400,"添加失败");
        }
        return  new Res(200,"添加成功");
    }

    //删除一个仓库
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Res delete(@RequestBody Warehouse warehouse) {
        int delete = warehouseM.deleteById(warehouse);
        if (delete == 0) {
            return new Res(400, "删除失败");
        }
        return new Res(200, "删除成功");
    }
}
