package com.yangkai.hotel.commons.hotel_main;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

//@SpringBootTest
@ContextConfiguration
class HotelMainApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(new Date());
        SimpleDateFormat format = new SimpleDateFormat();
        System.out.println();
    }

}
