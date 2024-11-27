package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.medicine.MedicineCreateDTO;
import com.zett.hcaredemo.dto.medicine.MedicineDTO;
import com.zett.hcaredemo.entity.Medicine;
import com.zett.hcaredemo.exception.ResourceNotFoundException;
import com.zett.hcaredemo.mapper.MedicineMapper;
import com.zett.hcaredemo.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public Medicine findById(UUID id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not found with id " + id));
    }

    @Override
    public List<MedicineDTO> getAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();
        return List.copyOf(medicines.stream().map(MedicineMapper::toDTO).collect(Collectors.toList()));
    }

    @Override
    public MedicineDTO create(MedicineCreateDTO medicineDTO) {
        Medicine medicine = MedicineMapper.toEntity(medicineDTO);
        medicineRepository.save(medicine);
        return MedicineMapper.toDTO(medicine);
    }

    @Override
    public MedicineDTO update(UUID id, MedicineCreateDTO medicineCreateDTO) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not found with id " + id));
        MedicineMapper.toUpdateEntity(medicine, medicineCreateDTO);
        medicineRepository.save(medicine);
        return MedicineMapper.toDTO(medicine);
    }

    @Override
    public void delete(UUID id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not found with id " + id));
        medicineRepository.delete(medicine);

    }

    @Override
    public MedicineDTO getMedicineById(UUID id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not found with id " + id));
        return MedicineMapper.toDTO(medicine);
    }
}
