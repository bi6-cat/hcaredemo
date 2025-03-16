package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.department.DepartmentSVIndexDTO;

import java.util.Set;
import java.util.UUID;

public interface DepartmentSVService {
    Set<DepartmentSVIndexDTO> findByDepartmentId(UUID departmentId);
}
