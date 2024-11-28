package com.zett.hcaredemo.dto.doctorschedule;

import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class DoctorScheduleDTO {
    private UUID id;
    private LocalTime startTime;
    private LocalDate scheduleDate;
    private Boolean isAvailable;
    private DoctorDTO doctor;
}