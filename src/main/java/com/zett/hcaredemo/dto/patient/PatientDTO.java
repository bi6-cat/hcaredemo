package com.zett.hcaredemo.dto.patient;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PatientDTO {
    private UUID id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;
    private String emergencyContact;
    private String bloodType;
    private String allergies;
    private String profilePictureUrl;
    private UUID userId;
}
