package com.wtw.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wtw.springcloud.service.PaymentService;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK: Thread: " + Thread.currentThread().getName() + ", id = " + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @Override
    public String paymentInfo_Timeout(Integer id) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "paymentInfo_Timeout: Thread: " + Thread.currentThread().getName() + ", id = " + id;
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "paymentInfo_TimeoutHandler: Thread: " + Thread.currentThread().getName() + ", id = " + id;
    }

    // 服务熔断
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //关闸时间
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //请求错误率率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if(id < 0) {
            throw new RuntimeException("id < 0!");
        }

        String simpleUUID = IdUtil.simpleUUID();
        return "paymentCircuitBreaker: Thread: " + Thread.currentThread().getName() + ", id = " + id + ", simpleUUID = " + simpleUUID;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "paymentCircuitBreaker_fallback: Error! id < 0, id = " + id;
    }
}
