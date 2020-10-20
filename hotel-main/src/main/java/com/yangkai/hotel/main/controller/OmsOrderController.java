package com.yangkai.hotel.main.controller;

import com.yangkai.hotel.commons.api.CommonPage;
import com.yangkai.hotel.commons.api.CommonResult;
import com.yangkai.hotel.main.bo.AdminUserDetails;
import com.yangkai.hotel.main.dto.OmsOrderSubmitDto;
import com.yangkai.hotel.main.service.OmsOrderService;
import com.yangkai.hotel.mbg.model.OmsOrder;
import io.swagger.annotations.ApiOperation;
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
@RestController
@RequestMapping("order")
public class OmsOrderController {
    /**
     * 服务对象
     */
    @Resource
    private OmsOrderService omsOrderService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/getOrderById")
    @ApiOperation("通过id查询订单")
    public OmsOrder getOrderById(Long id) {
        return this.omsOrderService.queryById(id);
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

    @ApiOperation("内部人员取消订单")
    @RequestMapping(value = "/cancel/vip/{orderId}", method = RequestMethod.GET)
    public CommonResult cancelVip(@NotNull @PathVariable Long orderId) {
        int count = omsOrderService.cancel(orderId,true);
        if (count == 0) {
            return CommonResult.failed("订单取消失败");
        } else {
            return CommonResult.success("订单取消成功");
        }
    }

    @ApiOperation("创建订单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(OmsOrder omsOrderQueryParam) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=( (AdminUserDetails)authentication.getPrincipal()).getUsername();
        omsOrderQueryParam.setUsername(username);
        omsOrderQueryParam.setCreateTime(new Date());
        omsOrderQueryParam.setOrderSn(username+new Date().getTime());
        OmsOrder result = omsOrderService.insert(omsOrderQueryParam);
        if (result==null){
            return CommonResult.failed("订单创建失败");
        }else {
            return CommonResult.success("订单创建成功");
        }
    }

    @ApiOperation("用户取消订单")
    @RequestMapping(value = "/cancel/user/{orderId}", method = RequestMethod.GET)
    public CommonResult cancelUser(@NotNull @PathVariable Long orderId) {
        int count = omsOrderService.cancel(orderId,false);
        if (count == 0) {
            return CommonResult.failed("订单取消失败");
        } else {
            return CommonResult.success("订单取消成功");
        }
    }

    @ApiOperation("用户支付")
    @RequestMapping(value = "/submit/", method = RequestMethod.POST)
    public CommonResult submit(OmsOrderSubmitDto omsOrderSubmitDto) {
        int count = omsOrderService.submit(omsOrderSubmitDto.getOrderId(),omsOrderSubmitDto.getPayType());
        if (count == 0) {
            return CommonResult.failed("支付失败");
        } else {
            return CommonResult.success("支付成功");
        }
    }

}