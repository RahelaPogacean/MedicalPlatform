package com.medication.app.controller;

import com.medication.app.dto.CaregiverDTO;
import com.medication.app.dto.PatientDTO;
import com.medication.app.entity.hospital.Caregiver;
import com.medication.app.entity.hospital.Patient;
import com.medication.app.service.hospital.CaregiverService;
import com.medication.app.service.hospital.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
//@CrossOrigin("http://localhost:4200")
@CrossOrigin("*")
public class CaregiverController {

    @Autowired
    private CaregiverService caregiverService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/allCaregivers")
    public ResponseEntity<List<CaregiverDTO>> getCaregivers() {
        List<CaregiverDTO> dtos = caregiverService.findCaregivers();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/findCaregiverById/{id}")
    public ResponseEntity<CaregiverDTO> getCaregiver(@PathVariable("id") Long id) {
        CaregiverDTO dto = caregiverService.findCaregiverById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/findCaregiverByName/{name}")
    public ResponseEntity<CaregiverDTO> getCaregiverByName(@PathVariable("name") String name) {
        CaregiverDTO dto = caregiverService.findCaregiverByName(name);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteCaregiverById/{id}")
    public ResponseEntity<CaregiverDTO> deleteCaregiverById(@PathVariable("id") Long id) {
        caregiverService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/deleteCaregiverByName/{name}")
    public ResponseEntity<CaregiverDTO> deleteCaregiverByName(@PathVariable("name") String name) {
        caregiverService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/updateCaregiverById/{id}")
    public ResponseEntity<CaregiverDTO> updateCaregiverById(@PathVariable("id" ) Long id, @RequestParam("name") String name, @RequestParam("password") String password,
                                                         @RequestParam("birthdate") Date birthdate, @RequestParam("gender") String gender, @RequestParam("address") String address,
                                                         @RequestParam("role_id") String role_id){

        Caregiver caregiver = caregiverService.getById(id);
        caregiverService.updateCaregiverById(caregiver, name, password, birthdate, gender, address, role_id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @PutMapping(value = "/updateCaregiverByName")
    public ResponseEntity<CaregiverDTO> updateCaregiverByName(@RequestBody CaregiverDTO caregiverDTO){

        caregiverService.updateCaregiverByName(caregiverDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/insertCaregiver")
    public ResponseEntity<CaregiverDTO> insertCaregiver(@RequestBody CaregiverDTO caregiverDTO){

        caregiverService.insert(caregiverDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/assignedPatients/{name}")
    public ResponseEntity<List<PatientDTO>> getPatients(@PathVariable String name){
        List<PatientDTO> patientDTOS = new ArrayList<>();
        CaregiverDTO dto = caregiverService.findCaregiverByName(name);

        if(dto != null){
            for(String name1: dto.getPatients()){
                PatientDTO patientDTO = patientService.findPatientByName(name1);
                patientDTOS.add(patientDTO);
            }
        }

        return new ResponseEntity<>(patientDTOS, HttpStatus.OK);
    }


}
