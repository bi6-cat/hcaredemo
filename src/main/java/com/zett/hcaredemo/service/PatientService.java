package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.PatientRequest;
import com.zett.hcaredemo.dto.PatientResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientService {
    List<PatientResponse> findAll();

    Optional<PatientResponse> findById(UUID id);

    PatientResponse save(PatientRequest patientRequest);

    PatientResponse update(UUID id, PatientRequest patientRequest);

    void deleteById(UUID id);
}
