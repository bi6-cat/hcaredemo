package com.zett.hcaredemo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.hcaredemo.entity.DepartmentService;

public interface DepartmentServiceRepository extends JpaRepository<DepartmentService, UUID> {
    DepartmentService findByName(String name);
}
