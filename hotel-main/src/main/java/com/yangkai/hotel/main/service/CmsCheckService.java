package com.yangkai.hotel.main.service;

import com.yangkai.hotel.main.dto.CheckDetail;
import com.yangkai.hotel.main.dto.CmsCheckPeopleQueryParams;
import com.yangkai.hotel.main.dto.RoomOptionDto;
import com.yangkai.hotel.mbg.model.CmsCheck;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 入住记录(CmsCheck)表服务接口
 *
 * @author makejava
 * @since 2020-10-27 15:14:51
 */
public interface CmsCheckService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CmsCheck queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<CmsCheck> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param cmsCheck 实例对象
     * @return 实例对象
     */
    CmsCheck insert(CmsCheck cmsCheck);

    /**
     * 修改数据
     *
     * @param cmsCheck 实例对象
     * @return 实例对象
     */
    CmsCheck update(CmsCheck cmsCheck);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<RoomOptionDto> fetchOptions();

    CheckDetail getDetail(String orderId);
    @Transactional
    int checkOut(Long orderId);

    List<CmsCheck> fetchList(CmsCheck cmsCheckQueryParam, Integer pageSize, Integer pageNum);
    @Transactional
    int  checkIn(CmsCheckPeopleQueryParams params);
}