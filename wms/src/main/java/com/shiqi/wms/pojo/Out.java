package com.shiqi.wms.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("outku")
public class Out {
    private int outId;
    private String outCode;
    private String outType;
    private int outWarehouse;
    private String outAddress;
    private int pId;
    private int outAmount;
    private String outDate;
    private int uId;
    private String outImg;

    public Out() {
    }

    public Out(int outId, String outCode, String outType, int outWarehouse, String outAddress, int pId, int outAmount, String outDate, int uId, String outImg) {
        this.outId = outId;
        this.outCode = outCode;
        this.outType = outType;
        this.outWarehouse = outWarehouse;
        this.outAddress = outAddress;
        this.pId = pId;
        this.outAmount = outAmount;
        this.outDate = outDate;
        this.uId = uId;
        this.outImg = outImg;
    }

    public int getOutId() {
        return outId;
    }

    public void setOutId(int outId) {
        this.outId = outId;
    }

    public String getOutCode() {
        return outCode;
    }

    public void setOutCode(String outCode) {
        this.outCode = outCode;
    }

    public String getOutAddress() {
        return outAddress;
    }

    public void setOutAddress(String outAddress) {
        this.outAddress = outAddress;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public int getOutWarehouse() {
        return outWarehouse;
    }

    public void setOutWarehouse(int outWarehouse) {
        this.outWarehouse = outWarehouse;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(int outAmount) {
        this.outAmount = outAmount;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getOutImg() {
        return outImg;
    }

    public void setOutImg(String outImg) {
        this.outImg = outImg;
    }
}
