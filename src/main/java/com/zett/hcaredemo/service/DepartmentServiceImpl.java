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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, HospitalRepository hospitalRepository) {
        this.departmentRepository = departmentRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public DepartmentDTO createByAdmin(DepartmentCreateDTO departmentCreateDTO) {
        Department department = new Department();
        department.setName(departmentCreateDTO.getName());
        department.setDescription(departmentCreateDTO.getDescription());
        department.setHeadOfDepartment(departmentCreateDTO.getHeadOfDepartment());
        department.setPhone(departmentCreateDTO.getPhone());
        department = departmentRepository.save(department);
        return DepartmentMapper.toDTO(department);
    }

    @Override
    public Set<Department> findAllByIds(Set<UUID> ids) {
        return new HashSet<>(departmentRepository.findAllById(ids));
    }

    @Override
    public Page<DepartmentDTO> findAll(String keyword, Pageable pageable) {
        return departmentRepository.findAllByKeyword(keyword, pageable)
                .map(DepartmentMapper::toDTO);
    }

    @Override
    public List<DepartmentDTO> findAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(DepartmentMapper::toDTO)
                .toList();
    }

    @Override
    public DepartmentDTO findById(UUID id) {
        Department department = departmentRepository.findById(id).orElseThrow();
        return DepartmentMapper.toDTO(department);
    }

    @Override
    public Department findByIdEntity(UUID id) {
        return departmentRepository.findById(id).orElseThrow();
    }

    @Override
    public DepartmentDTO create(DepartmentCreateDTO departmentDTO, UUID hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow();
        Department department = DepartmentMapper.toEntity(departmentDTO, hospital);
        department = departmentRepository.save(department);
        return DepartmentMapper.toDTO(department);
    }

    @Override
    public DepartmentDTO update(UUID id, DepartmentUpdateDTO departmentDTO) {
        Department existingDepartment = departmentRepository.findById(id).orElseThrow();
        existingDepartment = DepartmentMapper.updateEntity(existingDepartment, departmentDTO);
        departmentRepository.save(existingDepartment);
        return DepartmentMapper.toDTO(existingDepartment);
    }

    @Override
    public void delete(UUID id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Page<DepartmentDTO> findAllByHospitalId(UUID hospitalId, String keyword, Pageable pageable) {
        return departmentRepository.findByHospitalIdAndKeyword(hospitalId, keyword, pageable)
                .map(DepartmentMapper::toDTO);
    }

    @Override
    public Page<DepartmentDTO> searchDepartments(String keyword, Pageable pageable) {
        return departmentRepository.findByNameContainingIgnoreCase(keyword, pageable)
                .map(DepartmentMapper::toDTO);
    }

    @Override
    public long countDepartment() {
        return departmentRepository.count();
    }
}
