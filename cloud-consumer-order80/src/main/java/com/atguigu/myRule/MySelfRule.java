package com.atguigu.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WeiMao
 * @create 2020-08-14 21:35
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        //  随机
        return new RandomRule();
    }
}
