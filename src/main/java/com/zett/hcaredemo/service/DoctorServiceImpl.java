package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.doctor.DoctorCreateDTO;
import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import com.zett.hcaredemo.entity.Doctor;
import com.zett.hcaredemo.exception.ResourceNotFoundException;
import com.zett.hcaredemo.mapper.DoctorMapper;
import com.zett.hcaredemo.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<DoctorDTO> findAll() {
        return doctorRepository.findAll().stream()
                .map(DoctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorDTO> findAll(String keyword) {
        return doctorRepository.findByKeyword(keyword).stream()
                .map(DoctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DoctorDTO> findAll(String keyword, Pageable pageable) {
        return doctorRepository.findByKeyword(keyword, pageable)
                .map(DoctorMapper::toDTO);
    }

    @Override
    public DoctorDTO findById(UUID id) {
        return doctorRepository.findById(id)
                .map(DoctorMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    @Override
    public DoctorDTO create(DoctorCreateDTO doctorCreateDTO) {
        Doctor doctor = DoctorMapper.toEntity(doctorCreateDTO);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return DoctorMapper.toDTO(savedDoctor);
    }

    @Override
    public DoctorDTO update(UUID id, DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        updateEntityWithDTO(doctor, doctorDTO);
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return DoctorMapper.toDTO(updatedDoctor);
    }

    @Override
    public void delete(UUID id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        doctorRepository.delete(doctor);
    }

    private void updateEntityWithDTO(Doctor doctor, DoctorDTO doctorDTO) {
        doctor.setFullName(doctorDTO.getFullName());
        doctor.setDegree(doctorDTO.getDegree());
        doctor.setGender(doctorDTO.getGender());
        doctor.setPhoneNumber(doctorDTO.getPhoneNumber());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setExperience(doctorDTO.getExperience());
        doctor.setProfilePictureUrl(doctorDTO.getProfilePictureUrl());
    }
}