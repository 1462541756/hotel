package com.yangkai.hotel.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 杨锴
 * @date 2020/11/10 18:57
 * @description：
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean
//    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
