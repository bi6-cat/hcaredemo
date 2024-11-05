package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.patient.PatientCreateDTO;
import com.zett.hcaredemo.dto.patient.PatientDTO;
import com.zett.hcaredemo.dto.patient.PatientUpdateDTO;
import com.zett.hcaredemo.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO toDTO(Patient patient);

    Patient toEntity(PatientDTO patientDTO);

    Patient toEntity(PatientUpdateDTO patientUpdateDTO);

    Patient toEntity(PatientCreateDTO patientCreateDTO);
}
