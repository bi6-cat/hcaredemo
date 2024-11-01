package com.zett.hcaredemo.controller;

import com.zett.hcaredemo.dto.PatientRequest;
import com.zett.hcaredemo.dto.PatientResponse;
import com.zett.hcaredemo.dto.Response;
import com.zett.hcaredemo.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<Response<List<PatientResponse>>> getAllPatients() {
        logger.info("Fetching all patients");
        List<PatientResponse> patientResponses = patientService.findAll();
        Response<List<PatientResponse>> response = new Response<>(200, "Users retrieved successfully", patientResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getPatientById(@PathVariable UUID id) {
        logger.info("Fetching patient with ID: {}", id);
        Optional<PatientResponse> patientResponse = patientService.findById(id);
        if (patientResponse.isPresent()) {
            PatientResponse patientResponses = patientResponse.get();
            Response<PatientResponse> response = new Response<>(200, "Users retrieved successfully", patientResponses);
            return ResponseEntity.ok(response);
        }
        Response<String> errorResponse = new Response<>(404, "User not found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @PostMapping
    public ResponseEntity<Response<PatientResponse>> createPatient(@RequestBody PatientRequest patientRequest) {
        logger.info("Creating new patient with name: {}", patientRequest.getFullName());
        PatientResponse createdPatient = patientService.save(patientRequest);
        Response<PatientResponse> response = new Response<>(201, "User created successfully", createdPatient);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<PatientResponse>> updatePatient(@PathVariable UUID id, @RequestBody PatientRequest patientRequest) {
        logger.info("Updating patient with ID: {}", id);
        PatientResponse updatedPatient = patientService.update(id, patientRequest);
        Response<PatientResponse> response = new Response<>(200, "User updated successfully", updatedPatient);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deletePatient(@PathVariable UUID id) {
        logger.info("Deleting patient with ID: {}", id);
        patientService.deleteById(id);
        Response<Void> response = new Response<>(200, "User deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
