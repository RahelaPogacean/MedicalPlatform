package com.medication.app.controller;

import com.medication.app.rmi.RmiServerImpl;
import com.medication.app.service.hospital.PrescriptionService;
import com.medication.app.service.hospital.TakenMedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RmiController {
    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private TakenMedicationService takenMedicationService;

    @PostMapping("/rmi")
    public ResponseEntity<?> setService(){
        RmiServerImpl.setPrescriptionService(prescriptionService);
        RmiServerImpl.setTakenMedicationService(takenMedicationService);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
