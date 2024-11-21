package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.patient.PatientCreateDTO;
import com.zett.hcaredemo.dto.patient.PatientDTO;
import com.zett.hcaredemo.dto.patient.PatientUpdateDTO;
import com.zett.hcaredemo.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public static Patient toEntity(PatientDTO patient) {
        if (patient == null) return null;

        Patient patientEntity = new Patient();
        patientEntity.setId(patient.getId());
        patientEntity.setFullName(patient.getFullName());
        patientEntity.setDateOfBirth(patient.getDateOfBirth());
        patientEntity.setGender(patient.getGender());
        patientEntity.setAddress(patient.getAddress());
        patientEntity.setPhoneNumber(patient.getPhoneNumber());
        patientEntity.setEmergencyContact(patient.getEmergencyContact());
        patientEntity.setBloodType(patient.getBloodType());
        patientEntity.setAllergies(patient.getAllergies());
        patientEntity.setProfilePictureUrl(patient.getProfilePictureUrl());
        patientEntity.setHealthInsuranceNumber(patient.getHealthInsuranceNumber());
        patientEntity.setEthnicity(patient.getEthnicity());
        patientEntity.setUser(UserMapper.toUser(patient.getUser()));

        return patientEntity;
    }

    public static PatientDTO toDTO(Patient patient) {
        if (patient == null) return null;

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setFullName(patient.getFullName());
        patientDTO.setDateOfBirth(patient.getDateOfBirth());
        patientDTO.setGender(patient.getGender());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setPhoneNumber(patient.getPhoneNumber());
        patientDTO.setEmergencyContact(patient.getEmergencyContact());
        patientDTO.setBloodType(patient.getBloodType());
        patientDTO.setAllergies(patient.getAllergies());
        patientDTO.setProfilePictureUrl(patient.getProfilePictureUrl());
        patientDTO.setHealthInsuranceNumber(patient.getHealthInsuranceNumber());
        patientDTO.setEthnicity(patient.getEthnicity());
        patientDTO.setUser(UserMapper.toUserDTO(patient.getUser()));

        return patientDTO;
    }

    public static Patient toEntity(PatientCreateDTO patientCreateDTO) {
        if (patientCreateDTO == null) return null;

        Patient patient = new Patient();
        patient.setFullName(patientCreateDTO.getFullName());
        patient.setDateOfBirth(patientCreateDTO.getDateOfBirth());
        patient.setGender(patientCreateDTO.getGender());
        patient.setAddress(patientCreateDTO.getAddress());
        patient.setPhoneNumber(patientCreateDTO.getPhoneNumber());
        patient.setEmergencyContact(patientCreateDTO.getEmergencyContact());
        patient.setBloodType(patientCreateDTO.getBloodType());
        patient.setAllergies(patientCreateDTO.getAllergies());
        patient.setProfilePictureUrl(patientCreateDTO.getProfilePictureUrl());
        patient.setHealthInsuranceNumber(patientCreateDTO.getHealthInsuranceNumber());
        patient.setEthnicity(patientCreateDTO.getEthnicity());
        return patient;
    }

    public Patient toEntity(Patient patient, PatientUpdateDTO patientUpdateDTO) {
        if (patientUpdateDTO == null) return null;

        patient.setFullName(patientUpdateDTO.getFullName());
        patient.setDateOfBirth(patientUpdateDTO.getDateOfBirth());
        patient.setGender(patientUpdateDTO.getGender());
        patient.setAddress(patientUpdateDTO.getAddress());
        patient.setPhoneNumber(patientUpdateDTO.getPhoneNumber());
        patient.setEmergencyContact(patientUpdateDTO.getEmergencyContact());
        patient.setBloodType(patientUpdateDTO.getBloodType());
        patient.setAllergies(patientUpdateDTO.getAllergies());
        patient.setProfilePictureUrl(patientUpdateDTO.getProfilePictureUrl());
        patient.setHealthInsuranceNumber(patientUpdateDTO.getHealthInsuranceNumber());
        patient.setEthnicity(patientUpdateDTO.getEthnicity());

        return patient;
    }
}
