package com.zett.hcaredemo.service;

import com.zett.hcaredemo.entity.Doctor;
import com.zett.hcaredemo.entity.DoctorSchedule;

import java.time.LocalDate;
import java.util.List;

public interface DoctorScheduleService {
    void updateExpiredSchedules();
    List<DoctorSchedule> generateSchedules(LocalDate startDate, Doctor doctor);
    boolean scheduleExists(LocalDate date, Doctor doctor);
}