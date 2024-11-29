package com.zett.hcaredemo.controller;

import com.zett.hcaredemo.entity.Doctor;
import com.zett.hcaredemo.entity.DoctorSchedule;
import com.zett.hcaredemo.entity.User;
import com.zett.hcaredemo.exception.ResourceNotFoundException;
import com.zett.hcaredemo.repository.DoctorRepository;
import com.zett.hcaredemo.repository.DoctorScheduleRepository;
import com.zett.hcaredemo.repository.UserRepository;
import com.zett.hcaredemo.service.DoctorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/doctor/schedules")
public class DoctorScheduleController {

    @Autowired
    private DoctorScheduleRepository doctorScheduleRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorScheduleService doctorScheduleService;

    @GetMapping()
    public String getDoctorSchedule(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Lấy tên người dùng từ Authentication
        String username = authentication.getName();
        // Tìm User bằng username
        User user = userRepository.findByUsername(username);
        // Lấy thông tin bác sĩ liên kết với user
        Doctor doctor = doctorRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found for user with id " + user.getId()));
        UUID doctorId = doctor.getId();
        LocalDate startDate = LocalDate.now();
        doctorScheduleService.updateExpiredSchedules();
        doctorScheduleService.generateSchedules(startDate, doctor);
        List<DoctorSchedule> schedules = doctorScheduleRepository.findByDoctorId(doctorId);
        model.addAttribute("schedules", schedules);
        return "/doctors/schedule/schedule_list"; // Return the name of the view template
    }

    @PostMapping("/toggle/{id}")
    public String toggleScheduleAvailability(@PathVariable UUID id) {
        DoctorSchedule schedule = doctorScheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id " + id));

        schedule.setIsAvailable(false);
        schedule.setUpdatedAt(LocalDateTime.now());
        doctorScheduleRepository.save(schedule);

        return "redirect:/doctor/schedules";
    }

    @PostMapping("/toggle-available/{id}")
    public String toggleScheduleToAvailable(@PathVariable UUID id) {
        DoctorSchedule schedule = doctorScheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id " + id));

        schedule.setIsAvailable(true);
        schedule.setUpdatedAt(LocalDateTime.now());
        doctorScheduleRepository.save(schedule);

        return "redirect:/doctor/schedules";
    }

}
