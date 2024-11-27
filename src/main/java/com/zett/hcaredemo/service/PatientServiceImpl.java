package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.patient.PatientCreateDTO;
import com.zett.hcaredemo.dto.patient.PatientDTO;
import com.zett.hcaredemo.dto.patient.PatientUpdateDTO;
import com.zett.hcaredemo.entity.Patient;
import com.zett.hcaredemo.entity.User;
import com.zett.hcaredemo.exception.ResourceNotFoundException;
import com.zett.hcaredemo.mapper.PatientMapper;
import com.zett.hcaredemo.mapper.UserMapper;
import com.zett.hcaredemo.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final UserService userService;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper, UserService userService) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.userService = userService;
    }


    @Override
    public void create(PatientCreateDTO patientCreateDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        Patient patient = PatientMapper.toEntity(patientCreateDTO);
        patient.setUser(user);
        user.setPatient(patient);
        patientRepository.save(patient);
    }

    @Override
    public void update(PatientUpdateDTO patientUpdateDTO) {
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        Patient patient = user.getPatient();
        if (patient == null) {
            throw new ResourceNotFoundException("Patient not found");
        }
        patientMapper.toEntity(patient, patientUpdateDTO);
        patientRepository.save(patient);
    }

    @Override
    public void delete(UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        patientRepository.delete(patient);
    }

    @Override
    public PatientDTO findByUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        Patient patient = user.getPatient();
        if (patient == null) {
            throw new ResourceNotFoundException("Patient not found");
        }
        return patientMapper.toDTO(patient);
    }
}
