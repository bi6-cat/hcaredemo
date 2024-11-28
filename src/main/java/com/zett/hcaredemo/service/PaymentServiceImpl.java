package com.zett.hcaredemo.service;

import com.zett.hcaredemo.entity.Payment;
import com.zett.hcaredemo.entity.HealthCheckAppointment;
import com.zett.hcaredemo.repository.PaymentRepository;
import com.zett.hcaredemo.repository.HealthCheckAppointmentRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private HealthCheckAppointmentRepository healthCheckAppointmentRepository;

    @Transactional
    @Override
    public Payment processPayment(String appointmentCode) {
        HealthCheckAppointment appointment = healthCheckAppointmentRepository.findByCode(appointmentCode)
            .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // Create new payment
        Payment payment = new Payment();
        payment.setAmount(appointment.getDepartmentService().getPrice());
        payment.setAppointmentCode(appointmentCode);
        payment.setPaymentMethod("QR PAY"); // Mặc định thanh toán tiền mặt
        payment.setStatus("PAID");
        payment.setPatient(appointment.getPatient());

        // Update appointment status
        appointment.setStatus("PAID");
        healthCheckAppointmentRepository.save(appointment);

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(UUID id) {
        return paymentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}   