package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.department.DepartmentDTO;
import com.zett.hcaredemo.dto.hospital.HospitalCreateDTO;
import com.zett.hcaredemo.dto.hospital.HospitalDTO;
import com.zett.hcaredemo.entity.Hospital;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class HospitalMapper {
    public static HospitalDTO toDTO(Hospital hospital) {
        if (hospital == null) {
            return null;
        }
        Set<DepartmentDTO> departmentDTOs = hospital.getDepartments() != null ?
                hospital.getDepartments().stream()
                        .map(DepartmentMapper::toDTO)
                        .collect(Collectors.toSet()) :
                Collections.emptySet();

        HospitalDTO hospitalDTO = new HospitalDTO();
        hospitalDTO.setId(hospital.getId());
        hospitalDTO.setName(hospital.getName());
        hospitalDTO.setAddress(hospital.getAddress());
        hospitalDTO.setPhone(hospital.getPhone());
        hospitalDTO.setEmail(hospital.getEmail());
        hospitalDTO.setWebsite(hospital.getWebsite());
        hospitalDTO.setDescription(hospital.getDescription());
        hospitalDTO.setCreatedAt(hospital.getCreatedAt());
        hospitalDTO.setUpdatedAt(hospital.getUpdatedAt());
        hospitalDTO.setDepartments(departmentDTOs);
        return hospitalDTO;
    }

    public static Hospital toEntity(HospitalCreateDTO dto) {
        Hospital hospital = new Hospital();
        hospital.setName(dto.getName());
        hospital.setAddress(dto.getAddress());
        hospital.setPhone(dto.getPhone());
        hospital.setEmail(dto.getEmail());
        hospital.setWebsite(dto.getWebsite());
        hospital.setDescription(dto.getDescription());
        hospital.setDepartments(null);
        hospital.setHealthCheckAppointments(null);
        return hospital;
    }

    public Hospital toEntity(HospitalDTO hospitalDTO) {
        if (hospitalDTO == null) {
            return null;
        }

        Hospital hospital = new Hospital();
        hospital.setId(hospitalDTO.getId());
        hospital.setName(hospitalDTO.getName());
        hospital.setAddress(hospitalDTO.getAddress());
        hospital.setPhone(hospitalDTO.getPhone());
        hospital.setEmail(hospitalDTO.getEmail());
        hospital.setWebsite(hospitalDTO.getWebsite());
        hospital.setDescription(hospitalDTO.getDescription());
        hospital.setCreatedAt(hospitalDTO.getCreatedAt());
        hospital.setUpdatedAt(hospitalDTO.getUpdatedAt());
        if (hospitalDTO.getDepartments() != null) {
            hospital.setDepartments(hospitalDTO.getDepartments()
                    .stream()
                    .map(DepartmentMapper::toEntity)
                    .collect(Collectors.toSet()));
        } else {
            hospital.setDepartments(null);
        }
        return hospital;
    }
}