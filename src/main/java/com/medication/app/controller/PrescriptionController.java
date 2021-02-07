package com.medication.app.controller;

import com.medication.app.dto.MedicalDoctorDTO;
import com.medication.app.dto.PrescriptionDTO;
import com.medication.app.entity.hospital.Prescription;
import com.medication.app.service.hospital.MedicationService;
import com.medication.app.service.hospital.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@CrossOrigin("http://localhost:4200")
@CrossOrigin("*")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private MedicationService medicationService;

    @PostMapping("/insertPrescription")
    public ResponseEntity<?> insertPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {

        prescriptionService.insert(prescriptionDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/allPrescriptions")
    public ResponseEntity<List<PrescriptionDTO>> getPrescriptions() {
        List<PrescriptionDTO> dtos = prescriptionService.findPrescriptions();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
