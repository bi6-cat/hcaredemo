package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.department.DepartmentCreateDTO;
import com.zett.hcaredemo.dto.department.DepartmentDTO;
import com.zett.hcaredemo.dto.department.DepartmentUpdateDTO;
import com.zett.hcaredemo.entity.Department;
import com.zett.hcaredemo.entity.Hospital;
import com.zett.hcaredemo.mapper.DepartmentMapper;
import com.zett.hcaredemo.repository.DepartmentRepository;
import com.zett.hcaredemo.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final HospitalRepository hospitalRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper, HospitalRepository hospitalRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Page<DepartmentDTO> findAll(String keyword, Pageable pageable) {
        return departmentRepository.findAllByKeyword(keyword, pageable)
                .map(departmentMapper::toDTO);
    }
    @Override
    public List<DepartmentDTO> findAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDTO)
                .toList();
    }

    @Override
    public DepartmentDTO findById(UUID id) {
        Department department = departmentRepository.findById(id).orElseThrow();
        return departmentMapper.toDTO(department);
    }

    @Override
    public DepartmentDTO create(DepartmentCreateDTO departmentDTO, UUID hospitalId) {
        Hospital hospital= hospitalRepository.findById(hospitalId).orElseThrow();
        Department department = departmentMapper.toEntity(departmentDTO, hospital);
        department = departmentRepository.save(department);
        return departmentMapper.toDTO(department);
    }

    @Override
    public DepartmentDTO update(UUID id, DepartmentUpdateDTO departmentDTO) {
        Department existingDepartment = departmentRepository.findById(id).orElseThrow();
        existingDepartment = departmentMapper.updateEntity(existingDepartment, departmentDTO);
        departmentRepository.save(existingDepartment);
        return departmentMapper.toDTO(existingDepartment);
    }

    @Override
    public void delete(UUID id) {
        departmentRepository.deleteById(id);
    }
    @Override
    public Page<DepartmentDTO> findAllByHospitalId(UUID hospitalId, String keyword, Pageable pageable) {
        return departmentRepository.findByHospitalIdAndKeyword(hospitalId, keyword, pageable)
                .map(departmentMapper::toDTO);
    }
}