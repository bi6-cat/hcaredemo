package com.zett.hcaredemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

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

    @Column(name = "gender")
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

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "health_insurance_number", columnDefinition = "NVARCHAR(50)")
    private String healthInsuranceNumber;

    @Column(name ="ethnicity", columnDefinition = "NVARCHAR(50)")
    private String ethnicity;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

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
}

