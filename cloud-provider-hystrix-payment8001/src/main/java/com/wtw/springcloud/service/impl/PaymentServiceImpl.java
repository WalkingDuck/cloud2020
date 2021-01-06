package com.wtw.springcloud.service.impl;

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
}
