package com.zett.hcaredemo.dto.medicine;

import java.util.UUID;

import lombok.*;

@Getter
@Setter
public class MedicineRequest {
    private UUID medicineId;
    private String dosage;
    private Integer quantity;
    private String note;
}