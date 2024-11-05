package com.zett.hcaredemo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.zett.hcaredemo.dto.patient.PatientCreateDTO;
import com.zett.hcaredemo.dto.patient.PatientDTO;
import com.zett.hcaredemo.dto.patient.PatientUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface PatientService {
    Page<PatientDTO> findAll(String keyword, Pageable pageable);
    PatientDTO findById(UUID id);
    void create(PatientCreateDTO patientCreateDTO);
    void update(UUID id, PatientUpdateDTO patientUpdateDTO);
    void delete(UUID id);
}