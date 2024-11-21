package com.zett.hcaredemo.dto.patient;

import com.zett.hcaredemo.dto.auth.UserDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PatientUpdateDTO {
    private UUID id;

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must be less than 100 characters")
    private String fullName;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Phone number is required")
    @Size(max = 15, message = "Phone number must be less than 15 characters")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must be less than 255 characters")
    private String address;

    private LocalDate dateOfBirth;
    private String emergencyContact;
    private String bloodType;
    private String allergies;
    private String profilePictureUrl;
    private String healthInsuranceNumber;
    private String ethnicity;
    private UserDTO user;
}