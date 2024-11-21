package com.zett.hcaredemo.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private UUID id;
    @NotBlank(message = "Role name is required")
    private String name;
    private Set<UserDTO> users;
}
