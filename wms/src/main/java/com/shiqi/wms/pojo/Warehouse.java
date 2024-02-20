package com.shiqi.wms.pojo;

import com.baomidou.mybatisplus.annotation.TableId;

public class Warehouse {
    @TableId
    private int wId;
    private String wName;
    private String wAddress;
    private String wCapacity;
    private String wState;
    private String wCharge;
    private String wInventory;

    public Warehouse() {
    }

    public Warehouse(int wId, String wName, String wAddress, String wCapacity, String wState, String wCharge, String wInventory) {
        this.wId = wId;
        this.wName = wName;
        this.wAddress = wAddress;
        this.wCapacity = wCapacity;
        this.wState = wState;
        this.wCharge = wCharge;
        this.wInventory = wInventory;
    }

    public int getwId() {
        return wId;
    }

    public void setwId(int wId) {
        this.wId = wId;
    }

    public String getwName() {
        return wName;
    }

    public void setwName(String wName) {
        this.wName = wName;
    }

    public String getwAddress() {
        return wAddress;
    }

    public void setwAddress(String wAddress) {
        this.wAddress = wAddress;
    }

    public String getwCapacity() {
        return wCapacity;
    }

    public void setwCapacity(String wCapacity) {
        this.wCapacity = wCapacity;
    }

    public String getwState() {
        return wState;
    }

    public void setwState(String wState) {
        this.wState = wState;
    }

    public String getwCharge() {
        return wCharge;
    }

    public void setwCharge(String wCharge) {
        this.wCharge = wCharge;
    }

    public String getwInventory() {
        return wInventory;
    }

    public void setwInventory(String wInventory) {
        this.wInventory = wInventory;
    }
}
