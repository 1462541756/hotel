package com.yangkai.hotel.main.dto;

import com.yangkai.hotel.mbg.model.UmsMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 后台菜单节点封装
 */
@Getter
@Setter
public class UmsMenuNode extends UmsMenu {
    private List<UmsMenuNode> children;
}
