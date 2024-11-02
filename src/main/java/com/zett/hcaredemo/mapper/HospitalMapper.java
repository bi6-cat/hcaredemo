package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.hospital.HospitalCreateDTO;
import com.zett.hcaredemo.dto.hospital.HospitalDTO;
import com.zett.hcaredemo.entity.Hospital;

public class HospitalMapper {

    public static HospitalDTO toDTO(Hospital hospital) {
        if (hospital == null) {
            return null;
        }

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
        // Map other fields as needed
        return hospitalDTO;
    }

    public static Hospital toEntity(HospitalDTO hospitalDTO) {
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
        // Map other fields as needed
        return hospital;
    }

    public static Hospital toEntity(HospitalCreateDTO hospitalCreateDTO) {
        if (hospitalCreateDTO == null) {
            return null;
        }

        Hospital hospital = new Hospital();
        hospital.setName(hospitalCreateDTO.getName());
        hospital.setAddress(hospitalCreateDTO.getAddress());
        hospital.setPhone(hospitalCreateDTO.getPhone());
        hospital.setEmail(hospitalCreateDTO.getEmail());
        hospital.setWebsite(hospitalCreateDTO.getWebsite());
        hospital.setDescription(hospitalCreateDTO.getDescription());
        // Set other fields as needed
        return hospital;
    }
}