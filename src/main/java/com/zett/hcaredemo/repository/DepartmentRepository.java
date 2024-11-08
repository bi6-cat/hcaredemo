package com.zett.hcaredemo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.hcaredemo.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    Department findByName(String name);

    List<Department> findByHospitalId(UUID hospitalId);
}
