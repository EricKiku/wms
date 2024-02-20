package com.shiqi.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shiqi.wms.mapper.LoginM;
import com.shiqi.wms.pojo.Rule;
import com.shiqi.wms.tools.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/")
public class LoginC {
    @Autowired
    private LoginM loginM;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Res login(@RequestParam Map<String, String> map) {
        String uAccount = map.get("uAccount");
        String uPwd = map.get("uPwd");
        // 先判断有无账号，再判断密码
        String s = loginM.selectAccount(uAccount);
        if (Objects.isNull(s)) {
            // 无此账号
            return new Res(500, "无此账号");
        }
        Rule rule = loginM.selectOne(uAccount,uPwd);
        if (Objects.isNull(rule)) {
            // 密码错误
            return new Res(500, "密码错误");
        }
        return new Res(200, "登录成功",rule);
    }
}
