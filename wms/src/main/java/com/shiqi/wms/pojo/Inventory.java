package com.shiqi.wms.pojo;

public class Inventory {
    private int iId;
    private int pId;
    private int wId;
    private int iAmount;

    public Inventory() {
    }

    public Inventory(int iId, int pId, int wId, int iAmount) {
        this.iId = iId;
        this.pId = pId;
        this.wId = wId;
        this.iAmount = iAmount;
    }

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getwId() {
        return wId;
    }

    public void setwId(int wId) {
        this.wId = wId;
    }

    public int getiAmount() {
        return iAmount;
    }

    public void setiAmount(int iAmount) {
        this.iAmount = iAmount;
    }
}
