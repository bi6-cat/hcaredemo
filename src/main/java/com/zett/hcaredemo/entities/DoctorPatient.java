package com.zett.hcaredemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor_patient")
public class DoctorPatient {

    @EmbeddedId
    private DoctorPatientId id;

    private LocalDateTime relationshipStartDate;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DoctorPatientId implements Serializable {
        private String doctorId;
        private String patientId;
    }
}
