package com.zett.hcaredemo.repository;

import com.zett.hcaredemo.entities.HospitalDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalDepartmentRepository extends JpaRepository<HospitalDepartment, HospitalDepartment.HospitalDepartmentId> {
}
