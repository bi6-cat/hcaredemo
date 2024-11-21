package com.zett.hcaredemo.dto.prescription;

import com.zett.hcaredemo.dto.doctor.DoctorDTO;
import com.zett.hcaredemo.dto.heathcheckappointment.HealthCheckAppointmentDTO;
import com.zett.hcaredemo.dto.medicine.MedicalRecordDTO;
import com.zett.hcaredemo.dto.patient.PatientDTO;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class PrescriptionDTO {
    private UUID id;
    private String notes;
    private PatientDTO patient;
    private DoctorDTO doctor;
    private HealthCheckAppointmentDTO appointment;
    private Set<PrescriptionMedicineDTO> prescriptionMedicines;
    private MedicalRecordDTO medicalRecord;
}
