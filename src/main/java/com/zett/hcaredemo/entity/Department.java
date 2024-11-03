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
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "head_of_department", columnDefinition = "VARCHAR(100)")
    private String headOfDepartment;

    @Column(name = "phone", columnDefinition = "VARCHAR(15)")
    private String phone;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "department")
    private Set<Doctor> doctors;

    @OneToMany(mappedBy = "department")
    private Set<DepartmentService> services;

    @OneToMany(mappedBy = "department")
    private Set<HealthCheckAppointment> healthCheckAppointments;

}