package com.shiqi.wms.controller;

import com.shiqi.wms.mapper.ProductM;
import com.shiqi.wms.pojo.Product;
import com.shiqi.wms.tools.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductC {
    @Autowired
    private ProductM productM;

    // 返回所有产品
    @RequestMapping("/all")
    public Res all() {
        return new Res(200, "success", productM.selectAll());
    }

    // 添加一个产品
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Res add(@RequestBody Product product) {
        productM.insert(product);
        return new Res(200, "success", product);
    }

    // 删除一个产品
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Res delete(@RequestBody Map<String, Integer> map) {
        int i = productM.deleteProductById(map.get("id"));
        if (i == 0) {
            return new Res(400, "fail", null);
        }
        return new Res(200, "success");
    }

    // 根据pId查询产品
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    public Res selectById(@RequestParam int pId) {
        System.out.println(pId);
        List<Product> products = productM.selectProductById(pId);
        // 判断查询结果是否为空
        if (products.size() == 0) {
            return new Res(400, "fail", null);
        }
        return new Res(200, "success", products);
    }

    // 更新产品
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Res update(@RequestBody Product product) {
        int i = productM.updateById(product);
        if (i == 0) {
            return new Res(400, "fail", null);
        }
        return new Res(200, "success", product);
    }
}
