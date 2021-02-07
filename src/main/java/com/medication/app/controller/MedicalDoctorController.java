package com.medication.app.controller;

import com.medication.app.dto.MedicalDoctorDTO;
import com.medication.app.entity.hospital.MedicalDoctor;
import com.medication.app.service.hospital.MedicalDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
//@CrossOrigin("http://localhost:4200")
@CrossOrigin("*")
public class MedicalDoctorController {

    @Autowired
    private MedicalDoctorService medicalDoctorService;

    @GetMapping("/alldoctors")
    public ResponseEntity<List<MedicalDoctorDTO>> getDoctors() {
        List<MedicalDoctorDTO> dtos = medicalDoctorService.findDoctors();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/findDoctorById/{id}")
    public ResponseEntity<MedicalDoctorDTO> getDoctor(@PathVariable("id") Long id) {
        MedicalDoctorDTO dto = medicalDoctorService.findDoctorById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/findDoctorByName/{name}")
    public ResponseEntity<MedicalDoctorDTO> getDoctorByName(@PathVariable("name") String name) {
        MedicalDoctorDTO dto = medicalDoctorService.findDoctorByName(name);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteDoctor/{id}")
    public ResponseEntity<MedicalDoctorDTO> deleteDoctor(@PathVariable("id") Long id) {
        medicalDoctorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/updateById/{id}")
    public ResponseEntity<MedicalDoctorDTO> updateDoctor(@PathVariable("id" ) Long id, @RequestParam("name") String name, @RequestParam("password") String password,
                                                      @RequestParam("birthdate") Date birthdate, @RequestParam("gender") String gender, @RequestParam("address") String address,
                                                      @RequestParam("role_id") String role_id){

        MedicalDoctor medicalDoctor = medicalDoctorService.getById(id);
        medicalDoctorService.updateMedicalDoctor(medicalDoctor, name, password, birthdate, gender, address, role_id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/insertDoctor")
    public ResponseEntity<MedicalDoctorDTO> insertDoctor(@RequestBody MedicalDoctorDTO medicalDoctorDTO){

        medicalDoctorService.insert(medicalDoctorDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}




