package com.shiqi.wms.controller;


import com.shiqi.wms.mapper.InM;
import com.shiqi.wms.mapper.InventoryM;
import com.shiqi.wms.pojo.In;
import com.shiqi.wms.pojo.Inventory;
import com.shiqi.wms.tools.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/in")
public class InC {
    @Autowired
    private InM inM;
    @Autowired
    private InventoryM  inventoryM;
    // 添加一个入库记录
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Res add(@RequestBody In in) {
        int insert = inM.insert(in);
        if (insert == 0) {
            return  new Res(400,"添加失败");
        }
        // 查询库存表中是否有该商品
        Inventory inventory = inventoryM.selectListByPIdAndWId(in.getpId(), in.getInWarehouse());
        if (inventory == null) {
            // 如果没有该商品，则添加一条库存记录
            Inventory inventory1 = new Inventory();
            inventory1.setpId(in.getpId());
            inventory1.setwId(in.getInWarehouse());
            inventory1.setiAmount(in.getInAmount());
            int insert1 = inventoryM.insert(inventory1);
            if (insert1 == 0) {
                return  new Res(400,"添加失败");
            }
        } else {
            // 如果有该商品，则更新库存记录
            int i_amount = inventory.getiAmount() + in.getInAmount();
            int update = inventoryM.updateIAmountByPIdAndWId(i_amount, in.getpId(), in.getInWarehouse());
            if (update == 0) {
                return  new Res(400,"添加失败");
            }
        }
        return  new Res(200,"添加成功");
    }

    // 返回所有入库记录
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Res all() {
        return new Res(200, "success", inM.selectList(null));
    }

    // 获取入库记录根据id
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Res get(@RequestParam int inId) {
        return new Res(200, "success", inM.selectListById(inId));
    }

    //删除一个入库记录
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Res delete(@RequestBody Map<String,Integer> map) {

        int delete = inM.deleteInById(map.get("id"));
        if (delete == 0) {
            return new Res(400, "删除失败");
        }
        return new Res(200, "删除成功");
    }

}
