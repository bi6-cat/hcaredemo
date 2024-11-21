package com.zett.hcaredemo.controller;

import com.zett.hcaredemo.dto.medicine.MedicineCreateDTO;
import com.zett.hcaredemo.dto.medicine.MedicineDTO;
import com.zett.hcaredemo.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
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
    @GetMapping("/create")
    public String create(Model model) {
        log.info("MedicineController.create");
        MedicineCreateDTO medicineCreateDTO = new MedicineCreateDTO();
        model.addAttribute("medicineDTO", medicineCreateDTO);
        return "medicine/create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute MedicineCreateDTO medicineCreateDTO) {
        log.info("MedicineController.creating");
        medicineService.create(medicineCreateDTO);
        log.info("MedicineController.created");
        return "redirect:/medicines";
    }
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable UUID id) {
        MedicineDTO medicine = medicineService.getMedicineById(id);
        model.addAttribute("medicine", medicine);
        MedicineCreateDTO medicineCreateDTO = new MedicineCreateDTO();
        model.addAttribute("medicineCreateDTO", medicineCreateDTO);
        return "medicine/update";
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute MedicineCreateDTO medicineCreateDTO, @PathVariable UUID id) {
        medicineService.update(id, medicineCreateDTO);
        return "medicine/update";
    }
    @PostMapping("/delete/{id}")
    public String delete( @PathVariable UUID id, Model model) {
        model.addAttribute("id", id);
        medicineService.delete(id);
        return "medicine/delete";
    }
}
