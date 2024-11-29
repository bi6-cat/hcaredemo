package com.zett.hcaredemo.service;

import com.zett.hcaredemo.entity.Activity;
import com.zett.hcaredemo.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    // Lưu hoạt động
    @Override
    public void logActivity(String action, String adminName, String status) {
        Activity activity = new Activity();
        activity.setAction(action);
        activity.setAdminName(adminName);
        activity.setTimestamp(LocalDateTime.now());
        activity.setStatus(status);
        activityRepository.save(activity);
    }

    // Lấy danh sách hoạt động gần đây
    @Override
    public List<Activity> getRecentActivities() {
        return activityRepository.findTop10ByOrderByTimestampDesc();
    }
}
