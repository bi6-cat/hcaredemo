package com.zett.hcaredemo.dto.heathcheckappointment;

import com.zett.hcaredemo.dto.department.DepartmentServiceDTO;
import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import com.zett.hcaredemo.dto.hospital.HospitalDTO;
import com.zett.hcaredemo.dto.patient.PatientDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class HealthCheckAppointmentDTO {
    private UUID id;
    private LocalDateTime appointmentDate;
    private String status;
    private PatientDTO patient;
    private DoctorDTO doctor;
    private DepartmentServiceDTO departmentService;
    private HospitalDTO hospital;
}
