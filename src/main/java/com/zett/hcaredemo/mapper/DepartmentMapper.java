package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.department.DepartmentCreateDTO;
import com.zett.hcaredemo.dto.department.DepartmentDTO;
import com.zett.hcaredemo.dto.department.DepartmentUpdateDTO;
import com.zett.hcaredemo.entity.Department;
import com.zett.hcaredemo.entity.Hospital;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DepartmentMapper {

    public static Department toEntity(DepartmentCreateDTO dto, Hospital hospital) {
        if (dto == null) {
            return null;
        }
        Department department = new Department();
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        department.setHeadOfDepartment(dto.getHeadOfDepartment());
        department.setPhone(dto.getPhone());
        department.setHospital(hospital);
        return department;
    }

    public static Department toEntity(DepartmentDTO department) {
        if (department == null) {
            return null;
        }
        Department departmentEntity = new Department();
        departmentEntity.setId(department.getId());
        departmentEntity.setName(department.getName());
        departmentEntity.setDescription(department.getDescription());
        departmentEntity.setHeadOfDepartment(department.getHeadOfDepartment());
        departmentEntity.setPhone(department.getPhone());
        return departmentEntity;
    }

    public static DepartmentDTO toDTO(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setDescription(department.getDescription());
        dto.setHeadOfDepartment(department.getHeadOfDepartment());
        dto.setPhone(department.getPhone());
        return dto;
    }

    public static Department updateEntity(Department existingDepartment, DepartmentUpdateDTO dto) {
        if (existingDepartment == null) {
            return null;
        }
        existingDepartment.setName(dto.getName());
        existingDepartment.setDescription(dto.getDescription());
        existingDepartment.setHeadOfDepartment(dto.getHeadOfDepartment());
        existingDepartment.setPhone(dto.getPhone());
        return existingDepartment;
    }
}
