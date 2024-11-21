package com.zett.hcaredemo.mapper;

import com.zett.hcaredemo.dto.medicine.MedicineCreateDTO;
import com.zett.hcaredemo.dto.medicine.MedicineDTO;
import com.zett.hcaredemo.entity.Medicine;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class MedicineMapper {
    public static MedicineDTO toDTO(Medicine medicine) {
        MedicineDTO medicineDTO = new MedicineDTO();
        medicineDTO.setId(medicine.getId());
        medicineDTO.setName(medicine.getName());
        medicineDTO.setDescription(medicine.getDescription());
        medicineDTO.setPrice(medicine.getPrice());
        medicineDTO.setQuantity(medicine.getQuantity());
        medicineDTO.setPrescriptionMedicines(
                Optional.ofNullable(medicine.getPrescriptionMedicines()) // Kiểm tra nếu null
                        .orElse(Collections.emptySet()) // Nếu null thì trả về một Set rỗng
                        .stream()
                        .map(PrescriptionMedicineMapper::toDTO)
                        .collect(Collectors.toSet())
        );

        return medicineDTO;
    }
    public static Medicine toEntity(MedicineCreateDTO medicineDTO) {
        Medicine medicine = new Medicine();
        medicine.setName(medicineDTO.getName());
        medicine.setDescription(medicineDTO.getDescription());
        medicine.setPrice(medicineDTO.getPrice());
        medicine.setQuantity(medicineDTO.getQuantity());
        medicine.setPrescriptionMedicines(null);
        return medicine;
    }

    public static Medicine toEntity(MedicineDTO medicineDTO) {
        Medicine medicine = new Medicine();
        medicine.setId(medicineDTO.getId());
        medicine.setName(medicineDTO.getName());
        medicine.setDescription(medicineDTO.getDescription());
        medicine.setPrice(medicineDTO.getPrice());
        medicine.setQuantity(medicineDTO.getQuantity());
        medicine.setPrescriptionMedicines(medicineDTO.getPrescriptionMedicines().stream().map(PrescriptionMedicineMapper::toEntity).collect(Collectors.toSet()));
        return medicine;
    }
    public static void toUpdateEntity(Medicine medicine, MedicineCreateDTO medicineDTO) {
        medicine.setName(medicineDTO.getName());
        medicine.setDescription(medicineDTO.getDescription());
        medicine.setPrice(medicineDTO.getPrice());
        medicine.setQuantity(medicineDTO.getQuantity());
    }
}
