package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author WeiMao
 * @create 2020-07-23 23:13
 */
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {


    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("discovery")
    public Object discovery() {
//        获取服务列表对应 注册中心上有几个微服务
        List<String> services = this.discoveryClient.getServices();
        for (String service : services) {
            log.info("*********server:{}", service);
        }
//        获取该微服务下的全部实例
        List<ServiceInstance> instances = this.discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("服务id:{}-主机名称:{}-主机端口号{}-URI地址{}"
                    ,instance.getServiceId(),instance.getHost(),instance.getPort(),instance.getUri());
        }
        return this.discoveryClient;
    }

    @PostMapping("create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        int result = this.paymentService.created(payment);
        log.info("*********插入结果*************serverPort"+serverPort ,result);
        if (result > 0) {
            return new CommonResult<>(200,"插入成功",payment);
        }else {
            return new CommonResult<>(444,"插入数据库失败");
        }
    }

    @GetMapping("get/{id}")
    public CommonResult getPaymentById( @PathVariable Long  id){
        Payment result = this.paymentService.getPaymentById(id);
        log.info("*********插入结果*************" + result);

            return new CommonResult(200,"查询成功---------serverPort"+serverPort,result);
    }


    @GetMapping("/lb")
    public String getServerPort(){
        return this.serverPort;
    }

}
