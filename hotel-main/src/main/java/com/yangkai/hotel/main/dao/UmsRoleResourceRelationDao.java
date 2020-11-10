package com.yangkai.hotel.main.dao;

import com.yangkai.hotel.mbg.model.UmsRoleResourceRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UmsRoleResourceRelationDao {
    int insertList(@Param("list")List<UmsRoleResourceRelation> relationList);
}
