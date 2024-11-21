package com.zett.hcaredemo.controller;

import com.zett.hcaredemo.dto.patient.PatientCreateDTO;
import com.zett.hcaredemo.dto.patient.PatientDTO;
import com.zett.hcaredemo.dto.patient.PatientUpdateDTO;
import com.zett.hcaredemo.service.PatientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public String index(Model model) {
        PatientDTO patientDTO = patientService.findByUser();
        model.addAttribute("patientDTO", patientDTO);
        return "patients/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("patientCreateDTO", new PatientCreateDTO());
        return "patients/create";
    }

    @PostMapping("/create")
    public String savePatient(@ModelAttribute @Valid PatientCreateDTO patientCreateDTO, BindingResult bindingResult,
                              Model model,
                              @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture) {
        if (bindingResult.hasErrors()) {
            return "patients/create";
        }
        if (profilePicture != null && !profilePicture.isEmpty()) {
            try {
                byte[] bytes = profilePicture.getBytes();
                String uploadDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String uploadTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
                Path path = Paths.get("src/main/resources/static/images/patients/" + uploadDate + "/");
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                }
                Path filePath = Paths.get(path + uploadTime + profilePicture.getOriginalFilename());
                Files.write(filePath, bytes);
                patientCreateDTO.setProfilePictureUrl("/images/patients/" + uploadDate + "/" + uploadTime + profilePicture.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("message", "Failed to upload image");
                return "patients/create";
            }
        }
        patientService.create(patientCreateDTO);
        return "redirect:/patients";
    }

    @GetMapping("/edit")
    public String editForm(Model model) {
        PatientDTO patientDTO = patientService.findByUser();
//        model.addAttribute("patientUpdateDTO", new PatientUpdateDTO());
        model.addAttribute("patientDTO", patientDTO);
        return "patients/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute @Valid PatientUpdateDTO patientUpdateDTO,
                         BindingResult bindingResult, Model model, @RequestParam(name = "profilePicture", required = false) MultipartFile profilePicture) {
        if (bindingResult.hasErrors()) {
            return "shared/demo";
        }
        if (profilePicture != null && !profilePicture.isEmpty()) {
            try {
                byte[] bytes = profilePicture.getBytes();
                String uploadDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String uploadTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
                Path path = Paths.get("src/main/resources/static/images/patients/" + uploadDate + "/");
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                }
                Path filePath = Paths.get(path + uploadTime + profilePicture.getOriginalFilename());
                Files.write(filePath, bytes);
                patientUpdateDTO.setProfilePictureUrl("/images/patients/" + uploadDate + "/" + uploadTime + profilePicture.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("message", "Failed to upload image");
                return "patients/create";
            }
        }
        patientService.update(patientUpdateDTO);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        patientService.delete(id);
        return "redirect:/patients";
    }
}