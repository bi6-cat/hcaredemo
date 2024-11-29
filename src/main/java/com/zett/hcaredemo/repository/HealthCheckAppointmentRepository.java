package com.zett.hcaredemo.repository;

import com.zett.hcaredemo.entity.HealthCheckAppointment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HealthCheckAppointmentRepository extends JpaRepository<HealthCheckAppointment, UUID> {
    Page<HealthCheckAppointment> findByPatientId(UUID patientId, Pageable pageable);
    Page<HealthCheckAppointment> findByDoctorId(UUID doctorId, Pageable pageable);
    Page<HealthCheckAppointment> findByPatientFullNameContainingIgnoreCase(String keyword, Pageable pageable);
    boolean existsByDoctorIdAndAppointmentDateAndStatusNot(UUID doctorId, LocalDateTime appointmentDate, String status);
    boolean existsByCode(String code);
    Optional<HealthCheckAppointment> findByCode(String code);
    Object findByDoctorId(UUID doctorId);
    List<HealthCheckAppointment> findAllByDoctorId(UUID doctorId);
}