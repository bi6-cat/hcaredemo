package com.zett.hcaredemo.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.hcaredemo.entity.Patient;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, UUID>{
    @Query("SELECT p FROM Patient p WHERE p.fullName LIKE %?1%")
    Page<Patient> findAll(String keyword, Pageable pageable);
    Patient findByUser_Username(String username);

}
