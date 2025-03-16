package com.zett.hcaredemo.controller;

import com.zett.hcaredemo.entity.*;
import com.zett.hcaredemo.exception.ResourceNotFoundException;
import com.zett.hcaredemo.repository.*;
import com.zett.hcaredemo.service.DoctorScheduleService;
import com.zett.hcaredemo.service.HealthCheckAppointmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("patient/appointments")
public class PatientAppointmentController {

    private final HealthCheckAppointmentRepository appointmentRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    private final DepartmentServiceRepository departmentServiceRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorScheduleRepository doctorScheduleRepository;
    private final HealthCheckAppointmentService healthCheckAppointmentService;
    private final PatientRepository patientRepository;
    private final DoctorScheduleService doctorScheduleService;

    public PatientAppointmentController(HealthCheckAppointmentRepository appointmentRepository, HospitalRepository hospitalRepository, DepartmentRepository departmentRepository, DepartmentServiceRepository departmentServiceRepository, DoctorRepository doctorRepository, DoctorScheduleRepository doctorScheduleRepository, HealthCheckAppointmentService healthCheckAppointmentService, PatientRepository patientRepository, DoctorScheduleService doctorScheduleService) {
        this.appointmentRepository = appointmentRepository;
        this.hospitalRepository = hospitalRepository;
        this.departmentRepository = departmentRepository;
        this.departmentServiceRepository = departmentServiceRepository;
        this.doctorRepository = doctorRepository;
        this.doctorScheduleRepository = doctorScheduleRepository;
        this.healthCheckAppointmentService = healthCheckAppointmentService;
        this.patientRepository = patientRepository;
        this.doctorScheduleService = doctorScheduleService;
    }

    @GetMapping
    public String listAppointments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "appointmentDate") String sortBy,
            @RequestParam(defaultValue = "asc") String order,
            Model model) {

        Pageable pageable = PageRequest.of(page, size,
                order.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());

        Page<HealthCheckAppointment> appointments = healthCheckAppointmentService.findByUser(pageable);

        model.addAttribute("appointments", appointments);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", appointments.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("size", size);

        return "patient/appointment/index";
    }

    // Step 1: Select Hospital
    @GetMapping("/create/step1")
    public String selectHospital(Model model) {
        List<Hospital> hospitals = hospitalRepository.findAll();
        model.addAttribute("hospitals", hospitals);
        return "patient/appointment/select_hospital";
    }

    // Step 2: Select Department
    @GetMapping("/create/step2/{hospitalId}")
    public String selectDepartment(@PathVariable UUID hospitalId, Model model, HttpSession session) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found"));

        List<Department> departments = departmentRepository.findByHospitalId(hospitalId);

        session.setAttribute("selectedHospital", hospital);
        model.addAttribute("departments", departments);
        model.addAttribute("hospital", hospital);

        return "patient/appointment/select_department";
    }

    // Step 3: Select Service
    @GetMapping("/create/step3/{departmentId}")
    public String selectService(@PathVariable UUID departmentId, Model model, HttpSession session) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        List<DepartmentService> services = departmentServiceRepository.findByDepartmentId(departmentId);

        session.setAttribute("selectedDepartment", department);
        model.addAttribute("services", services);
        model.addAttribute("department", department);

        return "patient/appointment/select_service";
    }

    // Step 4: Select Doctor
    @GetMapping("/create/step4/{serviceId}")
    public String selectDoctor(@PathVariable UUID serviceId, Model model, HttpSession session) {
        DepartmentService service = departmentServiceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        Department department = (Department) session.getAttribute("selectedDepartment");
        List<Doctor> doctors = doctorRepository.findByDepartmentId(department.getId());

        session.setAttribute("selectedService", service);
        model.addAttribute("doctors", doctors);
        model.addAttribute("service", service);

        return "patient/appointment/select_doctor";
    }

    // Step 5: Select Schedule
    @GetMapping("/create/step5/{doctorId}")
    public String selectSchedule(@PathVariable UUID doctorId, Model model, HttpSession session) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        doctorScheduleService.generateSchedules(LocalDate.now(), doctor);
        doctorScheduleService.updateExpiredSchedules();

        List<DoctorSchedule> availableSchedules = doctorScheduleRepository
                .findByDoctorIdAndIsAvailableTrueOrderByScheduleDateAscStartTimeAsc(doctorId);

        List<LocalDate> scheduleDates = doctorScheduleRepository
                .findDistinctScheduleDatesByDoctorId(doctorId);


        session.setAttribute("selectedDoctor", doctor);
        model.addAttribute("scheduleDates", scheduleDates);
        model.addAttribute("schedules", availableSchedules);
        model.addAttribute("doctor", doctor);

        return "patient/appointment/select_schedule";
    }

    @PostMapping("/create/save")
    // Final step: Create Appointment
    public String createAppointment(@RequestParam UUID scheduleId, HttpSession session, Authentication authentication) {
        DoctorSchedule schedule = doctorScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));

        if (!schedule.getIsAvailable()) {
            throw new IllegalStateException("Selected schedule is no longer available");
        }

        // Lấy thông tin bệnh nhân từ tên người dùng trong Authentication
        String username = authentication.getName();
        Patient patient = patientRepository.findByUser_Username(username);

        // Lấy thông tin khác từ session
        Hospital hospital = (Hospital) session.getAttribute("selectedHospital");
        Department department = (Department) session.getAttribute("selectedDepartment");
        DepartmentService service = (DepartmentService) session.getAttribute("selectedService");
        Doctor doctor = (Doctor) session.getAttribute("selectedDoctor");

        // Tạo cuộc hẹn
        HealthCheckAppointment appointment = new HealthCheckAppointment();
        appointment.setHospital(hospital);
        appointment.setDepartment(department);
        appointment.setDepartmentService(service);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient); // Gán bệnh nhân vào cuộc hẹn
        appointment.setAppointmentDate(schedule.getScheduleDate().atTime(schedule.getStartTime()));
        appointment.setStatus("NOT PAID");
        appointment.setCode(healthCheckAppointmentService.generateUniqueCode());

        // Lưu cuộc hẹn và cập nhật lịch trình
        healthCheckAppointmentService.create(appointment);
        schedule.setIsAvailable(false);
        doctorScheduleRepository.save(schedule);

        // Xóa thông tin trong session
        session.removeAttribute("selectedHospital");
        session.removeAttribute("selectedDepartment");
        session.removeAttribute("selectedService");
        session.removeAttribute("selectedDoctor");

        return "redirect:/patient/appointments";
    }

    @GetMapping("/{id}/edit")
    public String editAppointment(@PathVariable UUID id, Model model) {
        HealthCheckAppointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id " + id));
        model.addAttribute("appointment", appointment);
        return "appointment/edit";
    }

    @PostMapping("/{id}/complete")
    public String completeAppointment(@PathVariable UUID id) {
        HealthCheckAppointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id " + id));
        appointment.setStatus("COMPLETED");
        appointment.setUpdatedAt(LocalDateTime.now());
        appointmentRepository.save(appointment);
        return "redirect:/patient/appointments";
    }

    @GetMapping("/{id}")
    public String viewAppointmentDetail(@PathVariable UUID id, Model model, Principal principal) {
        HealthCheckAppointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch hẹn với ID: " + id));

        model.addAttribute("appointment", appointment);
        model.addAttribute("patient", appointment.getPatient());
        model.addAttribute("doctor", appointment.getDoctor());
        model.addAttribute("service", appointment.getDepartmentService());
        model.addAttribute("department", appointment.getDepartment());
        model.addAttribute("hospital", appointment.getHospital());

        return "patient/appointment/appointment_detail";
    }

}