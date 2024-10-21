package com.zett.hcaredemo.repository;

import com.zett.hcaredemo.entities.HospitalDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalDoctorRepository extends JpaRepository<HospitalDoctor, HospitalDoctor.HospitalDoctorId> {
}
