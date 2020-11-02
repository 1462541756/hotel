package com.yangkai.hotel.main.service.impl;

import com.github.pagehelper.PageHelper;
import com.yangkai.hotel.main.dao.CmsCheckDao;
import com.yangkai.hotel.main.dto.CheckDetail;
import com.yangkai.hotel.main.dto.CmsCheckPeopleQueryParams;
import com.yangkai.hotel.main.dto.RoomOptionDto;
import com.yangkai.hotel.main.service.CmsCheckService;
import com.yangkai.hotel.mbg.mapper.CmsCheckMapper;
import com.yangkai.hotel.mbg.mapper.OmsOrderMapper;
import com.yangkai.hotel.mbg.mapper.RmsRoomMapper;
import com.yangkai.hotel.mbg.model.CmsCheck;
import com.yangkai.hotel.mbg.model.CmsCheckExample;
import com.yangkai.hotel.mbg.model.OmsOrder;
import com.yangkai.hotel.mbg.model.RmsRoom;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 入住记录(CmsCheck)表服务实现类
 *
 * @author makejava
 * @since 2020-10-27 15:14:51
 */
@Service("cmsCheckService")
public class CmsCheckServiceImpl implements CmsCheckService {
    @Resource
    private CmsCheckDao cmsCheckDao;
    @Resource
    private OmsOrderMapper omsOrderMapper;
    @Resource
    CmsCheckMapper cmsCheckMapper;
    @Resource
    RmsRoomMapper rmsRoomMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CmsCheck queryById(Long id) {
        return this.cmsCheckDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<CmsCheck> queryAllByLimit(int offset, int limit) {
        return this.cmsCheckDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param cmsCheck 实例对象
     * @return 实例对象
     */
    @Override
    public CmsCheck insert(CmsCheck cmsCheck) {
        this.cmsCheckDao.insert(cmsCheck);
        return cmsCheck;
    }

    /**
     * 修改数据
     *
     * @param cmsCheck 实例对象
     * @return 实例对象
     */
    @Override
    public CmsCheck update(CmsCheck cmsCheck) {
        this.cmsCheckDao.update(cmsCheck);
        return this.queryById(cmsCheck.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cmsCheckDao.deleteById(id) > 0;
    }

    /**
     * 按楼层获取房间信息
     */
    @Override
    public List<RoomOptionDto> fetchOptions(){
        List<RoomOptionDto> list = cmsCheckDao.fetchOptions();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setLabel(list.get(i).getValue() +"楼");
        }
        return list;
    }
    @Override
    public CheckDetail getDetail(String orderSn){
       return cmsCheckDao.getDetail(orderSn);
    }
    @Override
    public int checkOut(Long orderId){
        OmsOrder order = omsOrderMapper.selectByPrimaryKey(orderId);
        if (order==null||order.getStatus()!=1){
            return 0;
        }
        if (order.getRegisterStatus()==2){
            return -1;
        }
        order.setRegisterStatus(2);
        RmsRoom room=new RmsRoom();
        room.setId(order.getRoomId());
        room.setStatus(1);
        order.setCheckOutTime(new Date());
        rmsRoomMapper.updateByPrimaryKeySelective(room);
        return omsOrderMapper.updateByPrimaryKeySelective(order);
    }
    @Override
    public List<CmsCheck> fetchList(CmsCheck cmsCheckQueryParam, Integer pageSize, Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        CmsCheckExample example=new CmsCheckExample();
        CmsCheckExample.Criteria criteria = example.createCriteria();
        if (cmsCheckQueryParam.getName()!=null&&!cmsCheckQueryParam.getName().equals("")){
            criteria.andNameLike("%"+cmsCheckQueryParam.getName()+"%");
        }
        if (cmsCheckQueryParam.getCardId()!=null&&!cmsCheckQueryParam.getCardId().equals("")){
            criteria.andCardIdLike("%"+cmsCheckQueryParam.getCardId()+"%");
        }
        if (cmsCheckQueryParam.getOrderSn()!=null&&!cmsCheckQueryParam.getOrderSn().equals("")){
            criteria.andOrderSnLike("%"+cmsCheckQueryParam.getOrderSn()+"%");
        }
        return cmsCheckMapper.selectByExample(example);
    }
    @Override
    public int checkIn(CmsCheckPeopleQueryParams params){
        OmsOrder order=omsOrderMapper.selectByPrimaryKey(params.getOrderSn());
        if (order==null){
            return -1;
        }
        if (order.getRegisterStatus()==0){
            order.setRegisterStatus(1);
            order.setCheckInTime(new Date());
        }
        if (order.getCheckInTime()==null){
            order.setCheckInTime(new Date());
        }
        omsOrderMapper.updateByPrimaryKey(order);
        CmsCheck cmsCheck=new CmsCheck();
        cmsCheck.setCardId(params.getCardId());
        cmsCheck.setName(params.getName());
        cmsCheck.setCheckInTime(new Date());
        cmsCheck.setOrderSn(order.getOrderSn());
        return cmsCheckMapper.insert(cmsCheck);
    }
}