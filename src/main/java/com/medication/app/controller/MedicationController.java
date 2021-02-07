package com.medication.app.controller;


import com.medication.app.dto.CaregiverDTO;
import com.medication.app.dto.MedicationDTO;
import com.medication.app.entity.hospital.Medication;
import com.medication.app.service.hospital.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("http://localhost:4200")
@CrossOrigin("*")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @GetMapping("/allMedications")
    public ResponseEntity<List<MedicationDTO>> getMedications() {
        List<MedicationDTO> dtos = medicationService.findMedications();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/findMedicationById/{id}")
    public ResponseEntity<MedicationDTO> getMedication(@PathVariable("id") Long id) {
        MedicationDTO dto = medicationService.findMedicationById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/findMedicationByName/{name}")
    public ResponseEntity<MedicationDTO> getMedicationByName(@PathVariable("name") String name) {
        MedicationDTO dto = medicationService.findMedicationByName(name);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteMedicationById/{id}")
    public ResponseEntity<MedicationDTO> deleteMedicationById(@PathVariable("id") Long id) {
        medicationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/deleteMediationByName/{name}")
    public ResponseEntity<MedicationDTO> deleteMedicationByName(@PathVariable String name) {
        medicationService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/updateMedicationById/{id}")
    public ResponseEntity<MedicationDTO> updateMedicationById(@PathVariable("id" ) Long id, @RequestParam("name") String name, @RequestParam("description") String description,
                                                            @RequestParam("sideEffects") String sideEffects, @RequestParam("dosage") int dosage){

        Medication medication = medicationService.getById(id);
        medicationService.updateMedicationById(medication, name, description, sideEffects, dosage);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/updateMedicationByName")
    public ResponseEntity<MedicationDTO> updateMedicationByName(@RequestBody MedicationDTO medicationDTO){

        //Medication medication = medicationService.getByName(medicationDTO.getName());
        medicationService.updateMedicationByName(medicationDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @PostMapping(value = "/insertMedication")
    public ResponseEntity<MedicationDTO> insertMedication(@RequestBody MedicationDTO medicationDTO){
        medicationService.insert(medicationDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
