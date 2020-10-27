package com.yangkai.hotel.main.dto;

import com.yangkai.hotel.mbg.model.CmsCheck;

import java.util.List;

/**
 * @author 杨锴
 * @date 2020/10/27 17:51
 * @description：
 */
public class CheckDetail {
    private Long orderId;
    private String orderSn;
    private String roomName;
    private Integer floor;
    private String serial;
    private Integer status;
    private Integer registerStatus;
    private List<CmsCheck> people;

    public Long getOrderId() {
        return orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public List<CmsCheck> getPeople() {
        return people;
    }

    public void setPeople(List<CmsCheck> people) {
        this.people = people;
    }
}
