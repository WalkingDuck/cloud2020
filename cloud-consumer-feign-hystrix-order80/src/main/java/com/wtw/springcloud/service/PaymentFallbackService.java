package com.wtw.springcloud.service;

import com.wtw.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "paymentInfo_Timeout-------FALLBACK";
    }

    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK-------FALLBACK";
    }
}
