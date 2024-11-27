package com.zett.hcaredemo.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.hcaredemo.entity.Hospital;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, UUID> {
    Hospital findByName(String name);
    Page<Hospital> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
