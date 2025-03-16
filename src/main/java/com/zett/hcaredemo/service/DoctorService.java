package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.auth.AccountDTO;
import com.zett.hcaredemo.dto.doctor.DoctorCreateDTO;
import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import com.zett.hcaredemo.dto.doctor.DoctorIndexDTO;
import com.zett.hcaredemo.dto.doctor.DoctorUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface DoctorService {
    List<DoctorDTO> findAll();

    List<DoctorDTO> findAll(String keyword);

    Page<DoctorIndexDTO> findAll(String keyword, Pageable pageable);

    DoctorDTO findById(UUID id);

    DoctorDTO getDoctorByUsername(String name);

    AccountDTO create(DoctorCreateDTO doctorCreateDTO);

    DoctorDTO update(UUID id, DoctorUpdateDTO doctorDTO);

    void delete(UUID id);

    long countDoctor();

    Set<DoctorIndexDTO> getDoctorsByDepartment(UUID departmentId);

    DoctorDTO findByUser();

}