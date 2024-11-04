package com.zett.hcaredemo.dto.doctorschedule;

import java.time.LocalDate;
import java.util.UUID;


import lombok.*;

@Getter
@Setter
public class DoctorScheduleDTO {
    private UUID id;
    private UUID doctor_id;
    private LocalDate scheduleDate;
    private String time;
    private Boolean isAvailable;
}