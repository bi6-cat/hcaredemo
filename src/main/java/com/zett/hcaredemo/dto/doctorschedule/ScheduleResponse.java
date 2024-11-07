package com.zett.hcaredemo.dto.doctorschedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponse {
    private UUID id;
    private LocalDate scheduleDate;
    private LocalTime startTime;
}