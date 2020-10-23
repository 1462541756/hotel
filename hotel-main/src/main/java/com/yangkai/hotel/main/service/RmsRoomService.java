package com.yangkai.hotel.main.service;

import com.yangkai.hotel.mbg.model.RmsRoom;

import java.util.List;

/**
 * (RmsRoom)表服务接口
 *
 * @author makejava
 * @since 2020-10-12 09:45:54
 */
public interface RmsRoomService {


    List<RmsRoom> list(RmsRoom keyword, Integer pageSize, Integer pageNum);

    RmsRoom getRoomById(Long id);

    int updateRoom(RmsRoom room);

    int addRoom(RmsRoom room);

    int deleteById(Long id);

    int updateRoomsStatus(Integer status, List<Long> ids);
}