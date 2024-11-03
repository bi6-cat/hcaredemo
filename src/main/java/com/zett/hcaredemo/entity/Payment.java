package com.zett.hcaredemo.entity;

import java.util.UUID;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private Double amount;
    
    @Column(name = "payment_method", nullable = false, columnDefinition = "VARCHAR(50)")
    private String paymentMethod;
    
    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}