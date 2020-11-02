package com.yangkai.hotel.main.controller;

import com.yangkai.hotel.commons.api.CommonPage;
import com.yangkai.hotel.commons.api.CommonResult;
import com.yangkai.hotel.main.bo.AdminUserDetails;
import com.yangkai.hotel.mbg.model.EmsEvent;
import com.yangkai.hotel.main.service.EmsEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * (EmsEvent)事件管理
 *
 * @author makejava
 * @since 2020-11-02 18:45:20
 */
@Api(tags = "EmsEventController", description = "事件管理")
@RestController
@RequestMapping("event")
public class EmsEventController {

    @Autowired
    private EmsEventService emsEventService;

    @ApiOperation("获取事件列表")
    @RequestMapping(value = "/fetchList", method = RequestMethod.GET)
    public CommonResult<CommonPage<EmsEvent>> fetchList(EmsEvent emsEvent,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<EmsEvent> list= this.emsEventService.fetchList(emsEvent,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("获取单个事件")
    @RequestMapping(value = "/getEventById/{id}", method = RequestMethod.GET)
    public CommonResult getEventById(@PathVariable Long id) {
        return CommonResult.success(this.emsEventService.getEventById(id));
    }

    @ApiOperation("删除事件")
    @RequestMapping(value = "/deleteEventById/{id}", method = RequestMethod.GET)
    public CommonResult deleteEventById(@PathVariable Long id) {
        int result= this.emsEventService.deleteEventById(id);
        switch (result){
            case 1:return CommonResult.success("删除成功");
            case 2:return CommonResult.failed("事件不存在");
            case 3:return CommonResult.forbidden("无法操作他人创建的事件");
            case 4:return CommonResult.failed("事件已提交,无法删除");
            default:return CommonResult.failed("删除失败");
        }
    }

    @ApiOperation("取消上报")
    @RequestMapping(value = "/cancelReportById/{id}", method = RequestMethod.POST)
    public CommonResult cancelReportById(@PathVariable Long id) {
        int result= this.emsEventService.cancelReportById(id);
        switch (result){
            case 1:return CommonResult.success("取消上报成功");
            case 2:return CommonResult.failed("事件不存在");
            case 3:return CommonResult.forbidden("无法操作他人创建的事件");
            case 4:return CommonResult.failed("事件审核通过,无法取消上报");
            default:return CommonResult.failed("取消上报失败");
        }
    }

    @ApiOperation("上报事件/保存草稿")
    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public CommonResult report(@RequestBody EmsEvent params) {
        EmsEvent emsEvent=new EmsEvent();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=( (AdminUserDetails)authentication.getPrincipal()).getUsername();
        if (username==null){
            return CommonResult.forbidden("无操作权限");
        }

        emsEvent.setSubject(params.getSubject());
        emsEvent.setType(params.getType());
        emsEvent.setDescription(params.getDescription());
        emsEvent.setPic(params.getPic());

        emsEvent.setReportPeople(username);
        emsEvent.setCreateTime(new Date());
        emsEvent.setStatus(0);
        if (params.getCheckStatus()!=null&&params.getStatus()==1){
            //1为提交
            emsEvent.setStatus(1);
        }else {
            //0为仅保存，不提交
            emsEvent.setStatus(0);
        }
        int count= this.emsEventService.report(emsEvent);
        if (count==1){
            return CommonResult.success("上报成功");
        }else {
            return CommonResult.failed("上报失败");
        }
    }

    @ApiOperation("从草稿提交上报事件")
    @RequestMapping(value = "/reportFromDraft/{id}", method = RequestMethod.POST)
    public CommonResult reportFromDraft(@PathVariable Long id) {
        int result= this.emsEventService.reportFromDraft(id);
        switch (result){
            case 1:return CommonResult.success("上报成功");
            case 2:return CommonResult.failed("事件不存在");
            case 3:return CommonResult.forbidden("无法操作他人创建的事件");
            case 4:return CommonResult.failed("事件已上报,无法再次上报");
            default:return CommonResult.failed("上报失败");
        }
    }

}