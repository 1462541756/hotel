package com.yangkai.hotel.main.controller;

import com.yangkai.hotel.commons.api.CommonPage;
import com.yangkai.hotel.commons.api.CommonResult;
import com.yangkai.hotel.commons.util.CommonsUtil;
import com.yangkai.hotel.main.dto.CheckDetail;
import com.yangkai.hotel.main.dto.CmsCheckPeopleQueryParams;
import com.yangkai.hotel.main.dto.RoomOptionDto;
import com.yangkai.hotel.main.service.CmsCheckService;
import com.yangkai.hotel.mbg.model.CmsCheck;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author makejava
 * @since 2020-10-20 16:55:50
 */
@Api(tags = "TestController", description = "rest调用测试")
@RestController
@RequestMapping("test")
public class TestController {

    @Value("${spring.application.name}")
    private String APPLICATION_NAME;
    @Value("${server.port}")
    private String SERVER_PORT;


    @ApiOperation("rest调用测试接口")
    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    public CommonResult<String> test(){
        return CommonResult.success(APPLICATION_NAME+":"+SERVER_PORT);
    }

}