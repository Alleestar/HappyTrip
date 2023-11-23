package com.ssafy.enjoytrip.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BeanUtil {

    @Bean
    public Random getRandom(){
        return new Random( System.currentTimeMillis());
    }
}
