// HealthCheckAppointmentServiceImpl.java
package com.zett.hcaredemo.service;

import com.zett.hcaredemo.entity.HealthCheckAppointment;
import com.zett.hcaredemo.repository.HealthCheckAppointmentRepository;
import com.zett.hcaredemo.exception.ResourceNotFoundException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class HealthCheckAppointmentServiceImpl implements HealthCheckAppointmentService {

    @Autowired
    private HealthCheckAppointmentRepository healthCheckAppointmentRepository;


    @Override
    public HealthCheckAppointment findById(UUID id) {
        return healthCheckAppointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
    }

    @Override
    public HealthCheckAppointment create(HealthCheckAppointment appointment) {
        validateAppointment(appointment);
        appointment.setStatus("PENDING");
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setUpdatedAt(LocalDateTime.now());
        return healthCheckAppointmentRepository.save(appointment);
    }

    @Override
    public HealthCheckAppointment update(UUID id, HealthCheckAppointment appointment) {
        HealthCheckAppointment existingAppointment = findById(id);
        validateAppointment(appointment);
        
        existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
        existingAppointment.setDoctor(appointment.getDoctor());
        existingAppointment.setDepartment(appointment.getDepartment());
        existingAppointment.setDepartmentService(appointment.getDepartmentService());
        existingAppointment.setHospital(appointment.getHospital());
        existingAppointment.setUpdatedAt(LocalDateTime.now());
        
        return healthCheckAppointmentRepository.save(existingAppointment);
    }

    @Override
    public void delete(UUID id) {
        HealthCheckAppointment appointment = findById(id);
        healthCheckAppointmentRepository.delete(appointment);
    }

    @Override
    public HealthCheckAppointment cancelAppointment(UUID id) {
        HealthCheckAppointment appointment = findById(id);
        appointment.setStatus("CANCELLED");
        appointment.setUpdatedAt(LocalDateTime.now());
        return healthCheckAppointmentRepository.save(appointment);
    }

    @Override
    public HealthCheckAppointment completeAppointment(UUID id) {
        HealthCheckAppointment appointment = findById(id);
        appointment.setStatus("COMPLETED");
        appointment.setUpdatedAt(LocalDateTime.now());
        return healthCheckAppointmentRepository.save(appointment);
    }

    @Override
    public boolean isTimeSlotAvailable(UUID doctorId, LocalDateTime appointmentDate) {
        return !healthCheckAppointmentRepository.existsByDoctorIdAndAppointmentDateAndStatusNot(
            doctorId, appointmentDate, "CANCELLED");
    }

    @Override
    public Page<HealthCheckAppointment> findByPatientId(UUID patientId, Pageable pageable) {
        return healthCheckAppointmentRepository.findByPatientId(patientId, pageable);
    }

    @Override
    public Page<HealthCheckAppointment> findByDoctorId(UUID doctorId, Pageable pageable) {
        return healthCheckAppointmentRepository.findByDoctorId(doctorId, pageable);
    }

    private void validateAppointment(HealthCheckAppointment appointment) {
        if (appointment.getAppointmentDate() == null) {
            throw new IllegalArgumentException("Appointment date cannot be null");
        }
        if (appointment.getAppointmentDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past");
        }
        if (appointment.getDoctor() == null) {
            throw new IllegalArgumentException("Doctor cannot be null");
        }
        if (appointment.getPatient() == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        }
        if (!isTimeSlotAvailable(appointment.getDoctor().getId(), appointment.getAppointmentDate())) {
            throw new IllegalArgumentException("Selected time slot is not available");
        }
    }

    @Override
    public String generateUniqueCode() {
                String code;
        do {
            code = RandomStringUtils.randomAlphabetic(5).toUpperCase();
        } while (healthCheckAppointmentRepository.existsByCode(code));
        return code;
    }

    @Override
    public HealthCheckAppointment getByCode(String code) {
        return healthCheckAppointmentRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with code: " + code));
    }

    @Override
    public void confirmAppointment(UUID id) {
        HealthCheckAppointment appointment = findById(id);
        appointment.setStatus("CONFIRMED");
        appointment.setUpdatedAt(LocalDateTime.now());
        healthCheckAppointmentRepository.save(appointment);
    }

    @Override
    public List<HealthCheckAppointment> findByDoctorId(UUID doctorId) {
        return healthCheckAppointmentRepository.findAllByDoctorId(doctorId);
    }
}