package com.zett.hcaredemo.repository;

import com.zett.hcaredemo.entity.Doctor;
import com.zett.hcaredemo.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    @Query("SELECT d FROM Doctor d WHERE d.fullName LIKE %?1% OR d.email LIKE %?1%")
    List<Doctor> findByKeyword(String keyword);

    @Query("SELECT d FROM Doctor d WHERE d.fullName LIKE %?1% OR d.email LIKE %?1%")
    Page<Doctor> findByKeyword(String keyword, Pageable pageable);

    Doctor findByUser(User user);

    List<Doctor> findByDepartmentId(UUID id);
}