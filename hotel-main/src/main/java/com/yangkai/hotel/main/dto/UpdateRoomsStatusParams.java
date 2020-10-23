package com.yangkai.hotel.main.dto;

/**
 * @author 杨锴
 * @date 2020/10/23 21:33
 * @description：
 */
public class UpdateRoomsStatusParams {
    private  Integer status;
    private Long[] ids;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }
}
