package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.prescription.PrescriptionMedicineDTO;
import com.zett.hcaredemo.entity.PrescriptionMedicine;

public class PrescriptionMedicineMapper {
    public static PrescriptionMedicineDTO toDTO(PrescriptionMedicine prescriptionMedicine) {
        PrescriptionMedicineDTO prescriptionMedicineDTO = new PrescriptionMedicineDTO();
        prescriptionMedicineDTO.setDosage(prescriptionMedicine.getDosage());
        prescriptionMedicineDTO.setQuantity(prescriptionMedicine.getQuantity());
        prescriptionMedicineDTO.setNote(prescriptionMedicine.getNote());
        prescriptionMedicineDTO.setPrescription(PrescriptionMapper.toDTO(prescriptionMedicine.getPrescription()));
        prescriptionMedicineDTO.setMedicine(MedicineMapper.toDTO(prescriptionMedicine.getMedicine()));
        return prescriptionMedicineDTO;
    }

    public static PrescriptionMedicine toEntity(PrescriptionMedicineDTO prescriptionMedicineDTO) {
        PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine();
        prescriptionMedicine.setDosage(prescriptionMedicineDTO.getDosage());
        prescriptionMedicine.setQuantity(prescriptionMedicineDTO.getQuantity());
        prescriptionMedicine.setNote(prescriptionMedicineDTO.getNote());
        prescriptionMedicine.setPrescription(PrescriptionMapper.toEntity(prescriptionMedicineDTO.getPrescription()));
        prescriptionMedicine.setMedicine(MedicineMapper.toEntity(prescriptionMedicineDTO.getMedicine()));
        return prescriptionMedicine;
    }
}
