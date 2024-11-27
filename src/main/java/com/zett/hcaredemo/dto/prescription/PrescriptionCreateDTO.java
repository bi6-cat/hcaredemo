package com.zett.hcaredemo.dto.prescription;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class PrescriptionCreateDTO {
    private String notes;
    private UUID patientId;
    private UUID doctorId;
    private UUID healthCheckAppointmentId;
    private Set<UUID> prescriptionMedicineIds;
    private UUID medicalRecordId;
}
