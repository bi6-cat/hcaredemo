package com.zett.hcaredemo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.hcaredemo.entity.LabTestAppointment;

public interface LabTestAppointmentRepository extends JpaRepository<LabTestAppointment, UUID> {

}
