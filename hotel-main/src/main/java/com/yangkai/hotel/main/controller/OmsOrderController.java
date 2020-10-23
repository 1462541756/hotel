package com.yangkai.hotel.main.controller;

import cn.hutool.core.lang.Snowflake;
import com.yangkai.hotel.commons.api.CommonPage;
import com.yangkai.hotel.commons.api.CommonResult;
import com.yangkai.hotel.main.bo.AdminUserDetails;
import com.yangkai.hotel.main.dto.OmsOrderQueryParam;
import com.yangkai.hotel.main.dto.OmsOrderSubmitDto;
import com.yangkai.hotel.main.service.OmsOrderService;
import com.yangkai.hotel.mbg.model.OmsOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 订单表(OmsOrder)表控制层
 *
 * @author makejava
 * @since 2020-10-20 16:55:50
 */
@Api(tags = "OmsOrderController", description = "订单管理")
@RestController
@RequestMapping("order")
public class OmsOrderController {
    /**
     * 服务对象
     */
    @Resource
    private OmsOrderService omsOrderService;
    @Autowired
    Snowflake snowflake;


    @ApiOperation("通过id查询订单")
    @RequestMapping(value = "/getOrderById/{orderID}", method = RequestMethod.GET)
    public CommonResult<OmsOrder> getOrderById(@PathVariable Long orderID) {
        return CommonResult.success(this.omsOrderService.queryById(orderID));
    }

    @ApiOperation("用户分页查询订单")
    @RequestMapping(value = "/list/user", method = RequestMethod.GET)
    public CommonResult<CommonPage<OmsOrder>> listVip(OmsOrder omsOrderQueryParam,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        List<OmsOrder> orderList = omsOrderService.list(omsOrderQueryParam, pageSize, pageNum,false);
        return CommonResult.success(CommonPage.restPage(orderList));
    }
    @ApiOperation("内部人员分页查询订单")
    @RequestMapping(value = "/list/vip", method = RequestMethod.GET)
    public CommonResult<CommonPage<OmsOrder>> listUser(OmsOrder omsOrderQueryParam,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrder> orderList = omsOrderService.list(omsOrderQueryParam, pageSize, pageNum,true);
        return CommonResult.success(CommonPage.restPage(orderList));
    }


    @ApiOperation("创建订单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(OmsOrderQueryParam omsOrderQueryParam ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=( (AdminUserDetails)authentication.getPrincipal()).getUsername();
        OmsOrderQueryParam orderParam=new OmsOrderQueryParam();
        orderParam.setUsername(username);
        orderParam.setCreateTime(new Date());
        orderParam.setOrderSn(Long.toString(snowflake.nextId()));
        orderParam.setStatus(0);
        orderParam.setRoomId(omsOrderQueryParam.getRoomId());
        orderParam.setFloor(omsOrderQueryParam.getFloor());
        orderParam.setRoomName(omsOrderQueryParam.getRoomName());
        orderParam.setNote(omsOrderQueryParam.getNote());
        int  count = omsOrderService.insert(orderParam);
        if (count==1){
            return CommonResult.success("订单创建成功");
        }else {
            return CommonResult.failed("订单创建失败");
        }
    }
    @ApiOperation("内部人员取消订单")
    @RequestMapping(value = "/cancel/vip/{orderId}", method = RequestMethod.GET)
    public CommonResult cancelVip(@NotNull @PathVariable Long orderId) {
        int result = omsOrderService.cancel(orderId,true);
        switch(result){
            case 0:return CommonResult.failed("用户未登录");
            case 1:return CommonResult.failed("该订单不属于登录者账号");
            case 2:return CommonResult.failed("只能取消未付款的订单");
            case 3:return CommonResult.failed("取消失败");
            case 4:return CommonResult.success("取消成功");
            default:return CommonResult.failed("取消失败");
        }
    }
    @ApiOperation("用户取消订单")
    @RequestMapping(value = "/cancel/user/{orderId}", method = RequestMethod.POST)
    public CommonResult cancelUser(@NotNull @PathVariable Long orderId) {
        int result = omsOrderService.cancel(orderId,false);
        switch(result){
            case 0:return CommonResult.failed("用户未登录");
            case 1:return CommonResult.failed("该订单不属于登录者账号");
            case 2:return CommonResult.failed("只能取消未付款的订单");
            case 3:return CommonResult.failed("取消失败");
            case 4:return CommonResult.success("取消成功");
            default:return CommonResult.failed("取消失败");
        }
    }

    @ApiOperation("用户支付")
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public CommonResult submit(OmsOrderSubmitDto omsOrderSubmitDto) {
        int count = omsOrderService.submit(omsOrderSubmitDto.getOrderId(),omsOrderSubmitDto.getPayType());
        if (count == 0) {
            return CommonResult.failed("支付失败");
        } else {
            return CommonResult.success("支付成功");
        }
    }

    @ApiOperation("线下支付（内部人员操作）")
    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    public CommonResult commit(@RequestParam(value = "orderId") Long orderId,
                                @RequestParam(value = "commitPassword") String commitPassword) {
        int count = omsOrderService.commit(orderId,commitPassword);
        if (count == 0) {
            return CommonResult.failed("提交失败");
        } else {
            return CommonResult.success("提交成功");
        }
    }

}