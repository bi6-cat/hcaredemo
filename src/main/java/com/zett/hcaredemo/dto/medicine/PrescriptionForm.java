package com.zett.hcaredemo.dto.medicine;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PrescriptionForm {
    private String notes;
    private List<MedicineRequest> medicineRequests = new ArrayList<>();
}