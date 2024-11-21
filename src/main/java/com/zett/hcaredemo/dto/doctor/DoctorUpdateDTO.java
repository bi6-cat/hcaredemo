package com.zett.hcaredemo.dto.doctor;

import com.zett.hcaredemo.dto.auth.UserDTO;
import com.zett.hcaredemo.dto.department.DepartmentDTO;
import com.zett.hcaredemo.dto.doctorschedule.DoctorScheduleDTO;
import com.zett.hcaredemo.dto.heathcheckappointment.HealthCheckAppointmentDTO;
import com.zett.hcaredemo.dto.medicine.MedicalRecordDTO;
import com.zett.hcaredemo.dto.prescription.PrescriptionDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DoctorUpdateDTO {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Degree is required")
    private String degree;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    private String email;
    private String experience;
    private String profilePictureUrl;
    private DepartmentDTO department;
    private UserDTO user;
    private Set<DoctorScheduleDTO> doctorSchedules;
    private Set<HealthCheckAppointmentDTO> healthCheckAppointments;
    private Set<PrescriptionDTO> prescriptions;
    private Set<MedicalRecordDTO> medicalRecords;
}
