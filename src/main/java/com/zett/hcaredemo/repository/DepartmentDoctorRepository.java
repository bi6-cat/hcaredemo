package com.zett.hcaredemo.repository;

import com.zett.hcaredemo.entities.DepartmentDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDoctorRepository extends JpaRepository<DepartmentDoctor, DepartmentDoctor.DepartmentDoctorId> {
}
