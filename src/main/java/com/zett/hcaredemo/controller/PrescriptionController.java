package com.zett.hcaredemo.controller;

import com.zett.hcaredemo.dto.medicine.MedicineRequest;
import com.zett.hcaredemo.dto.medicine.PrescriptionForm;
import com.zett.hcaredemo.entity.Prescription;
import com.zett.hcaredemo.service.MedicineService;
import com.zett.hcaredemo.service.PrescriptionService;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final MedicineService medicineService;

    public PrescriptionController(PrescriptionService prescriptionService,
                                  MedicineService medicineService) {    
        this.prescriptionService = prescriptionService;
        this.medicineService = medicineService;
    }

    @GetMapping("/create/{id}")
    public String createPrescriptionForm(Model model, @PathVariable UUID id) {
        model.addAttribute("prescriptionForm", new PrescriptionForm());
        model.addAttribute("id", id);
        model.addAttribute("medicines", medicineService.getAllMedicines());

        return "doctors/prescription/create";
    }

    @PostMapping("/create/{id}")
    public String createPrescription(
            @PathVariable UUID id,
            @ModelAttribute("prescriptionForm") PrescriptionForm form,
            Model model) {
            Prescription prescription = prescriptionService.createPrescription(
                id, 
                form.getNotes(), 
                form.getMedicineRequests()
            );
            model.addAttribute("prescription", prescription);
            return "doctors/prescription/success";
    }

    //delete prescription
    @GetMapping("/delete/{id}")
    public String deletePrescription(@PathVariable UUID id) {
        prescriptionService.deletePrescription(id);
        return "redirect:/prescriptions";
    }
}