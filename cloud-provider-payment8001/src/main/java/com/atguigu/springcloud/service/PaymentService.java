package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.Payment;

/**
 * @author WeiMao
 * @create 2020-07-23 23:06
 */
public interface PaymentService {


    public int created(Payment payment);

    public Payment getPaymentById(Long id);
}
