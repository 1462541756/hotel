package com.yangkai.hotel.main.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author 杨锴
 * @date 2020/10/19 16:09
 * @description：
 */
@Configuration
public class MailConfiguration {
    @Value("${spring.mail.host}")
    public  String HOST;
    @Value("${spring.mail.username}")
    public  String USERNAME;
    @Value("${spring.mail.password}")
    public  String PASSWORD;
    @Value("${spring.mail.default-encoding}")
    public  String DEFAULT_ENCODING;
    @Value("${spring.mail.from}")
    public  String FROM;
    @Value("${spring.mail.subject}")
    public  String SUBJECT;
}