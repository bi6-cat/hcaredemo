package com.zett.hcaredemo.controller;

import com.zett.hcaredemo.dto.department.DepartmentCreateDTO;
import com.zett.hcaredemo.dto.department.DepartmentDTO;
import com.zett.hcaredemo.dto.department.DepartmentUpdateDTO;
import com.zett.hcaredemo.dto.hospital.HospitalDTO;
import com.zett.hcaredemo.service.DepartmentService;
import com.zett.hcaredemo.service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class DepartmentController {

    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @Autowired
    public DepartmentController(DepartmentService departmentService, HospitalService hospitalService) {
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }

    @GetMapping("hospitals/{hospitalId}/departments")
    public String getDepartmentsByHospitalId(@PathVariable UUID hospitalId,
                                             @RequestParam(required = false, defaultValue = "") String keyword,
                                             Pageable pageable, Model model) {
        Page<DepartmentDTO> departments = departmentService.findAllByHospitalId(hospitalId, keyword, pageable);
        model.addAttribute("departments", departments);
        model.addAttribute("keyword", keyword);
        model.addAttribute("hospitalId", hospitalId);
        HospitalDTO hospital = hospitalService.findById(hospitalId);
        model.addAttribute("hospital", hospital);
        return "hospitals/departments";
    }

    // Display all departments with pagination and search
    @GetMapping("/departments")
    public String index(@RequestParam(required = false, defaultValue = "") String keyword,
                        Pageable pageable, Model model) {
        Page<DepartmentDTO> departments = departmentService.findAll(keyword, pageable);
        model.addAttribute("departments", departments);
        model.addAttribute("keyword", keyword);
        return "departments/index";
    }

    // Show create form
    @GetMapping("/hospitals/{hospitalId}/departments/create")
    public String createForm(Model model, @PathVariable UUID hospitalId) {
        HospitalDTO hospital = hospitalService.findById(hospitalId);
        model.addAttribute("department", new DepartmentCreateDTO());
        model.addAttribute("hospital", hospital);
        return "departments/create";
    }

    // Handle department creation
    @PostMapping("/hospitals/{hospitalId}/departments/create")
    public String saveDepartment(@PathVariable UUID hospitalId, @ModelAttribute DepartmentCreateDTO department) {
        departmentService.create(department, hospitalId);
        return "redirect:/hospitals/" + hospitalId + "/departments";
    }

    // Show edit form
    @GetMapping("departments/edit/{id}")
    public String editForm(@PathVariable UUID id, Model model) {
        DepartmentDTO departmentDTO = departmentService.findById(id);
        model.addAttribute("departmentUpdateDTO", new DepartmentUpdateDTO());
        model.addAttribute("departmentDTO", departmentDTO);
        return "departments/edit";
    }

    // Handle department update
    @PostMapping("departments/edit/{id}")
    public String update(@PathVariable UUID id, @ModelAttribute @Valid DepartmentUpdateDTO departmentUpdateDTO,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departmentDTO", departmentService.findById(id));
            return "departments/edit";
        }
        departmentService.update(id, departmentUpdateDTO);
        return "redirect:/departments";
    }

    // Handle department deletion
    @GetMapping("departments/delete/{id}")
    public String delete(@PathVariable UUID id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }

    @GetMapping("hospitals/{hospitalId}/departments/delete/{id}")
    public String deleteByHospitalId(@PathVariable UUID hospitalId, @PathVariable UUID id) {
        departmentService.delete(id);
        return "redirect:/hospitals/" + hospitalId + "/departments";
    }
}
