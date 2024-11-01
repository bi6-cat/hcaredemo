package com.zett.hcaredemo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.hcaredemo.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    Department findByName(String name);
}
