package com.zett.hcaredemo.dto.department;

import lombok.Data;

import java.util.UUID;

@Data
public class DepartmentSVIndexDTO {
    private UUID id;
    private String name;
    private String description;
    private Double price;
}
