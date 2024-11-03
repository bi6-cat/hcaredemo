package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.doctor.DoctorCreateDTO;
import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import com.zett.hcaredemo.entity.Doctor;

public class DoctorMapper {

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
        doctorDTO.setUserId(doctor.getUser() != null ? doctor.getUser().getId() : null);
        doctorDTO.setDepartmentId(doctor.getDepartment() != null ? doctor.getDepartment().getId() : null);
        return doctorDTO;
    }

    public static Doctor toEntity(DoctorDTO doctorDTO) {
        if (doctorDTO == null) {
            return null;
        }

        Doctor doctor = new Doctor();
        doctor.setId(doctorDTO.getId());
        doctor.setFullName(doctorDTO.getFullName());
        doctor.setDegree(doctorDTO.getDegree());
        doctor.setGender(doctorDTO.getGender());
        doctor.setPhoneNumber(doctorDTO.getPhoneNumber());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setExperience(doctorDTO.getExperience());
        doctor.setProfilePictureUrl(doctorDTO.getProfilePictureUrl());
        // Set User and Department if needed
        return doctor;
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
        // Set other fields as needed
        return doctor;
    }
}