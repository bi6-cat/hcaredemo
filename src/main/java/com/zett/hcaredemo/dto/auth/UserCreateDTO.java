package com.zett.hcaredemo.dto.auth;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO {
    @NotBlank(message = "Username is required")
    @Length(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Length(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    private String password;

    @NotBlank(message = "Email is required")
    @Length(min = 6, max = 50, message = "Email must be between 6 and 50 characters")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Length(min = 6, max = 50, message = "Email must be between 6 and 50 characters")
    private String phone;
}