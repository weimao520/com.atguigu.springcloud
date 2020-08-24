package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import com.sun.applet2.AppletParameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WeiMao
 * @create 2020-07-24 23:56
 */
@RestController
@Slf4j
@RequestMapping("consumer/payment/")
public class OrderController {

    //  要想使用这个 服务名 去访问  需使用 @LoadBalanced注解 赋予RestTemplate负载均衡的能力
//    CLOUD-PAYMENT-SERVICE  微服务名称
    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancer loadBalancer;

    @GetMapping("create")
    public CommonResult<Payment> create( Payment payment) {
       return this.restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        return this.restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }
    @GetMapping("/getForEntity/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable Long id) {
        return this.restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class ).getBody();
    }

    Integer s = 1;

    @GetMapping("/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = this.discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if (instances.isEmpty() || instances.size() <= 0) {
            return null;
        }
        log.info("--------sss  {}----", ++s);
        ServiceInstance instances1 = this.loadBalancer.instances(instances);

        return this.restTemplate.getForObject(instances1.getUri()+"payment/lb", String.class);
    }

}
