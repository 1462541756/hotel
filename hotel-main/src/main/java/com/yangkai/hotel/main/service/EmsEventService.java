package com.yangkai.hotel.main.service;

import com.yangkai.hotel.mbg.model.EmsEvent;

import java.util.List;

/**
 * (EmsEvent)表服务接口
 *
 * @author makejava
 * @since 2020-11-02 18:45:19
 */
public interface EmsEventService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EmsEvent queryById(Long id);


    /**
     * 新增数据
     *
     * @param emsEvent 实例对象
     * @return 实例对象
     */
    EmsEvent insert(EmsEvent emsEvent);


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<EmsEvent> fetchList(EmsEvent emsEvent, Integer pageSize, Integer pageNum);

    int report(EmsEvent params);

    int deleteEventById(Long id);

    EmsEvent getEventById(Long id);

    int cancelReportById(Long id);

    int reportFromDraft(Long id);
}