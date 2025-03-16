package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.auth.AccountDTO;
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
import com.zett.hcaredemo.repository.RoleRepository;
import com.zett.hcaredemo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
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
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, UserRepository userRepository,
                             DoctorMapper doctorMapper, UserService userService,
                             DepartmentService departmentService, DoctorScheduleService doctorScheduleService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.doctorMapper = doctorMapper;
        this.userService = userService;
        this.departmentService = departmentService;
        this.doctorScheduleService = doctorScheduleService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
    public AccountDTO create(DoctorCreateDTO doctorCreateDTO) {

        String randomUsername = generateRandomUsername();
        String randomPassword = generateRandomPassword();

        User user = new User();
        user.setUsername(randomUsername);
        user.setPassword(passwordEncoder.encode(randomPassword));
        user.setRoles(Set.of(roleRepository.findByName("DOCTOR")));
        user.setEmail(doctorCreateDTO.getEmail());
        user.setPhone(doctorCreateDTO.getPhoneNumber());

        user.setIsActive(true);
        userRepository.save(user);

        Doctor doctor = DoctorMapper.toEntity(doctorCreateDTO);

        doctor.setUser(user);
        user.setDoctor(doctor);

        Department department = departmentService.findByIdEntity(doctorCreateDTO.getDepartmentId());
        doctor.setDepartment(department);

        Set<Doctor> doctors = department.getDoctors();
        doctors.add(doctor);
        department.setDoctors(doctors);

        doctorRepository.save(doctor);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUsername(randomUsername);
        accountDTO.setPassword(randomPassword);
        log.info("Doctor created with username: {}", randomUsername);
        log.info("Doctor created with password: {}", randomPassword);
        return accountDTO;

    }
    // Phương thức tạo username ngẫu nhiên
    private String generateRandomUsername() {
        return "user_" + UUID.randomUUID().toString(); // Tạo username với UUID duy nhất
    }

    // Phương thức tạo mật khẩu ngẫu nhiên
    private String generateRandomPassword() {
        int length = 10; // Độ dài mật khẩu
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?"; // Các ký tự cho mật khẩu
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index)); // Chọn ngẫu nhiên một ký tự từ chuỗi
        }

        return password.toString(); // Trả về mật khẩu ngẫu nhiên
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
    public Set<DoctorIndexDTO> getDoctorsByDepartment(UUID departmentId) {
        return doctorRepository.findByDepartmentId(departmentId).stream()
                .map(DoctorMapper::doctorIndexDTO)
                .collect(Collectors.toSet());
    }
    @Override
    public DoctorDTO findByUser() {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Doctor doctor = doctorRepository.findByUser(user);
        return DoctorMapper.toDTO(doctor);
    }
}