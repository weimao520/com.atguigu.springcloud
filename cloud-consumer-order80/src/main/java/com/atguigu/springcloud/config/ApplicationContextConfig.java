package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author WeiMao
 * @create 2020-07-25 0:29
 */
@Configuration
public class ApplicationContextConfig {


    /**
     * 赋予RestTemplate负载均衡的能力
     * @return
     */
    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
