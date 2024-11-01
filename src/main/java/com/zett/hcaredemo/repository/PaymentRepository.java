package com.zett.hcaredemo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.hcaredemo.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID>{

}
