package com.zett.hcaredemo.service;

import java.util.List;
import java.util.UUID;

import com.zett.hcaredemo.dto.medicine.MedicineRequest;
import com.zett.hcaredemo.entity.Prescription;

public interface PrescriptionService {
        Prescription createPrescription(UUID appointmentId, String notes, List<MedicineRequest> medicines);

        void deletePrescription(UUID id);

}
