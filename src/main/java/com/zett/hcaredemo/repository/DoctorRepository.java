package com.zett.hcaredemo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.hcaredemo.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Doctor findByEmail(String email);
}
