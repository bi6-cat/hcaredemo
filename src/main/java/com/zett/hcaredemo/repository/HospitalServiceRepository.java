package com.zett.hcaredemo.repository;

import com.zett.hcaredemo.entities.HospitalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalServiceRepository extends JpaRepository<HospitalService, HospitalService.HospitalServiceId> {
}
