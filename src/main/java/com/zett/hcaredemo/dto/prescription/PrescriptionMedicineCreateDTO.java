package com.zett.hcaredemo.dto.prescription;

import lombok.Data;

import java.util.UUID;

@Data
public class PrescriptionMedicineCreateDTO {
    private String dosage;
    private Integer quantity;
    private String note;
    private UUID prescriptionId;
    private UUID medicineId;
}
