package com.zett.hcaredemo.entity;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")
    private String email;

    @Column(name = "phone", nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")
    private String phone;

    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    private Patient patient;

    @OneToOne(mappedBy = "user")
    private Doctor doctor;

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications;
}
