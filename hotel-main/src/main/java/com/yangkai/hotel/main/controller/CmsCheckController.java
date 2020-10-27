package com.yangkai.hotel.main.controller;

import cn.hutool.core.lang.Snowflake;
import com.yangkai.hotel.commons.api.CommonPage;
import com.yangkai.hotel.commons.api.CommonResult;
import com.yangkai.hotel.main.bo.AdminUserDetails;
import com.yangkai.hotel.main.dto.*;
import com.yangkai.hotel.main.service.CmsCheckService;
import com.yangkai.hotel.main.service.OmsOrderService;
import com.yangkai.hotel.mbg.model.CmsCheck;
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
 * @author makejava
 * @since 2020-10-20 16:55:50
 */
@Api(tags = "CmsCheckController", description = "入住管理")
@RestController
@RequestMapping("check")
public class CmsCheckController {
    @Autowired
    private CmsCheckService cmsCheckService;

    @ApiOperation("获取入住信息列表")
    @RequestMapping(value = "/fetchList", method = RequestMethod.GET)
    public CommonResult<CommonPage<CmsCheck>> fetchList(CmsCheck cmsCheckQueryParam,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<CmsCheck>  list=cmsCheckService.fetchList(cmsCheckQueryParam,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }


    @ApiOperation("获取房间选项")
    @RequestMapping(value = "/fetchOptions", method = RequestMethod.GET)
    public CommonResult<List<RoomOptionDto>> fetchOptions() {
       List<RoomOptionDto>  list=cmsCheckService.fetchOptions();
        return CommonResult.success(list);
    }

    @ApiOperation("获取入住信息")
    @RequestMapping(value = "/getDetail", method = RequestMethod.GET)
    public CommonResult getDetail(String orderSn) {
        CheckDetail detail = cmsCheckService.getDetail(orderSn);
        if (detail==null){
            CommonResult.failed("未查到相关数据");
        }
        return CommonResult.success(detail);
    }
    @ApiOperation("退房")
    @RequestMapping(value = "/checkOut/{orderId}", method = RequestMethod.POST)
    public CommonResult getDetail(@PathVariable Long orderId) {
        int count = cmsCheckService.checkOut(orderId);
        if (count==-1){
            return CommonResult.failed("该订单已退房");
        }
        if (count!=1){
           return CommonResult.failed("退房失败");
        }else {
            return CommonResult.success("退房成功");
        }
    }

    @RequestMapping(value = "/checkPeople", method = RequestMethod.POST)
    public CommonResult checkPeople(@RequestBody CmsCheckPeopleQueryParams params) {
        int  count = cmsCheckService.checkIn(params);
        if (count!=1){
            CommonResult.failed("登记入住失败");
        }
        return CommonResult.success("登记入住成功");
    }
}