package com.zett.hcaredemo.dto.doctor;

import com.zett.hcaredemo.dto.auth.UserDTO;
import com.zett.hcaredemo.dto.department.DepartmentDTO;
import com.zett.hcaredemo.dto.doctorschedule.DoctorScheduleDTO;
import com.zett.hcaredemo.dto.heathcheckappointment.HealthCheckAppointmentDTO;
import com.zett.hcaredemo.dto.medicine.MedicalRecordDTO;
import com.zett.hcaredemo.dto.prescription.PrescriptionDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class DoctorDTO {
    private UUID id;
    private String fullName;
    private String degree;
    private String gender;
    private String phoneNumber;
    private String email;
    private String experience;
    private String profilePictureUrl;
    private UserDTO user;
    private DepartmentDTO department;
    private Set<DoctorScheduleDTO> doctorSchedules;
    private Set<HealthCheckAppointmentDTO> healthCheckAppointments;
    private Set<PrescriptionDTO> prescriptions;
    private Set<MedicalRecordDTO> medicalRecords;
}