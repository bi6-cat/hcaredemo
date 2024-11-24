package com.zett.hcaredemo.controller;

import com.zett.hcaredemo.dto.department.DepartmentCreateDTO;
import com.zett.hcaredemo.dto.department.DepartmentDTO;
import com.zett.hcaredemo.dto.department.DepartmentUpdateDTO;
import com.zett.hcaredemo.dto.doctor.DoctorCreateDTO;
import com.zett.hcaredemo.dto.doctor.DoctorUpdateDTO;
import com.zett.hcaredemo.dto.doctorschedule.DoctorScheduleDTO;
import com.zett.hcaredemo.dto.hospital.HospitalCreateDTO;
import com.zett.hcaredemo.dto.hospital.HospitalDTO;
import com.zett.hcaredemo.dto.medicine.MedicineCreateDTO;
import com.zett.hcaredemo.dto.medicine.MedicineDTO;
import com.zett.hcaredemo.entity.Activity;
import com.zett.hcaredemo.service.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/admin")
public class Admin {
    private final MedicineService medicineService;
    private final HospitalService hospitalService;
    private final DepartmentService departmentService;
    private final DoctorService doctorService;
    private final DoctorScheduleService doctorScheduleService;
    private final ActivityService activityService;


    public Admin(MedicineService medicineService, HospitalService hospitalService, DepartmentService departmentService, DoctorService doctorService, DoctorScheduleService doctorScheduleService, ActivityService activityService) {
        this.medicineService = medicineService;
        this.hospitalService = hospitalService;
        this.departmentService = departmentService;
        this.doctorService = doctorService;
        this.doctorScheduleService = doctorScheduleService;
        this.activityService = activityService;
    }

//    @GetMapping("")
//    public String adminHomePage() {
//        return "admin/index"; // Trả về file admin/index.html
//    }

    @GetMapping("")
    public String dashboard(Model model) {
        List<Activity> activities = activityService.getRecentActivities();
        model.addAttribute("activities", activities);
        long totalMedicines = medicineService.countMedicine();
        long totalHospitals = hospitalService.countHospital();
        long totalDepartments = departmentService.countDepartment();
        long totalDoctors = doctorService.countDoctor();
        model.addAttribute("totalMedicines", totalMedicines);
        model.addAttribute("totalHospitals", totalHospitals);
        model.addAttribute("totalDepartments", totalDepartments);
        model.addAttribute("totalDoctors", totalDoctors);
        return "admin/dashboard";
    }

    @GetMapping("/departments")
    public String indexDepartment(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false, defaultValue = "") String keyword,
            Model model) {

        Pageable pageable = null;
        if (order.equals("asc")) {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        }
        var departments = departmentService.searchDepartments(keyword, pageable);
        model.addAttribute("departments", departments);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("totalPages", departments.getTotalPages());
        model.addAttribute("totalElements", departments.getTotalElements());
        model.addAttribute("page", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("pageSizes", new Integer[]{5, 10, 20, 50, 100});
        return "admin/departments/index";
    }

    // Show create form
    @GetMapping("/departments/create")
    public String createDepartment(Model model) {
        log.info("Accessing Department Creation Form");
        model.addAttribute("department", new DepartmentCreateDTO());
        log.info("Successfully loaded Department Creation Form");
        return "admin/departments/create";
    }

    // Handle department creation
    @PostMapping("/departments/create")
    public String saveDepartment(@ModelAttribute @Valid DepartmentCreateDTO department, BindingResult bindingResult, Model model) {
        log.info("Starting Department Creation Process");

        if (bindingResult.hasErrors()) {
            log.warn("Validation errors while creating department: {}", bindingResult.getAllErrors());
            return "admin/departments/create";
        }

        try {
            DepartmentDTO departmentDTO = departmentService.createByAdmin(department);
            activityService.logActivity("Thêm khoa mới: " + department.getName(), "Admin", "Thành công");
            log.info("Successfully created Department: {}", departmentDTO.getName());
        } catch (Exception e) {
            log.error("Error occurred while creating department: {}", e.getMessage(), e);
            model.addAttribute("message", "Failed to create department");
            activityService.logActivity("Thêm khoa mới: " + department.getName(), "Admin", "Thất bại");
            return "admin/departments/create";
        }

        return "redirect:/admin/departments";
    }

    // Show edit form
    @GetMapping("/departments/edit/{id}")
    public String editDepartment(@PathVariable UUID id, Model model) {
        log.info("Accessing Edit Form for Department with ID: {}", id);

        try {
            DepartmentDTO departmentDTO = departmentService.findById(id);
            model.addAttribute("departmentUpdateDTO", new DepartmentUpdateDTO());
            model.addAttribute("departmentDTO", departmentDTO);
            log.info("Successfully loaded Edit Form for Department: {}", departmentDTO.getName());
        } catch (Exception e) {
            log.error("Error occurred while loading department for editing: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to load department for editing", e);
        }

        return "admin/departments/edit";
    }

    // Handle department update
    @PostMapping("/departments/edit/{id}")
    public String updateDepartment(@PathVariable UUID id, @ModelAttribute @Valid DepartmentUpdateDTO departmentUpdateDTO,
                                   BindingResult bindingResult, Model model) {
        log.info("Starting Update Process for Department with ID: {}", id);
        if (bindingResult.hasErrors()) {
            log.warn("Validation errors while updating department with ID {}: {}", id, bindingResult.getAllErrors());
            model.addAttribute("departmentDTO", departmentService.findById(id));
            return "admin/departments/edit";
        }
        try {
            departmentService.update(id, departmentUpdateDTO);
            log.info("Successfully updated Department with ID: {}", id);
            activityService.logActivity("Cập nhật thông tin khoa: " + departmentUpdateDTO.getName(), "Admin", "Thành công");
        } catch (Exception e) {
            log.error("Error occurred while updating department with ID: {}", id, e);
            model.addAttribute("message", "Failed to update department");
            activityService.logActivity("Cập nhật thông tin khoa: " + departmentUpdateDTO.getName(), "Admin", "Thất bại");
            return "admin/departments/edit";
        }
        return "redirect:/admin/departments";
    }

    // Handle department deletion
    @GetMapping("/departments/delete/{id}")
    public String deleteDepartment(@PathVariable UUID id) {
        log.info("Starting Delete Process for Department with ID: {}", id);

        try {
            departmentService.delete(id);
            activityService.logActivity("Xóa khoa: " + id, "Admin", "Thành công");
            log.info("Successfully deleted Department with ID: {}", id);
        } catch (Exception e) {
            log.error("Error occurred while deleting department with ID: {}", id, e);
            activityService.logActivity("Xóa khoa: " + id, "Admin", "Thất bại");
            throw new RuntimeException("Failed to delete department", e);
        }

        return "redirect:/admin/departments";
    }

    @GetMapping("/doctors")
    public String indexDoctor(
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
        return "admin/doctors/index";
    }

    @GetMapping("/doctors/create")
    public String createDoctor(Model model) {
        log.info("Accessing Doctor Create Page");

        var doctorCreateDTO = new DoctorCreateDTO();
        model.addAttribute("doctorCreateDTO", doctorCreateDTO);

        try {
            List<DepartmentDTO> departments = departmentService.findAllDepartments();
            if (departments == null || departments.isEmpty()) {
                log.warn("No departments found while accessing doctor create page");
                throw new RuntimeException("No departments found");
            }
            List<DoctorScheduleDTO> schedules = doctorScheduleService.findAll();
            model.addAttribute("schedules", schedules);
            model.addAttribute("departments", departments);
        } catch (Exception e) {
            log.error("Failed to load data for doctor creation: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to load necessary data for doctor creation", e);
        }

        log.info("Successfully loaded data for Doctor Create Page");
        return "admin/doctors/create";
    }

    @PostMapping("/doctors/create")
    public String createDoctor(@ModelAttribute @Valid DoctorCreateDTO doctorCreateDTO,
                               @RequestParam(name = "profilePicture", required = false) MultipartFile profilePicture,
                               BindingResult bindingResult, Model model) {
        log.info("Starting Doctor Creation Process");

        if (bindingResult.hasErrors()) {
            log.warn("Validation failed during doctor creation: {}", bindingResult.getAllErrors());
            return "admin/doctors/create";
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
                    log.info("Created directory for doctor profile picture at: {}", directoryPath);
                }
                Path filePath = Paths.get("src/main/resources/static/images/doctors/" + uploadDate + "/" + uploadTime + profilePicture.getOriginalFilename());
                Files.write(filePath, bytes);
                doctorCreateDTO.setProfilePictureUrl("/images/doctors/" + uploadDate + "/" + uploadTime + profilePicture.getOriginalFilename());
                log.info("Uploaded profile picture for doctor: {}", filePath);
            } catch (Exception e) {
                log.error("Failed to upload profile picture during doctor creation: {}", e.getMessage(), e);
                model.addAttribute("message", "Failed to upload image");
                return "admin/doctors/create";
            }
        }

        doctorService.create(doctorCreateDTO);
        activityService.logActivity("Thêm bác sĩ mới: " + doctorCreateDTO.getFullName(), "Admin", "Thành công");
        log.info("Successfully created new doctor with name: {}", doctorCreateDTO.getFullName());
        return "redirect:/admin/doctors";
    }

    @GetMapping("/doctors/{id}/edit")
    public String editDoctor(@PathVariable UUID id, ModelMap model) {
        log.info("Accessing Edit Page for Doctor with ID: {}", id);

        try {
            var doctorDTO = doctorService.findById(id);
            model.addAttribute("doctorDTO", doctorDTO);
            log.info("Successfully loaded doctor data for editing: {}", doctorDTO.getFullName());
        } catch (Exception e) {
            log.error("Failed to load doctor data for editing: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to load doctor data for editing", e);
        }

        return "admin/doctors/edit";
    }

    @PostMapping("/doctors/{id}/edit")
    public String editDoctor(@PathVariable UUID id, @ModelAttribute DoctorUpdateDTO doctorDTO,
                             @RequestParam(name = "profilePicture", required = false) MultipartFile profilePicture, Model model) {
        log.info("Starting Edit Process for Doctor with ID: {}", id);

        try {
            var oldDoctor = doctorService.findById(id);

            // Case 1: User does not select a new image
            if (profilePicture == null || Objects.requireNonNull(profilePicture.getOriginalFilename()).isEmpty()) {
                doctorDTO.setProfilePictureUrl(oldDoctor.getProfilePictureUrl());
                log.info("No new profile picture provided, using existing image for doctor: {}", oldDoctor.getFullName());
            } else {
                // Case 2: User selects a new image
                byte[] bytes = profilePicture.getBytes();

                String uploadDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String uploadTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hhmmss"));

                Path directoryPath = Paths.get("src/main/resources/static/images/doctors/" + uploadDate + "/");

                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                    log.info("Created directory for doctor profile picture at: {}", directoryPath);
                }

                Path filePath = Paths.get("src/main/resources/static/images/doctors/" + uploadDate + "/" + uploadTime + profilePicture.getOriginalFilename());
                Files.write(filePath, bytes);

                doctorDTO.setProfilePictureUrl("/images/doctors/" + uploadDate + "/" + uploadTime + profilePicture.getOriginalFilename());
                log.info("Updated profile picture for doctor with ID: {}", id);
            }

            doctorService.update(id, doctorDTO);
            activityService.logActivity("Cập nhật thông tin bác sĩ: " + doctorDTO.getFullName(), "Admin", "Thành công");
            log.info("Successfully updated doctor with ID: {}", id);
        } catch (Exception e) {
            log.error("Failed to edit doctor with ID: {}", id, e);
            activityService.logActivity("Cập nhật thông tin bác sĩ: " + doctorDTO.getFullName(), "Admin", "Thất bại");
            model.addAttribute("message", "Failed to update doctor information");
            return "admin/doctors/edit";
        }

        return "redirect:/admin/doctors";
    }

    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable UUID id) {
        log.info("Starting Delete Process for Doctor with ID: {}", id);

        try {
            doctorService.delete(id);
            log.info("Successfully deleted doctor with ID: {}", id);
        } catch (Exception e) {
            log.error("Failed to delete doctor with ID: {}", id, e);
            throw new RuntimeException("Failed to delete doctor", e);
        }

        return "redirect:/admin/doctors";
    }

    @GetMapping("/hospitals")
    public String indexHospital(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false, defaultValue = "") String keyword,
            Model model) {

        Pageable pageable = null;
        if (order.equals("asc")) {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        }
        var hospitals = hospitalService.searchHospitals(keyword, pageable);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("totalPages", hospitals.getTotalPages());
        model.addAttribute("totalElements", hospitals.getTotalElements());
        model.addAttribute("page", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("pageSizes", new Integer[]{5, 10, 20, 50, 100});
        return "admin/hospitals/index";
    }

    @GetMapping("/hospitals/{id}")
    public String showHospital(@PathVariable UUID id, Model model) {
        HospitalDTO hospitalDTO = hospitalService.findById(id);
        model.addAttribute("hospital", hospitalDTO);
        return "admin/hospitals/details";
    }

    @GetMapping("/hospitals/create")
    public String createHospital(Model model) {
        log.info("Accessing Hospital Creation Page");
        var hospitalCreateDTO = new HospitalCreateDTO();
        var departments = hospitalService.getAllDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("hospitalCreateDTO", hospitalCreateDTO);
        return "admin/hospitals/create";
    }

    @PostMapping("/hospitals/create")
    public String createHospital(@ModelAttribute @Valid HospitalCreateDTO hospitalCreateDTO,
                                 BindingResult bindingResult) {
        log.info("Starting Hospital Creation Process with data: {}", hospitalCreateDTO);
        if (bindingResult.hasErrors()) {
            return "admin/hospitals/create";
        }
        System.out.println(hospitalCreateDTO.getDepartmentIds());
        hospitalService.create(hospitalCreateDTO);
        log.info("Successfully created Hospital: {}", hospitalCreateDTO.getName());
        activityService.logActivity("Thêm bệnh viện mới: " + hospitalCreateDTO.getName(), "Admin", "Thành công");
        return "redirect:/admin";
    }

    @GetMapping("/hospitals/edit/{id}")
    public String editHospital(@PathVariable UUID id, ModelMap model) {
        log.info("Accessing Edit Form for Hospital with ID: {}", id);
        var hospitalDTO = hospitalService.findById(id);
        model.addAttribute("hospitalDTO", hospitalDTO);
        return "admin/hospitals/edit";
    }

    @PostMapping("/hospitals/edit/{id}")
    public String editHospital(@PathVariable UUID id, @ModelAttribute HospitalDTO hospitalDTO) {
        hospitalService.update(id, hospitalDTO);
        log.info("Successfully updated Hospital: {}", hospitalDTO.getName());
        activityService.logActivity("Cập nhật thông tin bệnh viện: " + hospitalDTO.getName(), "Admin", "Thành công");
        return "redirect:/admin/hospitals";
    }

    @GetMapping("/hospitals/delete/{id}")
    public String deleteHospital(@PathVariable UUID id) {
        log.info("Deleting Hospital with ID: {}", id);
        hospitalService.delete(id);
        log.info("Successfully deleted Hospital with ID: {}", id);
        return "redirect:/admin/hospitals";
    }

    @GetMapping("/medicines")
    public String indexMedicine(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false, defaultValue = "") String keyword,
            Model model) {

        Pageable pageable = null;
        if (order.equals("asc")) {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        }
        var medicines = medicineService.searchMedicine(keyword, pageable);
        model.addAttribute("medicines", medicines);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("totalPages", medicines.getTotalPages());
        model.addAttribute("totalElements", medicines.getTotalElements());
        model.addAttribute("page", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("pageSizes", new Integer[]{5, 10, 20, 50, 100});
        return "admin/medicines/index";
    }

    // Show create form
    @GetMapping("/medicines/create")
    public String createMedicine(Model model) {
        log.info("Accessing Medicine Creation Page");
        MedicineCreateDTO medicineCreateDTO = new MedicineCreateDTO();
        model.addAttribute("medicineDTO", medicineCreateDTO);
        log.info("Successfully loaded Medicine Creation Page");
        return "admin/medicines/create";
    }

    // Handle medicine creation
    @PostMapping("/medicines/create")
    public String createMedicine(@ModelAttribute MedicineCreateDTO medicineCreateDTO) {
        log.info("Starting Medicine Creation Process with data: {}", medicineCreateDTO);

        try {
            medicineService.create(medicineCreateDTO);
            log.info("Successfully created Medicine: {}", medicineCreateDTO.getName());
            activityService.logActivity("Thêm thuốc mới: " + medicineCreateDTO.getName(), "Admin", "Thành công");
        } catch (Exception e) {
            log.error("Error occurred while creating Medicine: {}", e.getMessage(), e);
            activityService.logActivity("Thêm thuốc mới: " + medicineCreateDTO.getName(), "Admin", "Thất bại");
            throw new RuntimeException("Failed to create medicine", e);
        }

        return "redirect:/admin/medicines";
    }

    // Show update form
    @GetMapping("/medicines/update/{id}")
    public String updateMedicine(Model model, @PathVariable UUID id) {
        log.info("Accessing Update Form for Medicine with ID: {}", id);

        try {
            MedicineDTO medicine = medicineService.getMedicineById(id);
            model.addAttribute("medicine", medicine);
            model.addAttribute("medicineCreateDTO", new MedicineCreateDTO());
            log.info("Successfully loaded Update Form for Medicine: {}", medicine.getName());
            activityService.logActivity("Cập nhật thông tin thuốc: " + medicine.getName(), "Admin", "Thành công");
        } catch (Exception e) {
            log.error("Error occurred while loading Medicine data for update: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to load medicine for update", e);
        }

        return "admin/medicines/edit";
    }

    // Handle medicine update
    @PostMapping("/medicines/update/{id}")
    public String updateMedicine(@ModelAttribute MedicineCreateDTO medicineCreateDTO, @PathVariable UUID id) {
        log.info("Starting Update Process for Medicine with ID: {}", id);

        try {
            medicineService.update(id, medicineCreateDTO);
            log.info("Successfully updated Medicine with ID: {}", id);
            activityService.logActivity("Cập nhật thông tin thuốc: " + medicineCreateDTO.getName(), "Admin", "Thành công");
        } catch (Exception e) {
            log.error("Error occurred while updating Medicine with ID: {}", id, e);
            activityService.logActivity("Cập nhật thông tin thuốc: " + medicineCreateDTO.getName(), "Admin", "Thất bại");

            throw new RuntimeException("Failed to update medicine", e);
        }

        return "redirect:/admin/medicines";
    }

    // Handle medicine deletion
    @PostMapping("/medicines/delete/{id}")
    public String deleteMedicine(@PathVariable UUID id) {
        log.info("Starting Delete Process for Medicine with ID: {}", id);
        try {
            medicineService.delete(id);
            log.info("Successfully deleted Medicine with ID: {}", id);
            activityService.logActivity("Xóa thuốc: " + id, "Admin", "Thành công");
        } catch (Exception e) {
            log.error("Error occurred while deleting Medicine with ID: {}", id, e);
            activityService.logActivity("Xóa thuốc: " + id, "Admin", "Thất bại");
            throw new RuntimeException("Failed to delete medicine", e);
        }
        return "redirect:/admin/medicines";
    }
}
