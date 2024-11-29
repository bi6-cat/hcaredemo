package com.zett.hcaredemo.controller;

import com.zett.hcaredemo.entity.HealthCheckAppointment;
import com.zett.hcaredemo.entity.Payment;
import com.zett.hcaredemo.service.HealthCheckAppointmentService;
import com.zett.hcaredemo.service.PaymentService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("patient/payments")

public class PaymentController {
    private final PaymentService paymentService;
    private final HealthCheckAppointmentService healthCheckAppointmentService;

    @Autowired
    public PaymentController(PaymentService paymentService, HealthCheckAppointmentService healthCheckAppointmentService) {
        this.paymentService = paymentService;
        this.healthCheckAppointmentService = healthCheckAppointmentService;
    }

    @GetMapping("/payment-history")
    public String showPaymentHistory(Model model) {
        List<Payment> payments = paymentService.getAll();
        model.addAttribute("payments", payments);
        return "patient/payment/history";
    }
    
    @GetMapping("/detail/{id}")
    public String showPaymentDetail(@PathVariable UUID id, Model model) {
        Payment payment = paymentService.getPaymentById(id);
        model.addAttribute("payment", payment);
        return "patient/payment/detail";
    }
    @GetMapping("/confirm/{code}")
    public String showConfirmPayment(@PathVariable String code, Model model) {
        HealthCheckAppointment appointment = healthCheckAppointmentService.getByCode(code);
        model.addAttribute("appointment", appointment);
        return "patient/payment/confirm";
    }

    @PostMapping("/process/{code}")
    public String processPayment(@PathVariable String code) {
        HealthCheckAppointment appointment = healthCheckAppointmentService.getByCode(code);
        paymentService.processPayment(code);
        return "redirect:/patient/appointments/" + appointment.getId();
    }
}