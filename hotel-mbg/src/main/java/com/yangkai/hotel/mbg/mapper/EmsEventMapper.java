package com.yangkai.hotel.mbg.mapper;

import com.yangkai.hotel.mbg.model.EmsEvent;
import com.yangkai.hotel.mbg.model.EmsEventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmsEventMapper {
    long countByExample(EmsEventExample example);

    int deleteByExample(EmsEventExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EmsEvent record);

    int insertSelective(EmsEvent record);

    List<EmsEvent> selectByExample(EmsEventExample example);

    EmsEvent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EmsEvent record, @Param("example") EmsEventExample example);

    int updateByExample(@Param("record") EmsEvent record, @Param("example") EmsEventExample example);

    int updateByPrimaryKeySelective(EmsEvent record);

    int updateByPrimaryKey(EmsEvent record);
}