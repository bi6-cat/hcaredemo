package com.zett.hcaredemo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.hcaredemo.entity.LabTest;

public interface LabTestRepository extends JpaRepository<LabTest, UUID>{

}
