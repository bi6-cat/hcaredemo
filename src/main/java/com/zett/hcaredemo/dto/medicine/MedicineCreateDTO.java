package com.zett.hcaredemo.dto.medicine;

import lombok.Data;

@Data
public class MedicineCreateDTO {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}

