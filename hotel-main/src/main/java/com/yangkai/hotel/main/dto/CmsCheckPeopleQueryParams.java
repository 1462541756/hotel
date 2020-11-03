package com.yangkai.hotel.main.dto;

import java.util.Date;

/**
 * @author 杨锴
 * @date 2020/10/28 0:05
 * @description：
 */
public class CmsCheckPeopleQueryParams {
    private String orderSn;
    private String name;
    private String cardId;
    private Date checkInTime;
    private Date checkOutTime;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}
