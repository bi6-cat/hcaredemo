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
@Table(name = "department_doctor")
public class DepartmentDoctor {

    @EmbeddedId
    private DepartmentDoctorId id;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DepartmentDoctorId implements Serializable {
        private String departmentId;
        private String doctorId;
    }
}
