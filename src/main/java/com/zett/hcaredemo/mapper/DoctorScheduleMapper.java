package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.doctorschedule.DoctorScheduleDTO;
import com.zett.hcaredemo.entity.DoctorSchedule;

public class DoctorScheduleMapper {
    public static DoctorScheduleDTO toDTO(DoctorSchedule doctorSchedule) {
        DoctorScheduleDTO doctorScheduleDTO = new DoctorScheduleDTO();
        doctorScheduleDTO.setId(doctorSchedule.getId());
        doctorScheduleDTO.setStartTime(doctorSchedule.getStartTime());
        doctorScheduleDTO.setScheduleDate(doctorSchedule.getScheduleDate());
        doctorScheduleDTO.setIsAvailable(doctorSchedule.getIsAvailable());
        doctorScheduleDTO.setDoctor(DoctorMapper.toDTO(doctorSchedule.getDoctor()));
        return doctorScheduleDTO;
    }

    public static DoctorSchedule toEntity(DoctorScheduleDTO doctorScheduleDTO) {
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        doctorSchedule.setId(doctorScheduleDTO.getId());
        doctorSchedule.setStartTime(doctorScheduleDTO.getStartTime());
        doctorSchedule.setScheduleDate(doctorScheduleDTO.getScheduleDate());
        doctorSchedule.setIsAvailable(doctorScheduleDTO.getIsAvailable());
        doctorSchedule.setDoctor(DoctorMapper.toEntity(doctorScheduleDTO.getDoctor()));
        return doctorSchedule;
    }
}
