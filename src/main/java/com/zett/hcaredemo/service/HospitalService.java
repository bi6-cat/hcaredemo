package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.department.DepartmentDTO;
import com.zett.hcaredemo.dto.hospital.HospitalCreateDTO;
import com.zett.hcaredemo.dto.hospital.HospitalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface HospitalService {
    List<DepartmentDTO> getAllDepartments();

    List<HospitalDTO> findAll();

    Page<HospitalDTO> findAll(Pageable pageable);

    Page<HospitalDTO> searchHospitals(String keyword, Pageable pageable);

    HospitalDTO findById(UUID id);

    HospitalDTO create(HospitalCreateDTO hospitalCreateDTO);

    HospitalDTO update(UUID id, HospitalDTO hospitalDTO);

    void delete(UUID id);
}