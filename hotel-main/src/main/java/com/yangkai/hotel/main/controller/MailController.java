package com.yangkai.hotel.main.controller;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.yangkai.hotel.commons.api.CommonResult;
import com.yangkai.hotel.commons.util.CommonsUtil;
import com.yangkai.hotel.main.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author 杨锴
 * @date 2020/10/19 18:29
 * @description：
 */
@Api(tags = "MailController", description = "mail管理")
@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    MailService mailService;
    @ApiOperation(value = "发送邮箱验证码")
    @RequestMapping(value = "/sentMailVerificationCode", method = RequestMethod.GET)
    public CommonResult sentMailVerificationCode(@RequestParam("mail") String mailAddress){
        if (!CommonsUtil.validEmail(mailAddress)){
            return  CommonResult.failed("邮箱格式错误，发送失败！");
        }
        String verificationCode= CommonsUtil.getRandomVerificationCode(6);
        String content="您的注册验证码是  "+verificationCode+"  该验证码将在30分钟内失效";
        boolean result=mailService.sentMail(mailAddress,content,verificationCode,30,TimeUnit.MINUTES);
        if (result){
            return  CommonResult.success("发送成功！");
        }else {
            return  CommonResult.failed("发送失败！");
        }
    }


}
