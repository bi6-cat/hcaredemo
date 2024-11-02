package com.zett.hcaredemo.controller;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.zett.hcaredemo.dto.doctor.DoctorCreateDTO;
import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import com.zett.hcaredemo.service.DoctorService;

import jakarta.validation.Valid;

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
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @RequestParam(required = false, defaultValue = "fullName") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String keyword,
            Model model) {
        
        Pageable pageable = null;
        if(order.equals("asc")){
            pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        }
        var doctors = doctorService.findAll(keyword, pageable);
        model.addAttribute("doctors", doctors);

        model.addAttribute("keyword", keyword);

        model.addAttribute("sortBy", sortBy);

        model.addAttribute("order", order);
        //Passing totalPage to view
        model.addAttribute("totalPages", doctors.getTotalPages());

        model.addAttribute("totalElements", doctors.getTotalElements());

        model.addAttribute("page", page);

        model.addAttribute("pageSize", size);

        model.addAttribute("pageSizes", new Integer[]{5, 10, 20, 50, 100});
        return "doctors/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        var doctorCreateDTO = new DoctorCreateDTO();
        model.addAttribute("doctorCreateDTO", doctorCreateDTO);
        return "doctors/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Valid DoctorCreateDTO doctorCreateDTO,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "doctors/create";
        }

        doctorService.create(doctorCreateDTO);
        return "redirect:/doctors";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable UUID id, ModelMap model){
        var doctorDTO = doctorService.findById(id);
        model.addAttribute("doctorDTO", doctorDTO);
        return "doctors/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable UUID id, @ModelAttribute DoctorDTO doctorDTO){
        doctorService.update(id, doctorDTO);
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id){
        doctorService.delete(id);
        return "redirect:/doctors";
    }
}