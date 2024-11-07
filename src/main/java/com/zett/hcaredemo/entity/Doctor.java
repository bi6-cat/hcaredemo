package com.zett.hcaredemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

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

    @Column(name = "gender", columnDefinition = "NVARCHAR(10)")
    private String gender;

    @Column(name = "phone_number", columnDefinition = "NVARCHAR(20)")
    private String phoneNumber;

    @Column(name = "email", columnDefinition = "NVARCHAR(100)")
    private String email;

    @Column(name = "experience", columnDefinition = "NVARCHAR(1000)")
    private String experience;

    @Column(name = "profile_picture_url", columnDefinition = "NVARCHAR(255)")
    private String profilePictureUrl;

    @Column(name = "rating", columnDefinition = "DECIMAL(2, 1)")
    private Double rating;

    @Column(name = "review_count")
    private Integer reviewCount;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "doctor")
    private Set<DoctorSchedule> doctorSchedules;

    @OneToMany(mappedBy = "doctor")
    private Set<HealthCheckAppointment> healthCheckAppointments;

    @OneToMany(mappedBy = "doctor")
    private Set<Prescription> prescriptions;

    @OneToMany(mappedBy = "doctor")
    private Set<MedicalRecord> medicalRecords;
}