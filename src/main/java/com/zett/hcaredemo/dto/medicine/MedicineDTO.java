package com.zett.hcaredemo.dto.medicine;

import com.zett.hcaredemo.dto.prescription.PrescriptionMedicineDTO;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class MedicineDTO {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Set<PrescriptionMedicineDTO> prescriptionMedicines;

    public MedicineDTO() {}
    
    public MedicineDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
