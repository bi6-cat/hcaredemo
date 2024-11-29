package com.zett.hcaredemo.controller;

import com.zett.hcaredemo.dto.medicine.PrescriptionForm;
import com.zett.hcaredemo.entity.Doctor;
import com.zett.hcaredemo.entity.HealthCheckAppointment;
import com.zett.hcaredemo.entity.User;
import com.zett.hcaredemo.exception.ResourceNotFoundException;
import com.zett.hcaredemo.repository.DoctorRepository;
import com.zett.hcaredemo.repository.MedicineRepository;
import com.zett.hcaredemo.repository.UserRepository;
import com.zett.hcaredemo.service.HealthCheckAppointmentService;
import com.zett.hcaredemo.service.MedicineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/doctor/appointments")
public class DoctorAppointmentController {

    @Autowired
    private HealthCheckAppointmentService healthCheckAppointmentService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public String listAppointments(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        // Lấy tên người dùng từ Authentication
        String username = authentication.getName();
        // Tìm User bằng username
        User user = userRepository.findByUsername(username);
        // Lấy thông tin bác sĩ liên kết với user
        Doctor doctor = doctorRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found for user with id " + user.getId()));
        UUID doctorId = doctor.getId();        
        List<HealthCheckAppointment> appointments = healthCheckAppointmentService.findByDoctorId(doctorId);
        model.addAttribute("appointments", appointments);
        return "doctors/appointment/list";
    }

    @GetMapping("/{id}")
    public String viewAppointment(@PathVariable UUID id, Model model) {
        var appointment = healthCheckAppointmentService.findById(id);
        model.addAttribute("prescriptionForm", new PrescriptionForm());
        model.addAttribute("appointment", appointment);
        model.addAttribute("id", id);
        model.addAttribute("medicines", medicineService.getAllMedicines());
        return "doctors/appointment/detail";
    }

    @PostMapping("/{id}/confirm")
    public String confirmAppointment(@PathVariable UUID id) {
        healthCheckAppointmentService.confirmAppointment(id);
        return "redirect:/doctor/appointments";
    }

    @PostMapping("/{id}/cancel")
    public String cancelAppointment(@PathVariable UUID id) {
        healthCheckAppointmentService.cancelAppointment(id);
        return "redirect:/doctor/appointments";
    }
}