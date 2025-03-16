package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.department.DepartmentSVIndexDTO;
import com.zett.hcaredemo.mapper.DepartmentServiceMapper;
import com.zett.hcaredemo.repository.DepartmentServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepartmentSVServiceImpl implements DepartmentSVService {
    @Autowired
    private DepartmentServiceRepository departmentServiceRepository;
    @Override
    public Set<DepartmentSVIndexDTO> findByDepartmentId(UUID departmentId) {
        return departmentServiceRepository.findByDepartmentId(departmentId).stream()
                .map(DepartmentServiceMapper::toDTO)
                .collect(Collectors.toSet());
    }
}
