package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.heathcheckappointment.HealthCheckAppointmentDTO;
import com.zett.hcaredemo.entity.HealthCheckAppointment;

public class HealthCheckAppointmentMapper {
    public static HealthCheckAppointmentDTO toDTO(HealthCheckAppointment healthCheckAppointment) {
        HealthCheckAppointmentDTO healthCheckAppointmentDTO = new HealthCheckAppointmentDTO();
        healthCheckAppointmentDTO.setAppointmentDate(healthCheckAppointment.getAppointmentDate());
        healthCheckAppointmentDTO.setPatient(PatientMapper.toDTO(healthCheckAppointment.getPatient()));
        healthCheckAppointmentDTO.setDoctor(DoctorMapper.toDTO(healthCheckAppointment.getDoctor()));
        return healthCheckAppointmentDTO;
    }

    public static HealthCheckAppointment toEntity(HealthCheckAppointmentDTO healthCheckAppointmentDTO) {
        HealthCheckAppointment healthCheckAppointment = new HealthCheckAppointment();
        healthCheckAppointment.setAppointmentDate(healthCheckAppointmentDTO.getAppointmentDate());
        healthCheckAppointment.setPatient(PatientMapper.toEntity(healthCheckAppointmentDTO.getPatient()));
        healthCheckAppointment.setDoctor(DoctorMapper.toEntity(healthCheckAppointmentDTO.getDoctor()));
        return healthCheckAppointment;
    }
}
