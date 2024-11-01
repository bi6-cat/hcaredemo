package com.zett.hcaredemo.entity;

import java.util.Set;
import java.util.UUID;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prescriptions")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column(name = "notes", columnDefinition = "VARCHAR(255)")
    private String notes;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private HealthCheckAppointment appointment;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private Set<PrescriptionMedicine> prescriptionMedicines;

    @OneToOne(mappedBy = "prescription")
    private MedicalRecord medicalRecord;

}