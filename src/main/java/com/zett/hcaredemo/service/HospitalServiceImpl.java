package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.department.DepartmentDTO;
import com.zett.hcaredemo.dto.hospital.HospitalCreateDTO;
import com.zett.hcaredemo.dto.hospital.HospitalDTO;
import com.zett.hcaredemo.entity.Department;
import com.zett.hcaredemo.entity.Hospital;
import com.zett.hcaredemo.mapper.HospitalMapper;
import com.zett.hcaredemo.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final DepartmentService departmentService;

    public HospitalServiceImpl(HospitalRepository hospitalRepository, DepartmentService departmentService) {
        this.hospitalRepository = hospitalRepository;
        this.departmentService = departmentService;
    }


    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.findAllDepartments();
    }

    @Override
    public List<HospitalDTO> findAll() {
        return hospitalRepository.findAll().stream()
                .map(HospitalMapper::toDTO)
                .toList();
    }

    @Override
    public Page<HospitalDTO> findAll(Pageable pageable) {
        return hospitalRepository.findAll(pageable)
                .map(HospitalMapper::toDTO);
    }

    @Override
    public Page<HospitalDTO> searchHospitals(String keyword, Pageable pageable) {
        return hospitalRepository.findByNameContainingIgnoreCase(keyword, pageable)
                .map(HospitalMapper::toDTO);
    }

    @Override
    public HospitalDTO findById(UUID id) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        return hospitalOptional.map(HospitalMapper::toDTO).orElse(null);
    }

    @Override
    public HospitalDTO create(HospitalCreateDTO hospitalCreateDTO) {
        Hospital hospital = HospitalMapper.toEntity(hospitalCreateDTO);
        hospitalRepository.save(hospital);
        return HospitalMapper.toDTO(hospital);
    }

    @Override
    public HospitalDTO update(UUID id, HospitalDTO hospitalDTO) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        if (hospitalOptional.isPresent()) {
            Hospital hospital = hospitalOptional.get();
            hospital.setName(hospitalDTO.getName());
            hospital.setAddress(hospitalDTO.getAddress());
            hospital.setPhone(hospitalDTO.getPhone());
            hospital.setEmail(hospitalDTO.getEmail());
            hospital.setWebsite(hospitalDTO.getWebsite());
            hospital.setDescription(hospitalDTO.getDescription());
            Hospital updatedHospital = hospitalRepository.save(hospital);
            return HospitalMapper.toDTO(updatedHospital);
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        hospitalRepository.deleteById(id);
    }

    @Override
    public long countHospital() {
        return hospitalRepository.count();
    }
}