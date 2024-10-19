package com.zett.hcaredemo.repository;

import com.zett.hcaredemo.entities.DoctorPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorPatientRepository extends JpaRepository<DoctorPatient, String> {
}
