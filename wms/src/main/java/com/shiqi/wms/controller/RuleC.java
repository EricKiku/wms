package com.shiqi.wms.controller;

import com.shiqi.wms.mapper.RuleM;
import com.shiqi.wms.pojo.Rule;
import com.shiqi.wms.tools.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rule")
public class RuleC {
    @Autowired
    private RuleM ruleM;
    // 获取所有规则
    @RequestMapping("/all")
    public Res all() {
        return new Res(200, "success", ruleM.selectList(null));
    }

    // 创建一个角色
    @RequestMapping("/create")
    public Res create(@RequestBody Rule rule) {
        int insert = ruleM.insert(rule);
        if (insert == 0) {
            return new Res(400, "fail", null);
        }
        return new Res(200, "success", null);
    }
    // 根据uId获取一个角色
    @RequestMapping("/selectById")
    public Res selectById(int uId) {
        return new Res(200, "success", ruleM.selectById(uId));
    }
    // 删除一个角色
    @RequestMapping("/delete")
    public Res delete(@RequestBody Rule rule) {
        int delete = ruleM.deleteById(rule);
        if (delete == 0) {
            return new Res(400, "fail", null);
        }
        return new Res(200, "success", null);
    }
}
