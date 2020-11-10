package com.yangkai.hotel.main.dao;
import com.yangkai.hotel.main.dto.CheckDetail;
import com.yangkai.hotel.main.dto.RoomOptionDto;
import com.yangkai.hotel.mbg.model.CmsCheck;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 入住记录(CmsCheck)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-27 15:14:51
 */
@Repository
public interface CmsCheckDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CmsCheck queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<CmsCheck> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param cmsCheck 实例对象
     * @return 对象列表
     */
    List<CmsCheck> queryAll(CmsCheck cmsCheck);

    /**
     * 新增数据
     *
     * @param cmsCheck 实例对象
     * @return 影响行数
     */
    int insert(CmsCheck cmsCheck);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CmsCheck> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CmsCheck> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CmsCheck> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<CmsCheck> entities);

    /**
     * 修改数据
     *
     * @param cmsCheck 实例对象
     * @return 影响行数
     */
    int update(CmsCheck cmsCheck);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<RoomOptionDto> fetchOptions();

    CheckDetail getDetail(String orderSn);
}