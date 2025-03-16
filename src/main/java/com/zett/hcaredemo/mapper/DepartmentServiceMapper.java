package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.department.DepartmentSVIndexDTO;
import com.zett.hcaredemo.entity.DepartmentService;

public class DepartmentServiceMapper {
    public static DepartmentSVIndexDTO toDTO(DepartmentService departmentService) {
        DepartmentSVIndexDTO departmentServiceDTO = new DepartmentSVIndexDTO();
        departmentServiceDTO.setId(departmentService.getId());
        departmentServiceDTO.setName(departmentService.getName());
        departmentServiceDTO.setDescription(departmentService.getDescription());
        departmentServiceDTO.setPrice(departmentService.getPrice());
        return departmentServiceDTO;
    }
}
