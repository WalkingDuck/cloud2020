package com.wtw.springcloud.controller;

import com.wtw.springcloud.entities.CommonResult;
import com.wtw.springcloud.entities.Payment;
import com.wtw.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        int res = paymentService.create(payment);
        if(res > 0) {
            return new CommonResult(200, "Create Success, Port: " + serverPort, res);
        } else {
            return new CommonResult<Payment>(444, "Failed");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);

        if(payment != null) {
            return new CommonResult<Payment>(200, "Get Success, Port: " + serverPort, payment);
        } else {
            return new CommonResult<Payment>(444, "Fail");
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        return this.discoveryClient;
    }

}
