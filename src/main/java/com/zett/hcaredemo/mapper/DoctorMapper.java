package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.doctor.DoctorCreateDTO;
import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import com.zett.hcaredemo.dto.doctor.DoctorUpdateDTO;
import com.zett.hcaredemo.entity.Department;
import com.zett.hcaredemo.entity.Doctor;
import com.zett.hcaredemo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    // Phương thức để chuyển đổi từ Doctor sang DoctorDTO
    public DoctorDTO toDTO(Doctor doctor) {
        if (doctor == null) {
            return null;
        }

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setFullName(doctor.getFullName());
        doctorDTO.setDegree(doctor.getDegree());
        doctorDTO.setGender(doctor.getGender());
        doctorDTO.setPhoneNumber(doctor.getPhoneNumber());
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setExperience(doctor.getExperience());
        doctorDTO.setProfilePictureUrl(doctor.getProfilePictureUrl());

        // Lấy ID từ đối tượng User nếu tồn tại
        if (doctor.getUser() != null) {
            doctorDTO.setUserId(doctor.getUser().getId());
        }

        // Lấy ID từ đối tượng Department nếu tồn tại
        if (doctor.getDepartment() != null) {
            doctorDTO.setDepartmentId(doctor.getDepartment().getId());
        }

        return doctorDTO;
    }

    // Phương thức để chuyển đổi từ DoctorCreateDTO sang Doctor
    public Doctor toEntity(DoctorCreateDTO doctorCreateDTO) {
        if (doctorCreateDTO == null) {
            return null;
        }

        Doctor doctor = new Doctor();
        doctor.setFullName(doctorCreateDTO.getFullName());
        doctor.setDegree(doctorCreateDTO.getDegree());
        doctor.setGender(doctorCreateDTO.getGender());
        doctor.setPhoneNumber(doctorCreateDTO.getPhoneNumber());
        doctor.setEmail(doctorCreateDTO.getEmail());
        doctor.setExperience(doctorCreateDTO.getExperience());
        doctor.setProfilePictureUrl(doctorCreateDTO.getProfilePictureUrl());

        // Thiết lập user và department dựa trên ID trong DTO
        if (doctorCreateDTO.getUserId() != null) {
            User user = new User();
            user.setId(doctorCreateDTO.getUserId());
            doctor.setUser(user);
        }

        if (doctorCreateDTO.getDepartmentId() != null) {
            Department department = new Department();
            department.setId(doctorCreateDTO.getDepartmentId());
            doctor.setDepartment(department);
        }

        return doctor;
    }

    // Phương thức để cập nhật thông tin từ DoctorUpdateDTO vào Doctor
    public Doctor updateEntity(Doctor existingDoctor, DoctorUpdateDTO doctorUpdateDTO) {
        if (existingDoctor == null) {
            return null;
        }

        existingDoctor.setFullName(doctorUpdateDTO.getFullName());
        existingDoctor.setDegree(doctorUpdateDTO.getDegree());
        existingDoctor.setGender(doctorUpdateDTO.getGender());
        existingDoctor.setPhoneNumber(doctorUpdateDTO.getPhoneNumber());
        existingDoctor.setEmail(doctorUpdateDTO.getEmail());
        existingDoctor.setExperience(doctorUpdateDTO.getExperience());
        existingDoctor.setProfilePictureUrl(doctorUpdateDTO.getProfilePictureUrl());
        if(doctorUpdateDTO.getDepartmentId() != null){
            Department department = new Department();
            department.setId(doctorUpdateDTO.getDepartmentId());
            existingDoctor.setDepartment(department);
        }
        if(doctorUpdateDTO.getUserId() !=null){
            User user = new User();
            user.setId(doctorUpdateDTO.getUserId());
            existingDoctor.setUser(user);
        }
        return existingDoctor;
    }
}
