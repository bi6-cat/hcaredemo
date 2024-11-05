package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.department.DepartmentCreateDTO;
import com.zett.hcaredemo.dto.department.DepartmentDTO;
import com.zett.hcaredemo.dto.department.DepartmentUpdateDTO;
import com.zett.hcaredemo.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    Page<DepartmentDTO> findAll(String keyword, Pageable pageable);

    List<DepartmentDTO> findAllDepartments();

    DepartmentDTO findById(UUID id);

    DepartmentDTO create(DepartmentCreateDTO departmentCreateDTO, UUID hospitalId);

    DepartmentDTO update(UUID id, DepartmentUpdateDTO departmentDTO);

    void delete(UUID id);

    Page<DepartmentDTO> findAllByHospitalId(UUID hospitalId, String keyword, Pageable pageable);
}
