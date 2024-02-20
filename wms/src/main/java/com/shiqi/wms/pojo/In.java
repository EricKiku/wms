package com.shiqi.wms.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("inku")
public class In {
    private int inId;
    private String inCode;
    private String inType;
    private int inWarehouse;
    private int pId;
    private int inAmount;
    private String inDate;
    private int uId;
    private String inImg;

    public In() {
    }

    public In(int inId, String inCode, String inType, int inWarehouse, int pId, int inAmount, String inDate, int uId, String inImg) {
        this.inId = inId;
        this.inCode = inCode;
        this.inType = inType;
        this.inWarehouse = inWarehouse;
        this.pId = pId;
        this.inAmount = inAmount;
        this.inDate = inDate;
        this.uId = uId;
        this.inImg = inImg;
    }

    public int getInId() {
        return inId;
    }

    public void setInId(int inId) {
        this.inId = inId;
    }

    public String getInCode() {
        return inCode;
    }

    public void setInCode(String inCode) {
        this.inCode = inCode;
    }

    public String getInType() {
        return inType;
    }

    public void setInType(String inType) {
        this.inType = inType;
    }

    public int getInWarehouse() {
        return inWarehouse;
    }

    public void setInWarehouse(int inWarehouse) {
        this.inWarehouse = inWarehouse;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getInAmount() {
        return inAmount;
    }

    public void setInAmount(int inAmount) {
        this.inAmount = inAmount;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getInImg() {
        return inImg;
    }

    public void setInImg(String inImg) {
        this.inImg = inImg;
    }
}
