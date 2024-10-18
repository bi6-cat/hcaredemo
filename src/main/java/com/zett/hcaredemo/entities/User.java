package com.zett.hcaredemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String description;
    private String facilities;
    private String city;
    private String postalCode;
    private String website;
    private String specialties;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
