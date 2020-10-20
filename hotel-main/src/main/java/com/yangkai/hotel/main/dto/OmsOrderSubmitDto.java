package com.yangkai.hotel.main.dto;

import io.swagger.models.auth.In;

/**
 * @author 杨锴
 * @date 2020/10/21 1:01
 * @description：
 */
public class OmsOrderSubmitDto {
    private Long orderId;
    private Integer payType;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
