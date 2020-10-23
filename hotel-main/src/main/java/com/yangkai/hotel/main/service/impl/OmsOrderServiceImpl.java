package com.yangkai.hotel.main.service.impl;

import com.github.pagehelper.PageHelper;
import com.sun.org.apache.regexp.internal.RE;
import com.yangkai.hotel.main.bo.AdminUserDetails;
import com.yangkai.hotel.main.dao.OmsOrderDao;
import com.yangkai.hotel.main.dto.OmsOrderQueryParam;
import com.yangkai.hotel.main.service.OmsOrderService;
import com.yangkai.hotel.mbg.mapper.OmsOrderMapper;
import com.yangkai.hotel.mbg.mapper.RmsRoomMapper;
import com.yangkai.hotel.mbg.model.OmsOrder;
import com.yangkai.hotel.mbg.model.OmsOrderExample;
import com.yangkai.hotel.mbg.model.RmsRoom;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 订单表(OmsOrder)表服务实现类
 *
 * @author makejava
 * @since 2020-10-20 16:55:50
 */
@Service("omsOrderService")
public class OmsOrderServiceImpl implements OmsOrderService {
    @Resource
    private OmsOrderDao omsOrderDao;
    @Resource
    private OmsOrderMapper omsOrderMapper;
    @Resource
    private RmsRoomMapper rmsRoomMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OmsOrder queryById(Long id) {
        return this.omsOrderDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<OmsOrder> queryAllByLimit(int offset, int limit) {
        return this.omsOrderDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param params 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(OmsOrderQueryParam params) {
        RmsRoom rmsRoom=rmsRoomMapper.selectByPrimaryKey(params.getRoomId());
        params.setRoomId(null);
        params.setRoomName(rmsRoom.getName());
        params.setFloor(rmsRoom.getFloor());
        params.setSerial(rmsRoom.getSerial());
        params.setPic(rmsRoom.getPic());
        return omsOrderMapper.insertSelective(params);
    }

    /**
     * 修改数据
     *
     * @param omsOrder 实例对象
     * @return 实例对象
     */
    @Override
    public OmsOrder update(OmsOrder omsOrder) {
        this.omsOrderDao.update(omsOrder);
        return this.queryById(omsOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.omsOrderDao.deleteById(id) > 0;
    }

    @Override
    public List<OmsOrder> list(OmsOrder omsOrderQueryParam, Integer pageSize, Integer pageNum,boolean isVip) {
        PageHelper.startPage(pageNum,pageSize);
        OmsOrderExample example = new OmsOrderExample();
        OmsOrderExample.Criteria criteria = example.createCriteria();
        if (!isVip){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username=( (AdminUserDetails)authentication.getPrincipal()).getUsername();
            criteria.andUsernameEqualTo(username);
        }else {
            if (omsOrderQueryParam.getUsername()!=null){
            criteria.andUsernameLike("%"+omsOrderQueryParam.getUsername()+"%");
            }
        }
        if (omsOrderQueryParam.getOrderSn()!=null){
            criteria.andOrderSnLike("%"+omsOrderQueryParam.getOrderSn()+"%");
        }
        if (omsOrderQueryParam.getSourceType()!=null){
            criteria.andSourceTypeEqualTo(omsOrderQueryParam.getSourceType());
        }
        if (omsOrderQueryParam.getPayType()!=null){
            criteria.andPayTypeEqualTo(omsOrderQueryParam.getPayType());
        }
        if (omsOrderQueryParam.getOrderType()!=null){
            criteria.andOrderTypeEqualTo(omsOrderQueryParam.getOrderType());
        }
        if (omsOrderQueryParam.getStatus()!=null){
            criteria.andStatusEqualTo(omsOrderQueryParam.getStatus());
        }
        example.setOrderByClause("status");
        return  omsOrderMapper.selectByExample(example);
    }
    @Override
    public int cancel(Long orderId,boolean isVip){
        OmsOrder order=omsOrderMapper.selectByPrimaryKey(orderId);
        //用户只能取消自己的订单，操作员可以取消所有人的订单
        if (!isVip){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username=( (AdminUserDetails)authentication.getPrincipal()).getUsername();
            if (username==null){
                //用户未登录
                return 0;
            }
            if (order==null||!username.equals(order.getUsername())){
                //该订单不属于登录者账号
                return 1;
            }
        }

        if (order.getStatus()!=0){
            //只能取消未付款的订单
            return 2;
        }

        order.setStatus(2);

        if (omsOrderMapper.updateByPrimaryKeySelective(order)!=1){
            //修改失败
            return 3;
        }else {
            //修改成功
            return 4;
        }
    }


    public int submit(Long orderId,Integer payType){
        OmsOrder order =new OmsOrder();
        order.setStatus(1);
        order.setPaymentTime(new Date());
        order.setPayType(payType);
        order.setId(orderId);
        return  omsOrderMapper.updateByPrimaryKeySelective(order);
    }

    public int commit(Long orderId, String commitPassword){
        //未实现支付接口先用固定字符模拟
        if (!"123456".equals(commitPassword)){
            return 0;
        }
        OmsOrder order=new OmsOrder();
        order.setId(orderId);
        order.setStatus(1);
        return omsOrderMapper.updateByPrimaryKeySelective(order);
    }

}