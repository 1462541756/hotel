package com.yangkai.hotel.main.controller;

import com.yangkai.hotel.commons.api.CommonPage;
import com.yangkai.hotel.commons.api.CommonResult;
import com.yangkai.hotel.main.dto.UpdateRoomsStatusParams;
import com.yangkai.hotel.main.service.RmsRoomService;
import com.yangkai.hotel.mbg.model.RmsRoom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * (RmsRoom)表控制层
 *
 * @author makejava
 * @since 2020-10-12 09:45:55
 */
@Api(tags = "RmsRoomController", description = "房间管理")
@RestController
@RequestMapping("room")
public class RmsRoomController {
    /**
     * 服务对象
     */
    @Autowired
    private RmsRoomService roomService;

    @ApiOperation("根据房间分页获取房间列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<RmsRoom>> list(RmsRoom rmsRoomQueryParam,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<RmsRoom> roomList = roomService.list(rmsRoomQueryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roomList));
    }

    @ApiOperation("根据id查询房间信息")
    @RequestMapping(value = "/getRoomById/{id}", method = RequestMethod.GET)
    public CommonResult getRoomById(@PathVariable Long id) {
        RmsRoom room = roomService.getRoomById(id);
        return CommonResult.success(room);
    }
    @ApiOperation("根据id修改房间信息")
    @RequestMapping(value = "/updateRoom", method = RequestMethod.POST)
    public CommonResult updateRoom(@RequestBody RmsRoom room) {

        int count = roomService.updateRoom(room);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    @ApiOperation("添加房间信息")
    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    public CommonResult addRoom(@RequestBody RmsRoom room) {

        int count = roomService.addRoom(room);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    @ApiOperation("根据id删除房间信息")
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.GET)
    public CommonResult deleteById(@PathVariable Long id) {

        int count = roomService.deleteById(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量启动/停用房间")
    @RequestMapping(value = "/updateRoomsStatus", method = RequestMethod.POST)
    public CommonResult updateRoomsStatus(@RequestBody UpdateRoomsStatusParams params) {
        int count = roomService.updateRoomsStatus(params.getStatus(), Arrays.asList(params.getIds()));
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

}