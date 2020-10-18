package com.yangkai.hotel.main.service;



import com.yangkai.hotel.mbg.model.UmsResource;

import java.util.List;

/**
 * 后台资源管理Service
 */
public interface UmsResourceService {
    /**
     * 添加资源
     */
    int create(UmsResource umsResource);

    /**
     * 修改资源
     */
    int update(Long id, UmsResource umsResource);

    /**
     * 获取资源详情
     */
    UmsResource getItem(Long id);

    /**
     * 删除资源及相关数据（资源表、角色资源表）
     */
    int delete(Long id);

    /**
     * 分页查询资源
     */
    List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 查询全部资源
     */
    List<UmsResource> listAll();


}
