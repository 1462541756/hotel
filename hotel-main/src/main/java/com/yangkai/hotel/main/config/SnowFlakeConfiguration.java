package com.yangkai.hotel.main.config;

import cn.hutool.core.lang.Snowflake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author 杨锴
 * @date 2020/10/22 18:21
 * @description：
 */
@Configuration
public class SnowFlakeConfiguration {
    @Value("${snowflake.machineId}")
    private long machineId ;

    @Value("${snowflake.data-center-id}")
    private long dataCenterId ;

    @Bean(name = "snowFlake")
    @Scope(value = "singleton")
    public Snowflake snowFlake(){
        return new Snowflake(machineId,dataCenterId);
    }
}
