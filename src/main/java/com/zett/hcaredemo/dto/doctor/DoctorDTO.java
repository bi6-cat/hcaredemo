package com.zett.hcaredemo.dto.doctor;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorDTO {
    private UUID id;
    private String fullName;
    private String degree;
    private String gender;
    private String phoneNumber;
    private String email;
    private String experience;
    private String profilePictureUrl;
    private Boolean isActive;
    private UUID userId;
    private UUID departmentId;
}