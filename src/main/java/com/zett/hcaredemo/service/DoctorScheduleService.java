package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.doctorschedule.DoctorScheduleDTO;
import com.zett.hcaredemo.entity.Doctor;
import com.zett.hcaredemo.entity.DoctorSchedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface DoctorScheduleService {

    Set<DoctorSchedule> findAllByIds(Set<UUID> ids);

    void updateExpiredSchedules();

    List<DoctorScheduleDTO> findAll();

    List<DoctorSchedule> generateSchedules(LocalDate startDate, Doctor doctor);

    boolean scheduleExists(LocalDate date, Doctor doctor);
}