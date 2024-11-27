package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.patient.PatientCreateDTO;
import com.zett.hcaredemo.dto.patient.PatientDTO;
import com.zett.hcaredemo.dto.patient.PatientUpdateDTO;

import java.util.UUID;

public interface PatientService {

    void create(PatientCreateDTO patientCreateDTO);

    void update(PatientUpdateDTO patientUpdateDTO);

    void delete(UUID id);

    PatientDTO findByUser();
}