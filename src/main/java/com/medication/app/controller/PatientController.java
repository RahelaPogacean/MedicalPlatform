package com.medication.app.controller;

import com.medication.app.dto.CaregiverDTO;
import com.medication.app.dto.PatientDTO;
import com.medication.app.dto.PrescriptionDTO;
import com.medication.app.entity.hospital.Patient;
import com.medication.app.service.hospital.PatientService;
import com.medication.app.service.hospital.PrescriptionService;
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
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/allPatientsOld")
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> dtos = patientService.findPatients();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/findPatientById/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("id") Long id) {
        PatientDTO dto = patientService.findPatientById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/findPatientByName/{name}")                     /////
    public ResponseEntity<PatientDTO> getPatientByName(@PathVariable("name") String name) {
        PatientDTO dto = patientService.findPatientByName(name);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletePatientById/{id}")
    public ResponseEntity<PatientDTO> deletePatientById(@PathVariable("id") Long id) {
        patientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/deletePatientByName/{name}")
    public ResponseEntity<PatientDTO> deletePatientByName(@PathVariable("name") String name) {
        patientService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/updatePatientById/{id}")
    public ResponseEntity<PatientDTO> updatePatientById(@PathVariable("id" ) Long id, @RequestParam("name") String name, @RequestParam("password") String password,
                                                            @RequestParam("birthdate") Date birthdate, @RequestParam("gender") String gender, @RequestParam("address") String address,
                                                            @RequestParam("role_id") String role_id,  @RequestParam("medical_record") String medical_record, @RequestParam("caregiver_id") Long caregiver_id){

        Patient patient = patientService.getById(id);
        patientService.updatePatientById(patient, name, password, birthdate, gender, address, role_id, medical_record, caregiver_id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/updatePatientByName")
    public ResponseEntity<PatientDTO> updatePatientByName(@RequestBody PatientDTO patientDTO){

        patientService.updatePatientByName(patientDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/insertPatient2")
    public ResponseEntity<PatientDTO> insertPatient2(@RequestBody PatientDTO patientDTO){

        patientService.insert(patientDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/viewDetails/{name}")
    public ResponseEntity<List<PrescriptionDTO>> viewDetails(@PathVariable("name") String name) {
        List<PrescriptionDTO> prescriptionDTOS = new ArrayList<>();
        PatientDTO dto = patientService.findPatientByName(name);

        for (Long id : dto.getPrescriptions()) {
            PrescriptionDTO prescriptionDTO = prescriptionService.findPrescriptionById(id);
            prescriptionDTOS.add(prescriptionDTO);

        }

        return new ResponseEntity<>(prescriptionDTOS, HttpStatus.OK);
    }

}
