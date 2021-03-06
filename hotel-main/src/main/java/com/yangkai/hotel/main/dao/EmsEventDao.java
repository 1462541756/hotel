package com.yangkai.hotel.main.dao;

import com.yangkai.hotel.mbg.model.EmsEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (EmsEvent)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-02 18:45:19
 */
//@Mapper  本注解配合@Select等使用可不用配置mappe.xml文件
@Repository
public interface EmsEventDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EmsEvent queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<EmsEvent> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param emsEvent 实例对象
     * @return 对象列表
     */
    List<EmsEvent> queryAll(EmsEvent emsEvent);

    /**
     * 新增数据
     *
     * @param emsEvent 实例对象
     * @return 影响行数
     */
    int insert(EmsEvent emsEvent);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<EmsEvent> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<EmsEvent> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<EmsEvent> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<EmsEvent> entities);

    /**
     * 修改数据
     *
     * @param emsEvent 实例对象
     * @return 影响行数
     */
    int update(EmsEvent emsEvent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}