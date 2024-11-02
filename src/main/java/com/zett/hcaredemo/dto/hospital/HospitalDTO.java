package com.zett.hcaredemo.dto.hospital;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.zett.hcaredemo.dto.department.DepartmentDTO;
import com.zett.hcaredemo.dto.heathcheckappointment.HealthCheckAppointmentDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalDTO {
    private UUID id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<DepartmentDTO> departments; 
    private Set<HealthCheckAppointmentDTO> healthCheckAppointments;
}
