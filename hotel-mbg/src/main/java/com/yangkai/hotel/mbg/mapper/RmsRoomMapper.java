package com.yangkai.hotel.mbg.mapper;

import com.yangkai.hotel.mbg.model.RmsRoom;
import com.yangkai.hotel.mbg.model.RmsRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RmsRoomMapper {
    long countByExample(RmsRoomExample example);

    int deleteByExample(RmsRoomExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RmsRoom record);

    int insertSelective(RmsRoom record);

    List<RmsRoom> selectByExample(RmsRoomExample example);

    RmsRoom selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RmsRoom record, @Param("example") RmsRoomExample example);

    int updateByExample(@Param("record") RmsRoom record, @Param("example") RmsRoomExample example);

    int updateByPrimaryKeySelective(RmsRoom record);

    int updateByPrimaryKey(RmsRoom record);
}