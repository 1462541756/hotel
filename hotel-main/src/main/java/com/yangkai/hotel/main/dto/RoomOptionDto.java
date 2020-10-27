package com.yangkai.hotel.main.dto;

import com.yangkai.hotel.mbg.model.RmsRoom;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author 杨锴
 * @date 2020/10/27 15:26
 * @description：
 */
public class RoomOptionDto {
    private String label;
    private Long value;
    private List<RoomOptionDto> children;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public List<RoomOptionDto> getChildren() {
        return children;
    }

    public void setChildren(List<RoomOptionDto> children) {
        this.children = children;
    }
}
