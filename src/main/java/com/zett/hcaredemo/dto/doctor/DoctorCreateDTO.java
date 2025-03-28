package com.zett.hcaredemo.dto.doctor;

import lombok.Data;
import java.util.Set;
import java.util.UUID;

@Data
public class DoctorCreateDTO {
    private String fullName;
    private String degree;
    private String gender;
    private String phoneNumber;
    private String email;
    private String experience;
    private String profilePictureUrl;
    private UUID departmentId;
    private Set<UUID> doctorScheduleIds;
    
    // Explicit implementations to avoid Lombok issues
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
    
    public String getDegree() {
        return degree;
    }
    
    public void setDegree(String degree) {
        this.degree = degree;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getExperience() {
        return experience;
    }
    
    public void setExperience(String experience) {
        this.experience = experience;
    }
    
    public UUID getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(UUID departmentId) {
        this.departmentId = departmentId;
    }
    
    public Set<UUID> getDoctorScheduleIds() {
        return doctorScheduleIds;
    }
    
    public void setDoctorScheduleIds(Set<UUID> doctorScheduleIds) {
        this.doctorScheduleIds = doctorScheduleIds;
    }
}