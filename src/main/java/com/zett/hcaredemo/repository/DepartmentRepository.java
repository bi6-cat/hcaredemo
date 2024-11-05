package com.zett.hcaredemo.repository;

import com.zett.hcaredemo.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    @Query("SELECT d FROM Department d WHERE d.name LIKE %?1%")
    Page<Department> findAllByKeyword(String keyword, Pageable pageable);
    @Query("SELECT d FROM Department d WHERE d.hospital.id = ?1 AND d.name LIKE %?2%")
    Page<Department> findByHospitalIdAndKeyword(UUID hospitalId, String keyword, Pageable pageable);
}
