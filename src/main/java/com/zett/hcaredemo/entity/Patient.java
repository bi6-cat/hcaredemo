package com.zett.hcaredemo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "full_name", columnDefinition = "NVARCHAR(100)")
    private String fullName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender", columnDefinition = "ENUM('MALE', 'FEMALE', 'OTHER')")
    private String gender;

    @Column(name = "address", columnDefinition = "NVARCHAR(255)")
    private String address;

    @Column(name = "phone_number", columnDefinition = "NVARCHAR(20)")
    private String phoneNumber;

    @Column(name = "emergency_contact", columnDefinition = "NVARCHAR(20)")
    private String emergencyContact;

    @Column(name = "blood_type", columnDefinition = "NVARCHAR(10)")
    private String bloodType;

    @Column(name = "allergies", columnDefinition = "NVARCHAR(1000)")
    private String allergies;

    @Column(name = "profile_picture_url", columnDefinition = "NVARCHAR(255)")
    private String profilePictureUrl;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "patient")
    private Set<HealthCheckAppointment> healthCheckAppointments;

    @OneToMany(mappedBy = "patient")
    private Set<Prescription> prescriptions;

    @OneToMany(mappedBy = "patient")
    private Set<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "patient")
    private Set<LabTestAppointment> labTestAppointments;

    @OneToMany(mappedBy = "patient")
    private Set<Payment> payments;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}