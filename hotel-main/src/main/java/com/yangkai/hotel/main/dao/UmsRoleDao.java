package com.yangkai.hotel.main.dao;

import com.yangkai.hotel.mbg.model.UmsMenu;
import com.yangkai.hotel.mbg.model.UmsResource;
import com.yangkai.hotel.mbg.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户角色自定义Dao
 */
public interface UmsRoleDao {
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);

    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);

    List<UmsRole> getRolesByUserId(Long id);
}
