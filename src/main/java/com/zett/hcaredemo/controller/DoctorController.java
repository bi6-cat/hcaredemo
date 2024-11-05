package com.zett.hcaredemo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.zett.hcaredemo.service.DepartmentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.zett.hcaredemo.dto.doctor.DoctorCreateDTO;
import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import com.zett.hcaredemo.service.DoctorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    public DoctorController(DoctorService doctorService, DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
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
        model.addAttribute("doctor", doctor);
        return "doctors/details";
    }

    @GetMapping("/create")
    public String create(Model model) {
        var doctorCreateDTO = new DoctorCreateDTO();
        model.addAttribute("doctorCreateDTO", doctorCreateDTO);
        model.addAttribute("departments", departmentService.findAllDepartments());
        return "doctors/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Valid DoctorCreateDTO doctorCreateDTO,
            @RequestParam(name = "profilePicture", required = false) MultipartFile profilePicture,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "doctors/create";
        }

        // Handle file upload
        if (profilePicture != null && !profilePicture.isEmpty()) {
            try {
                byte[] bytes = profilePicture.getBytes();

                String uploadDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String uploadTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hhmmss"));

                Path directoryPath = Paths.get("src/main/resources/static/images/doctors/" + uploadDate + "/");

                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                }
                
                Path filePath = Paths.get("src/main/resources/static/images/doctors/" + uploadDate + "/" + uploadTime + profilePicture.getOriginalFilename());

                Files.write(filePath, bytes);

                doctorCreateDTO.setProfilePictureUrl("/images/doctors/" + uploadDate + "/" + uploadTime + profilePicture.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("message", "Failed to upload image");
                return "doctors/create";
            }
        }

        doctorService.create(doctorCreateDTO);

        return "redirect:/doctors";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable UUID id, ModelMap model){
        var doctorDTO = doctorService.findById(id);
        model.addAttribute("doctorDTO", doctorDTO);
        return "doctors/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable UUID id, @ModelAttribute DoctorDTO doctorDTO,
            @RequestParam(name = "profilePicture", required = false) MultipartFile profilePicture, Model model) {

        var oldDoctor = doctorService.findById(id);

        // Case 1: User does not select a new image
        if (profilePicture.getOriginalFilename().isEmpty()) {
            doctorDTO.setProfilePictureUrl(oldDoctor.getProfilePictureUrl());
        } else {
            // Case 2: User selects a new image
            if (profilePicture.getOriginalFilename() != null && !profilePicture.getOriginalFilename().isEmpty()) {
                try {
                    byte[] bytes = profilePicture.getBytes();

                    String uploadDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String uploadTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hhmmss"));

                    Path directoryPath = Paths.get("src/main/resources/static/images/doctors/" + uploadDate + "/");

                    if (!Files.exists(directoryPath)) {
                        Files.createDirectories(directoryPath);
                    }
                    
                    Path filePath = Paths.get("src/main/resources/static/images/doctors/" + uploadDate + "/" + uploadTime + profilePicture.getOriginalFilename());

                    Files.write(filePath, bytes);

                    doctorDTO.setProfilePictureUrl("/images/doctors/" + uploadDate + "/" + uploadTime + profilePicture.getOriginalFilename());
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("message", "Failed to upload image");
                    return "doctors/edit";
                }
            }
        }

        doctorService.update(id, doctorDTO);
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id){
        doctorService.delete(id);
        return "redirect:/doctors";
    }
}