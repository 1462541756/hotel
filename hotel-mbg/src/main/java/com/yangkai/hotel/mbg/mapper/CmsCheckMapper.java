package com.yangkai.hotel.mbg.mapper;

import com.yangkai.hotel.mbg.model.CmsCheck;
import com.yangkai.hotel.mbg.model.CmsCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsCheckMapper {
    long countByExample(CmsCheckExample example);

    int deleteByExample(CmsCheckExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsCheck record);

    int insertSelective(CmsCheck record);

    List<CmsCheck> selectByExample(CmsCheckExample example);

    CmsCheck selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsCheck record, @Param("example") CmsCheckExample example);

    int updateByExample(@Param("record") CmsCheck record, @Param("example") CmsCheckExample example);

    int updateByPrimaryKeySelective(CmsCheck record);

    int updateByPrimaryKey(CmsCheck record);
}