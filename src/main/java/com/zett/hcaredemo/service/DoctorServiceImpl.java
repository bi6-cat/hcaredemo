package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.doctor.DoctorCreateDTO;
import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import com.zett.hcaredemo.dto.doctor.DoctorUpdateDTO;
import com.zett.hcaredemo.entity.Doctor;
import com.zett.hcaredemo.entity.User;
import com.zett.hcaredemo.exception.ResourceNotFoundException;
import com.zett.hcaredemo.mapper.DoctorMapper;
import com.zett.hcaredemo.repository.DoctorRepository;
import com.zett.hcaredemo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private final UserRepository userRepository;
    private final DoctorMapper doctorMapper;


    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, UserRepository userRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public List<DoctorDTO> findAll() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorDTO> findAll(String keyword) {
        return doctorRepository.findByKeyword(keyword).stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DoctorDTO> findAll(String keyword, Pageable pageable) {
        return doctorRepository.findByKeyword(keyword, pageable)
                .map(doctorMapper::toDTO);
    }

    @Override
    public DoctorDTO findById(UUID id) {
        return doctorRepository.findById(id)
                .map(doctorMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    @Override
    public DoctorDTO create(DoctorCreateDTO doctorCreateDTO) {
        Doctor doctor = doctorMapper.toEntity(doctorCreateDTO);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDTO(savedDoctor);
    }

    @Override
    public DoctorDTO update(UUID id, DoctorUpdateDTO dto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        doctor = doctorMapper.updateEntity(doctor, dto);
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDTO(updatedDoctor);
    }

    @Override
    public void delete(UUID id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        doctorRepository.delete(doctor);
    }

    @Override
    public DoctorDTO getDoctorByUsername(String username) {
        // Tìm người dùng dựa trên tên đăng nhập
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new EntityNotFoundException("User not found with username: " + username);
        }
        // Tìm bác sĩ dựa trên thông tin người dùng
        Doctor doctor = doctorRepository.findByUser(user);
        if (doctor == null) {
            throw new EntityNotFoundException("Doctor not found for user: " + username);
        }

        // Chuyển đổi Doctor sang DoctorDTO trước khi trả về
        return doctorMapper.toDTO(doctor);
    }
}