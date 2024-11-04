package com.zett.hcaredemo.dto.auth;

import java.util.UUID;
import java.util.List;

import jakarta.persistence.Column;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;

    @Column(name = "username", nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")
    private String username;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")
    private String email;

    @Column(name = "phone", nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")
    private String phone;

    public List<UUID> roleIds;

    private List<RoleDTO> roles;
}
