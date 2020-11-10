package com.yangkai.hotel.main.dao;

import com.yangkai.hotel.mbg.model.RmsRoom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (RmsRoom)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-17 22:29:16
 */
@Repository
public interface RmsRoomDao {



    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RmsRoom queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RmsRoom> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param rmsRoom 实例对象
     * @return 对象列表
     */
    List<RmsRoom> queryAll(RmsRoom rmsRoom);

    /**
     * 新增数据
     *
     * @param rmsRoom 实例对象
     * @return 影响行数
     */
    int insert(RmsRoom rmsRoom);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RmsRoom> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RmsRoom> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RmsRoom> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<RmsRoom> entities);

    /**
     * 修改数据
     *
     * @param rmsRoom 实例对象
     * @return 影响行数
     */
    int update(RmsRoom rmsRoom);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}