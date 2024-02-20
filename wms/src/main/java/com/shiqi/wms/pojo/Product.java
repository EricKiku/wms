package com.shiqi.wms.pojo;

import com.baomidou.mybatisplus.annotation.TableId;

public class Product {
    @TableId
    private int pId;
    private String pProp;
    private String pType;
    private String pName;
    private String pBrand;
    private String pModel;
    private String pUnit;
    private int pCost;
    private int pPrice;
    private String pImg;

    public Product() {
    }

    public Product(int pId, String pProp, String pType, String pName, String pBrand, String pModel, String pUnit, int pCost, int pPrice, String pImg) {
        this.pId = pId;
        this.pProp = pProp;
        this.pType = pType;
        this.pName = pName;
        this.pBrand = pBrand;
        this.pModel = pModel;
        this.pUnit = pUnit;
        this.pCost = pCost;
        this.pPrice = pPrice;
        this.pImg = pImg;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpProp() {
        return pProp;
    }

    public void setpProp(String pProp) {
        this.pProp = pProp;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpBrand() {
        return pBrand;
    }

    public void setpBrand(String pBrand) {
        this.pBrand = pBrand;
    }

    public String getpModel() {
        return pModel;
    }

    public void setpModel(String pModel) {
        this.pModel = pModel;
    }

    public String getpUnit() {
        return pUnit;
    }

    public void setpUnit(String pUnit) {
        this.pUnit = pUnit;
    }

    public int getpCost() {
        return pCost;
    }

    public void setpCost(int pCost) {
        this.pCost = pCost;
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public String getpImg() {
        return pImg;
    }

    public void setpImg(String pImg) {
        this.pImg = pImg;
    }
}
