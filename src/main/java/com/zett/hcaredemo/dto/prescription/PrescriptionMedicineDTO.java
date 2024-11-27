package com.zett.hcaredemo.dto.prescription;

import com.zett.hcaredemo.dto.medicine.MedicineDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class PrescriptionMedicineDTO {
    private UUID id;
    private String dosage;
    private Integer quantity;
    private String note;
    private PrescriptionDTO prescription;
    private MedicineDTO medicine;
}
