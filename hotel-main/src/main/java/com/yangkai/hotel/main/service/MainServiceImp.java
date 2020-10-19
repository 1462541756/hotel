package com.yangkai.hotel.main.service;
import com.yangkai.hotel.main.config.MailConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


/**
 * @author 杨锴
 * @date 2020/10/19 15:39
 * @description：
 */
@Service
public class MainServiceImp  implements MailService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    MailConfiguration mailConfiguration;

    private boolean sendActivationMail(String to,String content) {
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailConfiguration.FROM);
            helper.setSubject(mailConfiguration.SUBJECT);
            helper.setTo(to);
            helper.setText(content, true);
            mailSender.send(message);
            //日志信息
            logger.info("邮件已经发送。");
            return true;
        } catch (MessagingException e) {
            logger.error("发送邮件时发生异常！", e);
            return false;
        }
    }
      @Override
    public boolean sentMail(String mailAddress,String content,String verificationCode,long time,TimeUnit timeUnit) {

        boolean result=sendActivationMail(mailAddress,content);
        if (!result)return false;
        //保存邮箱账号和验证码，到redis缓存中，30分钟后过期
        redisTemplate.opsForValue().set(mailAddress,verificationCode,time, timeUnit);
        return true;
    }
}
