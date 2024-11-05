package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.patient.PatientCreateDTO;
import com.zett.hcaredemo.dto.patient.PatientDTO;
import com.zett.hcaredemo.dto.patient.PatientUpdateDTO;
import com.zett.hcaredemo.entity.Patient;
import com.zett.hcaredemo.entity.User;
import com.zett.hcaredemo.exception.ResourceNotFoundException;
import com.zett.hcaredemo.mapper.PatientMapper;
import com.zett.hcaredemo.repository.PatientRepository;
import com.zett.hcaredemo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PatientMapper patientMapper;

    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public Page<PatientDTO> findAll(String keyword, Pageable pageable) {
        return patientRepository.findAll(keyword, pageable)
                .map(patientMapper::toDTO);
    }

    @Override
    public PatientDTO findById(UUID id) {
        return patientRepository.findById(id)
                .map(patientMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
    }

    @Override
    public void create(PatientCreateDTO patientCreateDTO) {
        Patient patient = patientMapper.toEntity(patientCreateDTO);
        patientRepository.save(patient);
    }

    @Override
    public void update(UUID id, PatientUpdateDTO patientUpdateDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        patientMapper.toEntity(patientUpdateDTO);
        patientRepository.save(patient);
    }

    @Override
    public void delete(UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        patientRepository.delete(patient);
    }
}
