package com.yangkai.hotel.main.service.impl;

import com.github.pagehelper.PageHelper;
import com.yangkai.hotel.main.dao.UmsRoleDao;
import com.yangkai.hotel.main.service.UmsRoleService;
import com.yangkai.hotel.mbg.mapper.UmsRoleMapper;
import com.yangkai.hotel.mbg.mapper.UmsRoleMenuRelationMapper;
import com.yangkai.hotel.mbg.mapper.UmsRoleResourceRelationMapper;
import com.yangkai.hotel.mbg.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 后台角色管理Service实现类
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    @Autowired
    private UmsRoleMapper roleMapper;
    @Autowired
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;
    @Autowired
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;
    @Autowired
    private UmsRoleDao roleDao;

    @Override
    public int create(UmsRole role) {
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setSort(0);
        return roleMapper.insert(role);
    }

    @Override
    public int update(Long id, UmsRole role) {
        role.setId(id);
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int delete(List<Long> ids) {
        int count=1;
        //删除角色-菜单映射
        UmsRoleMenuRelationExample roleMenuRelationExample =new UmsRoleMenuRelationExample();
        roleMenuRelationExample.createCriteria().andRoleIdIn(ids);
        count=(count==0)?0:roleMenuRelationMapper.deleteByExample(roleMenuRelationExample);

        //删除角色-资源映射
        UmsRoleResourceRelationExample roleResourceRelationExample=new UmsRoleResourceRelationExample();
        roleResourceRelationExample.createCriteria().andRoleIdIn(ids);
        count=(count==0)?0:roleResourceRelationMapper.deleteByExample(roleResourceRelationExample);

        //删除角色
        UmsRoleExample example = new UmsRoleExample();
        example.createCriteria().andIdIn(ids);
        count=(count==0)?0:roleMapper.deleteByExample(example);
        return  count;
    }

    @Override
    public List<UmsRole> list() {
        return roleMapper.selectByExample(new UmsRoleExample());
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsRoleExample example = new UmsRoleExample();
        if (!StringUtils.isEmpty(keyword)) {
            example.createCriteria().andNameLike("%" + keyword + "%");
        }
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleDao.getMenuList(adminId);
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return roleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return roleDao.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        //先删除原有关系
        UmsRoleMenuRelationExample example=new UmsRoleMenuRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(example);

        //批量插入新关系
        List<UmsRoleMenuRelation> relationList = new ArrayList<>();
        for (Long menuId : menuIds) {
            UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            relationList.add(relation);
        }
        return roleMenuRelationMapper.insertList(relationList);
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        //先删除原有关系
        UmsRoleResourceRelationExample example=new UmsRoleResourceRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceRelationMapper.deleteByExample(example);
        //批量插入新关系
        List<UmsRoleResourceRelation> relationList = new ArrayList<>();
        for (Long resourceId : resourceIds) {
            UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            relationList.add(relation);
        }
        return roleResourceRelationMapper.insertList(relationList);
    }
}
