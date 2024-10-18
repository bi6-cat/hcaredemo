package com.zett.hcaredemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hospital_doctor")
public class HospitalDoctor {

    @EmbeddedId
    private HospitalDoctorId id;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HospitalDoctorId implements Serializable {
        private String hospitalId;
        private String doctorId;
    }
}
