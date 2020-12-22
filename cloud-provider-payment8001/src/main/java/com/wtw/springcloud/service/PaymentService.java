package com.wtw.springcloud.service;

import com.wtw.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Long id);
}
