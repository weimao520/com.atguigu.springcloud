package com.atguigu.springcloud7001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author WeiMao
 * @create 2020-07-28 23:26
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaMain7002 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7002.class, args);
    }
}
