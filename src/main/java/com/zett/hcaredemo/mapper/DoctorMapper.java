package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.doctor.DoctorCreateDTO;
import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import com.zett.hcaredemo.dto.doctor.DoctorIndexDTO;
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
        doctorEntity.setDoctorSchedules(doctor.getDoctorSchedules() != null ? doctor.getDoctorSchedules().stream()
                .map(DoctorScheduleMapper::toEntity).collect(Collectors.toSet()) : null);
        doctorEntity.setMedicalRecords(doctor.getMedicalRecords() != null ? doctor.getMedicalRecords().stream()
                .map(MedicalRecordMapper::toEntity).collect(Collectors.toSet()) : null);
        doctorEntity.setPrescriptions(doctor.getPrescriptions() != null ? doctor.getPrescriptions().stream()
                .map(PrescriptionMapper::toEntity).collect(Collectors.toSet()) : null);
        doctorEntity.setHealthCheckAppointments(doctor.getHealthCheckAppointments() != null ? doctor.getHealthCheckAppointments().stream()
                .map(HealthCheckAppointmentMapper::toEntity).collect(Collectors.toSet()) : null);

        return doctorEntity;
    }
    public static DoctorIndexDTO doctorIndexDTO(Doctor doctor) {
        if (doctor == null) {
            return null;
        }
        DoctorIndexDTO doctorIndexDTO = new DoctorIndexDTO();
        doctorIndexDTO.setId(doctor.getId());
        doctorIndexDTO.setFullName(doctor.getFullName());
        doctorIndexDTO.setDegree(doctor.getDegree());
        doctorIndexDTO.setGender(doctor.getGender());
        doctorIndexDTO.setPhoneNumber(doctor.getPhoneNumber());
        doctorIndexDTO.setEmail(doctor.getEmail());
        doctorIndexDTO.setExperience(doctor.getExperience());
        doctorIndexDTO.setProfilePictureUrl(doctor.getProfilePictureUrl());
        doctorIndexDTO.setDepartment(doctor.getDepartment() != null ? doctor.getDepartment().getName() : null);
        return doctorIndexDTO;
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
        doctorDTO.setProfilePictureUrl(doctor.getProfilePictureUrl()== null ? "https://i.imgur.com/7lI7D2a.png" : doctor.getProfilePictureUrl());
        doctorDTO.setUser(doctor.getUser() != null ? UserMapper.toDTO(doctor.getUser()) : null);
        doctorDTO.setDepartment(doctor.getDepartment() != null ? DepartmentMapper.toDTO(doctor.getDepartment()) : null);
        doctorDTO.setDoctorSchedules(doctor.getDoctorSchedules() != null ? doctor.getDoctorSchedules().stream()
                .map(DoctorScheduleMapper::toDTO).collect(Collectors.toSet()) : null);
        doctorDTO.setMedicalRecords(doctor.getMedicalRecords() != null ? doctor.getMedicalRecords().stream()
                .map(MedicalRecordMapper::toDTO).collect(Collectors.toSet()) : null);
        doctorDTO.setPrescriptions(doctor.getPrescriptions() != null ? doctor.getPrescriptions().stream()
                .map(PrescriptionMapper::toDTO).collect(Collectors.toSet()) : null);
        doctorDTO.setHealthCheckAppointments(doctor.getHealthCheckAppointments() != null ? doctor.getHealthCheckAppointments().stream()
                .map(HealthCheckAppointmentMapper::toDTO).collect(Collectors.toSet()) : null);
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
        doctor.setDoctorSchedules(doctorUpdateDTO.getDoctorSchedules() != null ? doctorUpdateDTO.getDoctorSchedules().stream()
                .map(DoctorScheduleMapper::toEntity).collect(Collectors.toSet()) : null);
        doctor.setMedicalRecords(doctorUpdateDTO.getMedicalRecords() != null ? doctorUpdateDTO.getMedicalRecords().stream()
                .map(MedicalRecordMapper::toEntity).collect(Collectors.toSet()) : null);
        doctor.setPrescriptions(doctorUpdateDTO.getPrescriptions() != null ? doctorUpdateDTO.getPrescriptions().stream()
                .map(PrescriptionMapper::toEntity).collect(Collectors.toSet()) : null);
        doctor.setHealthCheckAppointments(doctorUpdateDTO.getHealthCheckAppointments() != null ? doctorUpdateDTO.getHealthCheckAppointments().stream()
                .map(HealthCheckAppointmentMapper::toEntity).collect(Collectors.toSet()) : null);
        return doctor;
    }
}
