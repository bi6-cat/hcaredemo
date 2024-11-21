package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.doctor.DoctorCreateDTO;
import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import com.zett.hcaredemo.dto.doctor.DoctorUpdateDTO;
import com.zett.hcaredemo.entity.Doctor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DoctorMapper {

    public static Doctor toEntity(DoctorDTO doctor) {
        if (doctor == null) {
            return null;
        }
        Doctor doctorEntity = new Doctor();
        doctorEntity.setId(doctor.getId());
        doctorEntity.setFullName(doctor.getFullName());
        doctorEntity.setDegree(doctor.getDegree());
        doctorEntity.setGender(doctor.getGender());
        doctorEntity.setPhoneNumber(doctor.getPhoneNumber());
        doctorEntity.setEmail(doctor.getEmail());
        doctorEntity.setExperience(doctor.getExperience());
        doctorEntity.setProfilePictureUrl(doctor.getProfilePictureUrl());
        doctorEntity.setUser(UserMapper.toUser(doctor.getUser()));
        doctorEntity.setDepartment(DepartmentMapper.toEntity(doctor.getDepartment()));
        doctorEntity.setDoctorSchedules(doctor.getDoctorSchedules().stream()
                .map(DoctorScheduleMapper::toEntity).collect(Collectors.toSet()));
        doctorEntity.setMedicalRecords(doctor.getMedicalRecords().stream()
                .map(MedicalRecordMapper::toEntity).collect(Collectors.toSet()));
        doctorEntity.setPrescriptions(doctor.getPrescriptions().stream()
                .map(PrescriptionMapper::toEntity).collect(Collectors.toSet()));
        doctorEntity.setHealthCheckAppointments(doctor.getHealthCheckAppointments().stream()
                .map(HealthCheckAppointmentMapper::toEntity).collect(Collectors.toSet()));

        return doctorEntity;
    }

    public static DoctorDTO toDTO(Doctor doctor) {
        if (doctor == null) {
            return null;
        }
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setFullName(doctor.getFullName());
        doctorDTO.setDegree(doctor.getDegree());
        doctorDTO.setGender(doctor.getGender());
        doctorDTO.setPhoneNumber(doctor.getPhoneNumber());
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setExperience(doctor.getExperience());
        doctorDTO.setProfilePictureUrl(doctor.getProfilePictureUrl());
        doctorDTO.setUser(UserMapper.toDTO(doctor.getUser()));
        doctorDTO.setDepartment(DepartmentMapper.toDTO(doctor.getDepartment()));
        doctorDTO.setDoctorSchedules(doctor.getDoctorSchedules().stream()
                .map(DoctorScheduleMapper::toDTO).collect(Collectors.toSet()));
        doctorDTO.setMedicalRecords(doctor.getMedicalRecords().stream()
                .map(MedicalRecordMapper::toDTO).collect(Collectors.toSet()));
        doctorDTO.setPrescriptions(doctor.getPrescriptions().stream()
                .map(PrescriptionMapper::toDTO).collect(Collectors.toSet()));
        doctorDTO.setHealthCheckAppointments(doctor.getHealthCheckAppointments().stream()
                .map(HealthCheckAppointmentMapper::toDTO).collect(Collectors.toSet()));
        return doctorDTO;
    }

    public static Doctor toEntity(DoctorCreateDTO doctorCreateDTO) {
        if (doctorCreateDTO == null) {
            return null;
        }
        Doctor doctor = new Doctor();
        doctor.setFullName(doctorCreateDTO.getFullName());
        doctor.setDegree(doctorCreateDTO.getDegree());
        doctor.setGender(doctorCreateDTO.getGender());
        doctor.setPhoneNumber(doctorCreateDTO.getPhoneNumber());
        doctor.setEmail(doctorCreateDTO.getEmail());
        doctor.setExperience(doctorCreateDTO.getExperience());
        doctor.setProfilePictureUrl(doctorCreateDTO.getProfilePictureUrl());
        doctor.setMedicalRecords(null);
        doctor.setPrescriptions(null);
        doctor.setHealthCheckAppointments(null);
        return doctor;
    }

    public Doctor updateEntity(Doctor doctor, DoctorUpdateDTO doctorUpdateDTO) {
        if (doctor == null) {
            return null;
        }
        doctor.setFullName(doctorUpdateDTO.getFullName());
        doctor.setDegree(doctorUpdateDTO.getDegree());
        doctor.setGender(doctorUpdateDTO.getGender());
        doctor.setPhoneNumber(doctorUpdateDTO.getPhoneNumber());
        doctor.setEmail(doctorUpdateDTO.getEmail());
        doctor.setExperience(doctorUpdateDTO.getExperience());
        doctor.setProfilePictureUrl(doctorUpdateDTO.getProfilePictureUrl());
        doctor.setDepartment(DepartmentMapper.toEntity(doctorUpdateDTO.getDepartment()));
        doctor.setUser(UserMapper.toUser(doctorUpdateDTO.getUser()));
        doctor.setDoctorSchedules(doctorUpdateDTO.getDoctorSchedules().stream()
                .map(DoctorScheduleMapper::toEntity).collect(Collectors.toSet()));
        doctor.setMedicalRecords(doctorUpdateDTO.getMedicalRecords().stream()
                .map(MedicalRecordMapper::toEntity).collect(Collectors.toSet()));
        doctor.setPrescriptions(doctorUpdateDTO.getPrescriptions().stream()
                .map(PrescriptionMapper::toEntity).collect(Collectors.toSet()));
        doctor.setHealthCheckAppointments(doctorUpdateDTO.getHealthCheckAppointments().stream()
                .map(HealthCheckAppointmentMapper::toEntity).collect(Collectors.toSet()));
        return doctor;
    }
}
