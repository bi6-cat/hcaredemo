package com.zett.hcaredemo.service;

import com.zett.hcaredemo.dto.hospital.HospitalCreateDTO;
import com.zett.hcaredemo.dto.hospital.HospitalDTO;
import com.zett.hcaredemo.entity.Hospital;
import com.zett.hcaredemo.mapper.HospitalMapper;
import com.zett.hcaredemo.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public List<HospitalDTO> findAll() {
        return hospitalRepository.findAll().stream()
                .map(HospitalMapper::toDTO)
                .toList();
    }

    @Override
    public Page<HospitalDTO> findAll(Pageable pageable) {
        return hospitalRepository.findAll(pageable)
                .map(HospitalMapper::toDTO);
    }

    @Override
    public Page<HospitalDTO> searchHospitals(String keyword, Pageable pageable) {
        return hospitalRepository.findByNameContainingIgnoreCase(keyword, pageable)
                .map(HospitalMapper::toDTO);
    }

    @Override
    public HospitalDTO findById(UUID id) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        return hospitalOptional.map(HospitalMapper::toDTO).orElse(null);
    }

    @Override
    public HospitalDTO create(HospitalCreateDTO hospitalCreateDTO) {
        Hospital hospital = HospitalMapper.toEntity(hospitalCreateDTO);
        Hospital savedHospital = hospitalRepository.save(hospital);
        return HospitalMapper.toDTO(savedHospital);
    }

    @Override
    public HospitalDTO update(UUID id, HospitalDTO hospitalDTO) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        if (hospitalOptional.isPresent()) {
            Hospital hospital = hospitalOptional.get();
            hospital.setName(hospitalDTO.getName());
            hospital.setAddress(hospitalDTO.getAddress());
            hospital.setPhone(hospitalDTO.getPhone());
            hospital.setEmail(hospitalDTO.getEmail());
            hospital.setWebsite(hospitalDTO.getWebsite());
            hospital.setDescription(hospitalDTO.getDescription());
            Hospital updatedHospital = hospitalRepository.save(hospital);
            return HospitalMapper.toDTO(updatedHospital);
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        hospitalRepository.deleteById(id);
    }
}