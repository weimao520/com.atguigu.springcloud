package com.atguigu.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author WeiMao
 * @create 2020-07-25 0:29
 */
@Configuration
public class ApplicationContextConfig {


    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
