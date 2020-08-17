package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author WeiMao
 * @create 2020-08-09 17:13
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderzkMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderzkMain80.class, args);
    }
}
