package com.zett.hcaredemo.service;

import com.zett.hcaredemo.entity.Activity;

import java.util.List;

public interface ActivityService {
    // Lưu hoạt động
    void logActivity(String action, String adminName, String status);

    // Lấy danh sách hoạt động gần đây
    List<Activity> getRecentActivities();
}
