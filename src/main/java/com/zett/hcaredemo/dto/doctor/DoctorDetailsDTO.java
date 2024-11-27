package com.zett.hcaredemo.dto.doctor;

import lombok.Data;

import java.util.UUID;

@Data
public class DoctorDetailsDTO {
    private UUID id;
    private String fullName;
    private String degree;
    private String gender;
    private String phoneNumber;
    private String email;
    private String experience;
    private String profilePictureUrl;
    private String department;
}
