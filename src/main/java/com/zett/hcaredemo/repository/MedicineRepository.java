package com.zett.hcaredemo.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.hcaredemo.entity.Medicine;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, UUID>{
    Page<Medicine> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}
