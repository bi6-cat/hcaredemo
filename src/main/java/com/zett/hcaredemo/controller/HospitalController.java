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

import com.zett.hcaredemo.dto.hospital.HospitalCreateDTO;
import com.zett.hcaredemo.dto.hospital.HospitalDTO;
import com.zett.hcaredemo.service.HospitalService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String index(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String keyword,
            Model model) {
        
        Pageable pageable = null;
        if(order.equals("asc")){
            pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        }
        var hospitals = hospitalService.searchHospitals(keyword, pageable);
        model.addAttribute("hospitals", hospitals);

        model.addAttribute("keyword", keyword);

        model.addAttribute("sortBy", sortBy);

        model.addAttribute("order", order);
        //Passing totalPage to view
        model.addAttribute("totalPages", hospitals.getTotalPages());

        model.addAttribute("totalElements", hospitals.getTotalElements());

        model.addAttribute("page", page);

        model.addAttribute("pageSize", size);

        model.addAttribute("pageSizes", new Integer[]{5, 10, 20, 50, 100});
        return "hospitals/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        var hospitalCreateDTO = new HospitalCreateDTO();
        model.addAttribute("hospitalCreateDTO", hospitalCreateDTO);
        return "hospitals/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Valid HospitalCreateDTO hospitalCreateDTO,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "hospitals/create";
        }

        hospitalService.create(hospitalCreateDTO);
        return "redirect:/hospitals";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable UUID id, ModelMap model){
        var hospitalDTO = hospitalService.findById(id);
        model.addAttribute("hospitalDTO", hospitalDTO);
        return "hospitals/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable UUID id, @ModelAttribute HospitalDTO hospitalDTO){
        hospitalService.update(id, hospitalDTO);
        return "redirect:/hospitals";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id){
        hospitalService.delete(id);
        return "redirect:/hospitals";
    }
}