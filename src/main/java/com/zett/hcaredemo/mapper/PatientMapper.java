package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.patient.PatientCreateDTO;
import com.zett.hcaredemo.dto.patient.PatientDTO;
import com.zett.hcaredemo.dto.patient.PatientUpdateDTO;
import com.zett.hcaredemo.entity.Patient;
import org.springframework.stereotype.Component;
@Component
public class PatientMapper {

    public PatientDTO toDTO(Patient patient) {
        if (patient == null) {
            return null;
        }

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
//        patientDTO.setUserId(patient.getUser().getId());

        return patientDTO;
    }

    Patient toEntity(PatientDTO patientDTO) {
        if (patientDTO == null) {
            return null;
        }

        Patient patient = new Patient();
        patient.setId(patientDTO.getId());
        patient.setFullName(patientDTO.getFullName());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setGender(patientDTO.getGender());
        patient.setAddress(patientDTO.getAddress());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setEmergencyContact(patientDTO.getEmergencyContact());
        patient.setBloodType(patientDTO.getBloodType());
        patient.setAllergies(patientDTO.getAllergies());
        patient.setProfilePictureUrl(patientDTO.getProfilePictureUrl());

        return patient;
    }

    public Patient toEntity(PatientUpdateDTO patientUpdateDTO) {
        if (patientUpdateDTO == null) {
            return null;
        }

        Patient patient = new Patient();
        patient.setFullName(patientUpdateDTO.getFullName());
        patient.setDateOfBirth(patientUpdateDTO.getDateOfBirth());
        patient.setGender(patientUpdateDTO.getGender());
        patient.setAddress(patientUpdateDTO.getAddress());
        patient.setPhoneNumber(patientUpdateDTO.getPhoneNumber());
        patient.setEmergencyContact(patientUpdateDTO.getEmergencyContact());
        patient.setBloodType(patientUpdateDTO.getBloodType());
        patient.setAllergies(patientUpdateDTO.getAllergies());
        patient.setProfilePictureUrl(patientUpdateDTO.getProfilePictureUrl());

        return patient;
    }

    public Patient toEntity(PatientCreateDTO patientCreateDTO) {
        if (patientCreateDTO == null) {
            return null;
        }

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

        return patient;
    }
}
