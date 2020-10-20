package com.yangkai.hotel.main.dao;

import com.yangkai.hotel.mbg.model.OmsOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单表(OmsOrder)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-20 16:55:50
 */
public interface OmsOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OmsOrder queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OmsOrder> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param omsOrder 实例对象
     * @return 对象列表
     */
    List<OmsOrder> queryAll(OmsOrder omsOrder);

    /**
     * 新增数据
     *
     * @param omsOrder 实例对象
     * @return 影响行数
     */
    int insert(OmsOrder omsOrder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<OmsOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<OmsOrder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<OmsOrder> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<OmsOrder> entities);

    /**
     * 修改数据
     *
     * @param omsOrder 实例对象
     * @return 影响行数
     */
    int update(OmsOrder omsOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);



    String selectUsernameByOrderId(Long orderId);
}