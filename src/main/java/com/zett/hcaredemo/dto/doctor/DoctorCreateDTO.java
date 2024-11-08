package com.zett.hcaredemo.dto.doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DoctorCreateDTO {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Degree is required")
    private String degree;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    private String email;

    private String experience;

    private String profilePictureUrl;

    @NotNull(message = "Department is required")
    private UUID departmentId;

    private UUID userId;

}