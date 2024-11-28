package com.zett.hcaredemo.controller;

import com.zett.hcaredemo.dto.department.DepartmentDTO;
import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import com.zett.hcaredemo.dto.doctorschedule.DoctorScheduleDTO;
import com.zett.hcaredemo.service.DoctorService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public String index(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "12") Integer size,
            @RequestParam(required = false, defaultValue = "fullName") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false, defaultValue = "") String keyword,
            Model model) {

        Pageable pageable = null;
        if (order.equals("asc")) {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        }
        var doctors = doctorService.findAll(keyword, pageable);
        model.addAttribute("doctors", doctors);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("totalPages", doctors.getTotalPages());
        model.addAttribute("totalElements", doctors.getTotalElements());
        model.addAttribute("page", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("pageSizes", new Integer[]{6, 12, 24, 60, 100});
        return "doctors/index";
    }

    @GetMapping("details/{id}")
    public String viewDetails(@PathVariable UUID id, Model model) {
        DoctorDTO doctor = doctorService.findById(id);
        DepartmentDTO department = doctor.getDepartment();
        Set<DoctorScheduleDTO> doctorSchedules = doctor.getDoctorSchedules();
        model.addAttribute("doctorSchedules", doctorSchedules);
        model.addAttribute("doctor", doctor);
        model.addAttribute("department", department);
        return "doctors/details";
    }

}