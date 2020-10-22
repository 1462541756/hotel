package com.yangkai.hotel.main.dto;

import com.yangkai.hotel.mbg.model.OmsOrder;

/**
 * @author 杨锴
 * @date 2020/10/23 0:12
 * @description：
 */
public class OmsOrderQueryParam extends OmsOrder {
    private Long roomId;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
