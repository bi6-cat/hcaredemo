package com.zett.hcaredemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.zett.hcaredemo.entity.Doctor;
import com.zett.hcaredemo.entity.DoctorSchedule;
import com.zett.hcaredemo.repository.DoctorScheduleRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    @Autowired
    private DoctorScheduleRepository doctorScheduleRepository;

    @Override
    public List<DoctorSchedule> generateSchedules(LocalDate startDate, Doctor doctor) {
        List<DoctorSchedule> schedules = new ArrayList<>();
        LocalDate currentDate = startDate;

        for (int day = 0; day < 5; day++) {
            if (scheduleExists(currentDate, doctor)) {
                currentDate = currentDate.plusDays(1);
                continue; // Skip to the next date if a schedule already exists
            }

            for (int hour = 8; hour <= 17; hour++) {
                DoctorSchedule schedule = new DoctorSchedule();
                schedule.setId(UUID.randomUUID());
                schedule.setScheduleDate(currentDate);
                schedule.setStartTime(LocalTime.of(hour, 0));
                schedule.setIsAvailable(true);
                schedule.setDoctor(doctor);

                schedules.add(schedule);
            }
            currentDate = currentDate.plusDays(1);
        }
        doctorScheduleRepository.saveAll(schedules);
        return schedules;
    }

    @Override
    public boolean scheduleExists(LocalDate date, Doctor doctor) {
        return doctorScheduleRepository.existsByScheduleDateAndDoctor(date, doctor);
    }

    @Override
    @Scheduled(fixedRate = 3600000) // Runs every hour
    public void updateExpiredSchedules() {
    LocalDateTime now = LocalDateTime.now();
    
    // Format the current date and time as strings
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    String currentDate = now.format(dateFormatter);
    String currentTime = now.format(timeFormatter);

    // Fetch expired schedules
    List<DoctorSchedule> expiredSchedules = doctorScheduleRepository
        .findExpiredSchedules(currentDate, currentTime);

    for (DoctorSchedule schedule : expiredSchedules) {
        schedule.setIsAvailable(false);
        schedule.setUpdatedAt(now);
    }

    // Save all updated schedules
    doctorScheduleRepository.saveAll(expiredSchedules);
}
}
