package com.yangkai.hotel.main.dao;

import com.yangkai.hotel.mbg.model.UmsRoleResourceRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsRoleResourceRelationDao {
    int insertList(@Param("list")List<UmsRoleResourceRelation> relationList);
}
