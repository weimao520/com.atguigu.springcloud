package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author WeiMao
 * @create 2020-07-23 23:13
 */
@RestController
@Slf4j
public class PaymentController {


    @Resource
    private PaymentService paymentService;

    @PostMapping("payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        int result = this.paymentService.created(payment);
        log.info("*********插入结果*************" + result);
        if (result > 0) {
            return new CommonResult<>(200,"插入成功",payment);
        }else {
            return new CommonResult<>(444,"插入数据库失败");
        }
    }

    @GetMapping("payment/get/{id}")
    public CommonResult getPaymentById( @PathVariable Long  id){
        Payment result = this.paymentService.getPaymentById(id);
        log.info("*********插入结果*************" + result);

            return new CommonResult(200,"查询成功",result);
    }

}
