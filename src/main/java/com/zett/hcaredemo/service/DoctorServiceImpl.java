package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.doctor.DoctorCreateDTO;
import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import com.zett.hcaredemo.dto.doctor.DoctorIndexDTO;
import com.zett.hcaredemo.dto.doctor.DoctorUpdateDTO;
import com.zett.hcaredemo.entity.Department;
import com.zett.hcaredemo.entity.Doctor;
import com.zett.hcaredemo.entity.DoctorSchedule;
import com.zett.hcaredemo.entity.User;
import com.zett.hcaredemo.exception.ResourceNotFoundException;
import com.zett.hcaredemo.mapper.DoctorMapper;
import com.zett.hcaredemo.repository.DoctorRepository;
import com.zett.hcaredemo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DoctorMapper doctorMapper;
    private final UserService userService;
    private final DepartmentService departmentService;
    private final DoctorScheduleService doctorScheduleService;


    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, UserRepository userRepository,
                             DoctorMapper doctorMapper, UserService userService,
                             DepartmentService departmentService, DoctorScheduleService doctorScheduleService) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.doctorMapper = doctorMapper;
        this.userService = userService;
        this.departmentService = departmentService;
        this.doctorScheduleService = doctorScheduleService;
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
    public Page<DoctorIndexDTO> findAll(String keyword, Pageable pageable) {
        log.info("Finding doctors by keyword: {}", keyword);
        return doctorRepository.findByKeyword(keyword, pageable)
                .map(DoctorMapper::doctorIndexDTO);
    }

    @Override
    public DoctorDTO findById(UUID id) {
        return doctorRepository.findById(id)
                .map(DoctorMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    @Override
    public DoctorDTO create(DoctorCreateDTO doctorCreateDTO) {
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Doctor doctor = DoctorMapper.toEntity(doctorCreateDTO);
        doctor.setUser(user);
        user.setDoctor(doctor);
        Department department = departmentService.findByIdEntity(doctorCreateDTO.getDepartmentId());
        doctor.setDepartment(department);
        Set<Doctor> doctors = department.getDoctors();
        doctors.add(doctor);
        department.setDoctors(doctors);
        Set<DoctorSchedule> doctorSchedule = doctorScheduleService.findAllByIds(doctorCreateDTO.getDoctorScheduleIds());
        doctor.setDoctorSchedules(doctorSchedule);
        for (DoctorSchedule schedule : doctorSchedule) {
            schedule.setDoctor(doctor);
        }
        Doctor savedDoctor = doctorRepository.save(doctor);
        userRepository.save(user);
        return DoctorMapper.toDTO(savedDoctor);
    }

    @Override
    public DoctorDTO update(UUID id, DoctorUpdateDTO dto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        doctor = doctorMapper.updateEntity(doctor, dto);
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return DoctorMapper.toDTO(updatedDoctor);
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
        return DoctorMapper.toDTO(doctor);
    }

    @Override
    public long countDoctor() {
        return doctorRepository.count();
    }

    @Override
    public Set<DoctorDTO> getDoctorsByDepartment(UUID departmentId) {
        return doctorRepository.findByDepartmentId(departmentId).stream()
                .map(DoctorMapper::toDTO)
                .collect(Collectors.toSet());
    }
}