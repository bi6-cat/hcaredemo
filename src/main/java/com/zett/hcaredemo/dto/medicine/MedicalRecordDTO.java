package com.zett.hcaredemo.dto.medicine;

import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import com.zett.hcaredemo.dto.patient.PatientDTO;
import com.zett.hcaredemo.dto.prescription.PrescriptionDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class MedicalRecordDTO {
    private UUID id;
    private String diagnosis;
    private String treatmentPlan;
    private String notes;
    private PrescriptionDTO prescription;
    private PatientDTO patient;
    private DoctorDTO doctor;
}