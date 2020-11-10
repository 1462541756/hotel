package com.yangkai.hotel.main.dao;

import com.yangkai.hotel.mbg.model.UmsRoleMenuRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UmsRoleMenuRelationDao {
    int insertList(@Param("list")List<UmsRoleMenuRelation> relationList);
}
