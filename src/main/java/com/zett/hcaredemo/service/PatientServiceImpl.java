package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.PatientRequest;
import com.zett.hcaredemo.dto.PatientResponse;
import com.zett.hcaredemo.entity.Patient;
import com.zett.hcaredemo.exception.ResourceNotFoundException;
import com.zett.hcaredemo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientResponse> findAll() {
        return patientRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PatientResponse> findById(UUID id) {
        return patientRepository.findById(id)
                .map(this::convertToResponse);
    }

    @Override
    public PatientResponse save(PatientRequest patientRequest) {
        Patient patient = convertToEntity(patientRequest);
        Patient savedPatient = patientRepository.save(patient);
        return convertToResponse(savedPatient);
    }

    @Override
    public PatientResponse update(UUID id, PatientRequest patientRequest) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        updateEntityWithRequest(patient, patientRequest);
        Patient updatedPatient = patientRepository.save(patient);
        return convertToResponse(updatedPatient);
    }

    @Override
    public void deleteById(UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        patientRepository.deleteById(id);
    }

    private PatientResponse convertToResponse(Patient patient) {
        PatientResponse response = new PatientResponse();
        response.setId(patient.getId());
        response.setFullName(patient.getFullName());
        response.setDateOfBirth(patient.getDateOfBirth());
        response.setGender(patient.getGender());
        response.setAddress(patient.getAddress());
        response.setPhoneNumber(patient.getPhoneNumber());
        response.setEmergencyContact(patient.getEmergencyContact());
        response.setBloodType(patient.getBloodType());
        response.setAllergies(patient.getAllergies());
        response.setProfilePictureUrl(patient.getProfilePictureUrl());
        response.setIsActive(patient.getIsActive());
        response.setCreatedAt(patient.getCreatedAt());
        response.setUpdatedAt(patient.getUpdatedAt());
        return response;
    }

    private Patient convertToEntity(PatientRequest patientRequest) {
        Patient patient = new Patient();
        updateEntityWithRequest(patient, patientRequest);
        return patient;
    }

    private void updateEntityWithRequest(Patient patient, PatientRequest patientRequest) {
        patient.setFullName(patientRequest.getFullName());
        patient.setDateOfBirth(patientRequest.getDateOfBirth());
        patient.setGender(patientRequest.getGender());
        patient.setAddress(patientRequest.getAddress());
        patient.setPhoneNumber(patientRequest.getPhoneNumber());
        patient.setEmergencyContact(patientRequest.getEmergencyContact());
        patient.setBloodType(patientRequest.getBloodType());
        patient.setAllergies(patientRequest.getAllergies());
        patient.setProfilePictureUrl(patientRequest.getProfilePictureUrl());
        patient.setIsActive(patientRequest.getIsActive());
    }
}
