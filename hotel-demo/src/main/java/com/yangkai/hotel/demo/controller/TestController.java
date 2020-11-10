package com.yangkai.hotel.demo.controller;

import com.yangkai.hotel.commons.api.CommonResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * test(Test)表控制层
 *
 * @author makejava
 * @since 2020-11-10 18:32:10
 */
@RestController
@RequestMapping("test")
public class TestController {
    /**
     * 服务对象
     */
    private  final String REST_TEMPLATE_ADDRESS="http://127.0.0.1:8080";
    @Resource
    RestTemplate template;
    @RequestMapping(value = "/rest",method = RequestMethod.GET)
    public CommonResult<String> test(){
        CommonResult result = template.getForObject(REST_TEMPLATE_ADDRESS + "/test/rest", CommonResult.class);
        return result;
    }
}