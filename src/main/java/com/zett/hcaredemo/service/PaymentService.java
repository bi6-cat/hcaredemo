package com.zett.hcaredemo.service;

import java.util.List;
import java.util.UUID;

import com.zett.hcaredemo.entity.Payment;

public interface PaymentService {
    Payment processPayment(String appointmentCode);

    List<Payment> getAll();

    Payment getPaymentById(UUID id);
}