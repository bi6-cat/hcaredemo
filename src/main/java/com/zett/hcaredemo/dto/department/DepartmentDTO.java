package com.zett.hcaredemo.dto.department;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DepartmentDTO {
    private UUID id;
    private String name;
    private String description;
    private String headOfDepartment;
    private String phone;
}
