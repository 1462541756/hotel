package com.yangkai.hotel.main.service;

import com.yangkai.hotel.main.dto.OmsOrderQueryParam;
import com.yangkai.hotel.mbg.model.OmsOrder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单表(OmsOrder)表服务接口
 *
 * @author makejava
 * @since 2020-10-20 16:55:50
 */
public interface OmsOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OmsOrder queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OmsOrder> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param params 实例对象
     * @return 实例对象
     */
    @Transactional
    int insert(OmsOrderQueryParam params);

    /**
     * 修改数据
     *
     * @param omsOrder 实例对象
     * @return 实例对象
     */
    OmsOrder update(OmsOrder omsOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<OmsOrder> list(OmsOrder omsOrderQueryParam, Integer pageSize, Integer pageNum,boolean isVip);

    int cancel(Long orderId,boolean isVip);

    int submit(Long orderId,Integer payType);

    int commit(Long orderId, String commitPassword);
}