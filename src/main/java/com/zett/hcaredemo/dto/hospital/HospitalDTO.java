package com.zett.hcaredemo.dto.hospital;

import com.zett.hcaredemo.dto.department.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalDTO {
    private UUID id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<DepartmentDTO> departments;
}
