package com.zett.hcaredemo.dto.hospital;

import com.zett.hcaredemo.dto.department.DepartmentDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalCreateDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Email is required")
    private String email;

    private String website;
    
    private String description;

    private Set<UUID> departmentIds;
}
