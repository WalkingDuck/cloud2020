package com.wtw.springcloud.service;

import org.springframework.stereotype.Service;

public interface PaymentService {
    String paymentInfo_OK(Integer id);
    String paymentInfo_Timeout(Integer id);
    String paymentCircuitBreaker(Integer id);
}
