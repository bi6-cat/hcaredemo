package com.zett.hcaredemo.entity;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
