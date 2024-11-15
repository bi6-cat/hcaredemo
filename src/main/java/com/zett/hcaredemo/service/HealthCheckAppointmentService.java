package com.zett.hcaredemo.service;

import com.zett.hcaredemo.entity.HealthCheckAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.UUID;

public interface HealthCheckAppointmentService {
    HealthCheckAppointment findById(UUID id);
    HealthCheckAppointment create(HealthCheckAppointment appointment);
    HealthCheckAppointment update(UUID id, HealthCheckAppointment appointment);
    void delete(UUID id);
    HealthCheckAppointment cancelAppointment(UUID id);
    HealthCheckAppointment completeAppointment(UUID id);
    boolean isTimeSlotAvailable(UUID doctorId, LocalDateTime appointmentDate);
    Page<HealthCheckAppointment> findByPatientId(UUID patientId, Pageable pageable);
    Page<HealthCheckAppointment> findByDoctorId(UUID doctorId, Pageable pageable);
    String generateUniqueCode();
    HealthCheckAppointment getByCode(String code);
}