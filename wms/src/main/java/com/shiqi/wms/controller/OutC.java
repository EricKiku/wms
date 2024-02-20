package com.shiqi.wms.controller;

import com.shiqi.wms.mapper.InventoryM;
import com.shiqi.wms.mapper.OutM;
import com.shiqi.wms.pojo.Inventory;
import com.shiqi.wms.pojo.Out;
import com.shiqi.wms.tools.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/out")
public class OutC {
    @Autowired
    private OutM outM;
    @Autowired
    private InventoryM inventoryM;
    //添加一个出库记录
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Res add(@RequestBody Out out) {
        // 查询库存表中是否有该商品
        Inventory inventory = inventoryM.selectListByPIdAndWId(out.getpId(), out.getOutWarehouse());
        //
        if (inventory == null) {
            // 如果没有该商品，返回没有该商品
            return  new Res(400,"没有该商品");
        } else {
            // 如果有该商品，则比较库存数量，如果库存数量小于出库数量，返回库存不足
            if (inventory.getiAmount() < out.getOutAmount()) {
                return  new Res(400,"库存不足");
            }
            int i_amount = inventory.getiAmount() - out.getOutAmount();
            int update = inventoryM.updateIAmountByPIdAndWId(i_amount, out.getpId(), out.getOutWarehouse());
            if (update == 0) {
                return  new Res(400,"操作失败");
            }
        }
        int insert = outM.insert(out);
        if (insert == 0) {
            return  new Res(400,"操作失败");
        }
        return  new Res(200,"添加成功");
    }

    // 获取所有出库记录
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Res all() {
        return new Res(200, "success", outM.selectList(null));
    }
    // 获取出库记录根据id
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Res get(@RequestParam int outId) {
        return new Res(200, "success", outM.selectListById(outId));
    }
    //删除一个出库记录
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Res delete(@RequestBody Map<String,Integer> map) {

        int delete = outM.deleteInById(map.get("id"));
        if (delete == 0) {
            return new Res(400, "删除失败");
        }
        return new Res(200, "删除成功");
    }
}
