package com.zett.hcaredemo.dto.department;

import com.zett.hcaredemo.dto.heathcheckappointment.HealthCheckAppointmentDTO;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class DepartmentServiceDTO {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private DepartmentDTO department;
    private Set<HealthCheckAppointmentDTO> healthCheckAppointments;
}
