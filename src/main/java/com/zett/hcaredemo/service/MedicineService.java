package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.medicine.MedicineCreateDTO;
import com.zett.hcaredemo.dto.medicine.MedicineDTO;
import com.zett.hcaredemo.entity.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface MedicineService {
    Page<MedicineDTO> searchMedicine(String keyword, Pageable pageable);

    Medicine findById(UUID id);

    List<MedicineDTO> getAllMedicines();

    MedicineDTO create(MedicineCreateDTO medicineDTO);

    MedicineDTO update(UUID id, MedicineCreateDTO medicineCreateDTO);

    void delete(UUID id);

    MedicineDTO getMedicineById(UUID id);

    long countMedicine();

}
