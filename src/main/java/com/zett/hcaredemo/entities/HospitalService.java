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
@Table(name = "hospital_service")
public class HospitalService {

    @EmbeddedId
    private HospitalServiceId id;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HospitalServiceId implements Serializable {
        private String hospitalId;
        private String serviceId;
    }
}
