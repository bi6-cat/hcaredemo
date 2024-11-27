package com.zett.hcaredemo.repository;

import com.zett.hcaredemo.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {
    Page<Activity> findByAdminNameContainingIgnoreCase(String keyword, Pageable pageable);

    List<Activity> findTop10ByOrderByTimestampDesc();
}
