package com.zett.hcaredemo.controller;

import com.zett.hcaredemo.dto.medicine.MedicineDTO;
import com.zett.hcaredemo.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/medicines")
public class MedicineController {
    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping()
    public String index(Model model) {
        log.info("MedicineController.index");
        List<MedicineDTO> medicines = medicineService.getAllMedicines();
        model.addAttribute("medicines", medicines);
        return "medicine/index";
    }
}
