package com.wtw.springcloud.controller;

import com.wtw.springcloud.entities.CommonResult;
import com.wtw.springcloud.entities.Payment;
import com.wtw.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        int res = paymentService.create(payment);
        if(res > 0) {
            return new CommonResult(200, "Success", res);
        } else {
            return new CommonResult<Payment>(444, "Failed");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);

        if(payment != null) {
            return new CommonResult<Payment>(200, "Success", payment);
        } else {
            return new CommonResult<Payment>(444, "Fail");
        }
    }

}
