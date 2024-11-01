package com.zett.hcaredemo.entity;

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
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "full_name", columnDefinition = "NVARCHAR(100)")
    private String fullName;

    @Column(name = "degree", columnDefinition = "NVARCHAR(10)")
    private String degree;

    @Column(name = "gender", columnDefinition = "ENUM('MALE', 'FEMALE', 'OTHER')")
    private String gender;

    @Column(name = "phone_number", columnDefinition = "NVARCHAR(20)")
    private String phoneNumber;

    @Column(name = "email", columnDefinition = "NVARCHAR(100)")
    private String email;

    @Column(name = "experience", columnDefinition = "NVARCHAR(1000)")
    private String experience;

    @Column(name = "profile_picture_url", columnDefinition = "NVARCHAR(255)")
    private String profilePictureUrl;

    @OneToOne(mappedBy = "doctor")
    private Patient patient;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "doctor")
    private Set<DoctorSchedule> schedules;

    @OneToMany(mappedBy = "doctor")
    private Set<HealthCheckAppointment> healthCheckAppointments;

    @OneToMany(mappedBy = "doctor")
    private Set<Prescription> prescriptions;

    @OneToMany(mappedBy = "doctor")
    private Set<MedicalRecord> medicalRecords;
}