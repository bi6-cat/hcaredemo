package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.medicine.MedicalRecordDTO;
import com.zett.hcaredemo.entity.MedicalRecord;

public class MedicalRecordMapper {
    public static MedicalRecordDTO toDTO(MedicalRecord medicalRecord) {
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        medicalRecordDTO.setId(medicalRecord.getId());
        medicalRecordDTO.setDiagnosis(medicalRecord.getDiagnosis());
        medicalRecordDTO.setTreatmentPlan(medicalRecord.getTreatmentPlan());
        medicalRecordDTO.setNotes(medicalRecord.getNotes());
        medicalRecordDTO.setPrescription(PrescriptionMapper.toDTO(medicalRecord.getPrescription()));
        medicalRecordDTO.setPatient(PatientMapper.toDTO(medicalRecord.getPatient()));
        medicalRecordDTO.setDoctor(DoctorMapper.toDTO(medicalRecord.getDoctor()));

        return medicalRecordDTO;
    }

    public static MedicalRecord toEntity(MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setId(medicalRecordDTO.getId());
        medicalRecord.setDiagnosis(medicalRecordDTO.getDiagnosis());
        medicalRecord.setTreatmentPlan(medicalRecordDTO.getTreatmentPlan());
        medicalRecord.setNotes(medicalRecordDTO.getNotes());
        medicalRecord.setPrescription(PrescriptionMapper.toEntity(medicalRecordDTO.getPrescription()));
        medicalRecord.setPatient(PatientMapper.toEntity(medicalRecordDTO.getPatient()));
        medicalRecord.setDoctor(DoctorMapper.toEntity(medicalRecordDTO.getDoctor()));
        return medicalRecord;
    }
}
