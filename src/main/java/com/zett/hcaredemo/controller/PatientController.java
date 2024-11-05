package com.zett.hcaredemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.zett.hcaredemo.dto.patient.PatientCreateDTO;
import com.zett.hcaredemo.dto.patient.PatientDTO;
import com.zett.hcaredemo.dto.patient.PatientUpdateDTO;
import com.zett.hcaredemo.service.PatientService;

import jakarta.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public String index(@RequestParam(required = false, defaultValue = "") String keyword, Pageable pageable, Model model) {
        Page<PatientDTO> patients = patientService.findAll(keyword, pageable);
        model.addAttribute("patients", patients);
        model.addAttribute("keyword", keyword);
        return "patients/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("patientCreateDTO", new PatientCreateDTO());
        return "patients/create";
    }

    @PostMapping("/create")
    public String savePatient(@ModelAttribute @Valid PatientCreateDTO patientCreateDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patientCreateDTO", patientCreateDTO);
            return "patients/create";
        }
        patientService.create(patientCreateDTO);
        return "redirect:/patients/create";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable UUID id, Model model) {
        PatientDTO patientDTO = patientService.findById(id);
        model.addAttribute("patientUpdateDTO", new PatientUpdateDTO());
        model.addAttribute("patientDTO", patientDTO);
        return "patients/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable UUID id, @ModelAttribute @Valid PatientUpdateDTO patientUpdateDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patientDTO", patientService.findById(id));
            return "patients/edit";
        }
        patientService.update(id, patientUpdateDTO);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        patientService.delete(id);
        return "redirect:/patients";
    }
}