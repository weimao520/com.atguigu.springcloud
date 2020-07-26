package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author WeiMao
 * @create 2020-07-24 23:56
 */
@RestController
@Slf4j
public class OrderController {


    public static final String PAYMENT_URL="http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("consumer/payment/create")
    public CommonResult<Payment> create( Payment payment) {
       return this.restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        return this.restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }
}