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
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "room_number", nullable = false, columnDefinition = "VARCHAR(50)")
    private String roomNumber;

    @Column(name = "type", nullable = false, columnDefinition = "ENUM('EXAMINATION', 'TESTING', 'WAITING', 'VACCINATION')")
    private String type;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "room")
    private Set<HealthCheckAppointment> healthCheckAppointments;

    @OneToOne(mappedBy = "room")
    private LabTest labTest;
}