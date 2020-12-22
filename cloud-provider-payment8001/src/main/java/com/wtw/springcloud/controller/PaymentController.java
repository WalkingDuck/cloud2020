package com.wtw.springcloud.controller;

import com.wtw.springcloud.entities.CommonResult;
import com.wtw.springcloud.entities.Payment;
import com.wtw.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(Payment payment) {
        int res = paymentService.create(payment);
        if(res > 0) {
            return new CommonResult(200, "Success", res);
        } else {
            return new CommonResult(444, "Failed");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);

        if(payment != null) {
            return new CommonResult(200, "Success", payment);
        } else {
            return new CommonResult(444, "Fail");
        }
    }

}
