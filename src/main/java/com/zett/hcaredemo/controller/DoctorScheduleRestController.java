// DoctorScheduleRestController.java
package com.zett.hcaredemo.controller;

import com.zett.hcaredemo.entity.DoctorSchedule;
import com.zett.hcaredemo.repository.DoctorScheduleRepository;
import com.zett.hcaredemo.dto.doctorschedule.ScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/schedules")
public class DoctorScheduleRestController {

    @Autowired
    private DoctorScheduleRepository doctorScheduleRepository;

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAvailableSchedules(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam UUID doctorId) {
        
        List<DoctorSchedule> schedules = doctorScheduleRepository
            .findByDoctorIdAndScheduleDateAndIsAvailableTrue(doctorId, date);

        List<ScheduleResponse> response = schedules.stream()
            .map(schedule -> new ScheduleResponse(
                schedule.getId(),
                schedule.getScheduleDate(),
                schedule.getStartTime()
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}