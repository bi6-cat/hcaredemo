package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.prescription.PrescriptionCreateDTO;
import com.zett.hcaredemo.dto.prescription.PrescriptionDTO;
import com.zett.hcaredemo.entity.Prescription;

import java.util.stream.Collectors;

public class PrescriptionMapper {
    public static PrescriptionDTO toDTO(Prescription prescription) {
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
        prescriptionDTO.setId(prescription.getId());
        prescriptionDTO.setNotes(prescription.getNotes());
        prescriptionDTO.setPatient(PatientMapper.toDTO(prescription.getPatient()));
        prescriptionDTO.setDoctor(DoctorMapper.toDTO(prescription.getDoctor()));
        prescriptionDTO.setAppointment(HealthCheckAppointmentMapper.toDTO(prescription.getAppointment()));
        prescriptionDTO.setPrescriptionMedicines(prescription.getPrescriptionMedicines().stream().map(PrescriptionMedicineMapper::toDTO).collect(Collectors.toSet()));
        prescriptionDTO.setMedicalRecord(MedicalRecordMapper.toDTO(prescription.getMedicalRecord()));
        return prescriptionDTO;
    }

    public static Prescription toEntity(PrescriptionDTO prescriptionDTO) {
        Prescription prescription = new Prescription();
        prescription.setId(prescriptionDTO.getId());
        prescription.setNotes(prescriptionDTO.getNotes());
        prescription.setPatient(PatientMapper.toEntity(prescriptionDTO.getPatient()));
        prescription.setDoctor(DoctorMapper.toEntity(prescriptionDTO.getDoctor()));
        prescription.setAppointment(HealthCheckAppointmentMapper.toEntity(prescriptionDTO.getAppointment()));
        prescription.setPrescriptionMedicines(prescriptionDTO.getPrescriptionMedicines().stream().map(PrescriptionMedicineMapper::toEntity).collect(Collectors.toSet()));
        prescription.setMedicalRecord(MedicalRecordMapper.toEntity(prescriptionDTO.getMedicalRecord()));
        return prescription;
    }
//    public static Prescription toEntity(PrescriptionCreateDTO prescriptionDTO) {
//        Prescription prescription = new Prescription();
//        prescription.setNotes(prescriptionDTO.getNotes());
//        prescription.setPatient(PatientMapper.toEntity(prescriptionDTO.getPatient()));
//        prescription.setDoctor(DoctorMapper.toEntity(prescriptionDTO.getDoctor()));
//        return prescription;
//    }
//    public static void toUpdateEntity(Prescription prescription, PrescriptionCreateDTO prescriptionDTO) {
//        prescription.setNotes(prescriptionDTO.getNotes());
//        prescription.setPatient(PatientMapper.toEntity(prescriptionDTO.getPatient()));
//        prescription.setDoctor(DoctorMapper.toEntity(prescriptionDTO.getDoctor()));
//        prescription.setAppointment(HealthCheckAppointmentMapper.toEntity(prescriptionDTO.getAppointment()));
//        prescription.setPrescriptionMedicines(prescriptionDTO.getPrescriptionMedicines().stream().map(PrescriptionMedicineMapper::toEntity).collect(Collectors.toSet()));
//        prescription.setMedicalRecord(MedicalRecordMapper.toEntity(prescriptionDTO.getMedicalRecord()));
//    }
}
