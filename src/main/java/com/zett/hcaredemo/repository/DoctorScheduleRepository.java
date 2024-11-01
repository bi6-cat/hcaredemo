package com.zett.hcaredemo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.hcaredemo.entity.DoctorSchedule;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, UUID> {
    DoctorSchedule findByDoctorId(UUID doctorId);
}
