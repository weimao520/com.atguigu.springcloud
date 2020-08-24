package com.atguigu.springcloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WeiMao
 * @create 2020-08-19 21:48
 */
@Component
@Slf4j
public class MyLB implements LoadBalancer {


    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 第几次访问
     *
     * @return
     */
    public final int getAndInstance() {

        int current ;
        int next;
        do {
            current=this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : 1 + current;

            // cas 比较并替换  第一个参数预期值，第二个更新值
            // 如果预期值等于当前值，则更新
        } while (!this.atomicInteger.compareAndSet(current, next));
        log.info("第---{}---访问次", next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {

        int index = this.getAndInstance() % serviceInstances.size();
        return serviceInstances.get(index);
    }

}
