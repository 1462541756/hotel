package com.yangkai.hotel.main.controller;

import com.yangkai.hotel.commons.api.CommonPage;
import com.yangkai.hotel.commons.api.CommonResult;
import com.yangkai.hotel.main.dto.CheckDetail;
import com.yangkai.hotel.main.dto.RoomOptionDto;
import com.yangkai.hotel.main.service.CmsCheckService;
import com.yangkai.hotel.mbg.model.CmsCheck;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "FmsGenerationController", description = "前台接口")
@RestController
@RequestMapping("front")
public class FmsGenerationController {
}