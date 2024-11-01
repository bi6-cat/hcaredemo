package com.zett.hcaredemo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.hcaredemo.entity.HealthCheckAppointment;

public interface HealthCheckAppointmentRepository extends JpaRepository<HealthCheckAppointment, UUID> {
    HealthCheckAppointment findByPatientId(UUID patientId);
}
