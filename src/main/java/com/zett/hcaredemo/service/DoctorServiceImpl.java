package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.doctor.DoctorCreateDTO;
import com.zett.hcaredemo.dto.doctor.DoctorDTO;
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


    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
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
        return convertToDTO(doctor);
    }

    // Phương thức để chuyển đổi Doctor thành DoctorDTO
    private DoctorDTO convertToDTO(Doctor doctor) {
        DoctorDTO dto = new DoctorDTO();
        // Điền thông tin vào dto từ doctor
        dto.setId(doctor.getId());
        dto.setUserId(doctor.getUser().getId());
        // Thiết lập các thuộc tính khác
        return dto;
    }
}