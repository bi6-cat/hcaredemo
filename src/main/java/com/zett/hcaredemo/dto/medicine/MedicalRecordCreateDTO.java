package com.zett.hcaredemo.dto.medicine;

import lombok.Data;

import java.util.UUID;

@Data
public class MedicalRecordCreateDTO {
    private String diagnosis;
    private String treatmentPlan;
    private String notes;
    private UUID prescriptionId;
    private UUID patientId;
    private UUID doctorId;
}
