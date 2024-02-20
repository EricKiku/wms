package com.shiqi.wms.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

public class Rule {
    @TableId
    private int uId;
    private String uName;
    private String uAccount;
    private String uPwd;
    private String uRule;
    private String uMenu;

    public Rule() {
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuAccount() {
        return uAccount;
    }

    public void setuAccount(String uAccount) {
        this.uAccount = uAccount;
    }

    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public String getuRule() {
        return uRule;
    }

    public void setuRule(String uRule) {
        this.uRule = uRule;
    }

    public String getuMenu() {
        return uMenu;
    }

    public void setuMenu(String uMenu) {
        this.uMenu = uMenu;
    }
}
