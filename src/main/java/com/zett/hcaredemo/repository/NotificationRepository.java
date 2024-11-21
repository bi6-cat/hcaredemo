package com.zett.hcaredemo.repository;

import java.util.List;
import java.util.UUID;

import com.zett.hcaredemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.hcaredemo.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, UUID>{
    List<Notification> findByUserAndStatus(User user, String status);
}
