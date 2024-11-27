package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.auth.ChangePasswordDTO;
import com.zett.hcaredemo.dto.auth.RegisterDTO;
import com.zett.hcaredemo.dto.auth.UserDTO;
import com.zett.hcaredemo.entity.Patient;
import com.zett.hcaredemo.entity.Role;
import com.zett.hcaredemo.entity.User;
import com.zett.hcaredemo.mapper.UserMapper;
import com.zett.hcaredemo.repository.PatientRepository;
import com.zett.hcaredemo.repository.RoleRepository;
import com.zett.hcaredemo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PatientRepository patientRepository;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                             UserMapper userMapper, RoleRepository roleRepository, 
                             PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> authorities = Set.of();

        // Check if user has roles and map them to authorities
        if (user.getRoles() != null) {
            authorities = user.getRoles().stream()
                    .map(role -> "ROLE_" + role.getName())
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }

        // Return a UserDetails object with username, password and authorities (empty if
        // no roles)
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities);
    }

    @Override
    public UserDTO register(RegisterDTO registerDTO) {
        // Validate register request
        if (registerDTO == null) {
            throw new IllegalArgumentException("Register request cannot be null");
        }

        // Check if username is already taken
        var existingUser = userRepository.findByUsername(registerDTO.getUsername());

        if (existingUser != null) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // Compare password and confirm password
        if (registerDTO.getPassword() == null ||
                !registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // Create a new user
        var user = userMapper.toUser(registerDTO);
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        Role patientRole = roleRepository.findByName("PATIENT");
        user.setRoles(Set.of(patientRole));
        user.setIsActive(true);
        user = userRepository.save(user);

        Patient patient = new Patient();
        patient.setUser(user);
        patientRepository.save(patient);

        // Return user DTO

        return UserMapper.toUserDTO(user);
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
    
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
    
        if (!passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }
    
        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())) {
            throw new IllegalArgumentException("New passwords do not match");
        }
    
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }
}
