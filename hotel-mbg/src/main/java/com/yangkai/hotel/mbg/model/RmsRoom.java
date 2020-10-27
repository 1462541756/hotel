package com.yangkai.hotel.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

public class RmsRoom implements Serializable {
    @ApiModelProperty(value = "房间id")
    private Long id;

    @ApiModelProperty(value = "房间名")
    private String name;

    @ApiModelProperty(value = "房间状态(0-不可用/1-空闲)")
    private Integer status;

    @ApiModelProperty(value = "清洁状态(0-未清洁，1-清洁中，2-已清洁)")
    private Integer clean;

    @ApiModelProperty(value = "维修状态(0-未维修，1-维修中，2-维修完成)")
    private Integer maintenance;

    @ApiModelProperty(value = "房间描述")
    private String description;

    @ApiModelProperty(value = "楼层数")
    private Integer floor;

    @ApiModelProperty(value = "房间序号")
    private String serial;

    @ApiModelProperty(value = "排序号")
    private Long sort;

    @ApiModelProperty(value = "房间价格")
    private BigDecimal price;

    @ApiModelProperty(value = "房间图片地址")
    private String pic;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getClean() {
        return clean;
    }

    public void setClean(Integer clean) {
        this.clean = clean;
    }

    public Integer getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Integer maintenance) {
        this.maintenance = maintenance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", clean=").append(clean);
        sb.append(", maintenance=").append(maintenance);
        sb.append(", description=").append(description);
        sb.append(", floor=").append(floor);
        sb.append(", serial=").append(serial);
        sb.append(", sort=").append(sort);
        sb.append(", price=").append(price);
        sb.append(", pic=").append(pic);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}