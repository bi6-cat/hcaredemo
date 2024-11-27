package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.medicine.MedicineCreateDTO;
import com.zett.hcaredemo.dto.medicine.MedicineDTO;
import com.zett.hcaredemo.entity.Medicine;

import java.util.List;
import java.util.UUID;

public interface MedicineService {
    Medicine findById(UUID id);

    List<MedicineDTO> getAllMedicines();

    MedicineDTO create(MedicineCreateDTO medicineDTO);

    MedicineDTO update(UUID id, MedicineCreateDTO medicineCreateDTO);

    void delete(UUID id);

    MedicineDTO getMedicineById(UUID id);
}
