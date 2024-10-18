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
@Table(name = "hospital_department")
public class HospitalDepartment {

    @EmbeddedId
    private HospitalDepartmentId id;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HospitalDepartmentId implements Serializable {
        private String hospitalId;
        private String departmentId;
    }
}
