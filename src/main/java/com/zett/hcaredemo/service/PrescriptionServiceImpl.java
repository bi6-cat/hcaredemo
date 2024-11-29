package com.zett.hcaredemo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zett.hcaredemo.dto.medicine.MedicineRequest;
import com.zett.hcaredemo.entity.Doctor;
import com.zett.hcaredemo.entity.HealthCheckAppointment;
import com.zett.hcaredemo.entity.Medicine;
import com.zett.hcaredemo.entity.Patient;
import com.zett.hcaredemo.entity.Prescription;
import com.zett.hcaredemo.entity.PrescriptionMedicine;
import com.zett.hcaredemo.entity.User;
import com.zett.hcaredemo.exception.ResourceNotFoundException;
import com.zett.hcaredemo.repository.DoctorRepository;
import com.zett.hcaredemo.repository.HealthCheckAppointmentRepository;
import com.zett.hcaredemo.repository.MedicineRepository;
import com.zett.hcaredemo.repository.PatientRepository;
import com.zett.hcaredemo.repository.PrescriptionMedicineRepository;
import com.zett.hcaredemo.repository.PrescriptionRepository;
import com.zett.hcaredemo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final HealthCheckAppointmentRepository healthCheckAppointmentRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository,
                                   PrescriptionMedicineRepository prescriptionMedicineRepository,
                                   MedicineRepository medicineRepository,
                                   UserRepository userRepository,
                                   DoctorRepository doctorRepository,
                                   HealthCheckAppointmentRepository heathCheckAppointmentRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionMedicineRepository = prescriptionMedicineRepository;
        this.medicineRepository = medicineRepository;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.healthCheckAppointmentRepository = heathCheckAppointmentRepository;
    }

    @Override
    @Transactional
    public Prescription createPrescription(UUID appointmentId, String notes, List<MedicineRequest> medicines) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        Doctor doctor = doctorRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found for user with id " + user.getId()));
        HealthCheckAppointment appointment = healthCheckAppointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id " + appointmentId));
        
        Prescription prescription = new Prescription();
        prescription.setDoctor(doctor); // Assumes Doctor is fetched elsewhere
        prescription.setAppointment(appointment); // Assumes Appointment is fetched elsewhere
        prescription.setNotes(notes);

        prescription = prescriptionRepository.save(prescription);

        // Add Prescription Medicines
        for (MedicineRequest medicineRequest : medicines) {
            Medicine medicine = medicineRepository.findById(medicineRequest.getMedicineId())
                    .orElseThrow(() -> new IllegalArgumentException("Medicine not found"));

            PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine();
            prescriptionMedicine.setPrescription(prescription);
            prescriptionMedicine.setMedicine(medicine);
            prescriptionMedicine.setDosage(medicineRequest.getDosage());
            prescriptionMedicine.setQuantity(medicineRequest.getQuantity());
            prescriptionMedicine.setNote(medicineRequest.getNote());

            prescriptionMedicineRepository.save(prescriptionMedicine);
        }

        return prescription;
    }

    @Override
    public void deletePrescription(UUID id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with id " + id));
        prescriptionRepository.delete(prescription);
    }
}