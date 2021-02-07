package com.medication.app.rmi;

import assigment3.dto.MedicationRmiDto;
import assigment3.rmi.RmiServer;
import com.medication.app.entity.hospital.Medication;
import com.medication.app.entity.hospital.Prescription;
import com.medication.app.rmi.builders.MedicationRmiDtoBuilder;
import com.medication.app.service.hospital.PrescriptionService;
import com.medication.app.service.hospital.TakenMedicationService;

import java.util.ArrayList;
import java.util.List;

public class RmiServerImpl implements RmiServer {

    private static PrescriptionService prescriptionService;

    private static TakenMedicationService takenMedicationService;

    @Override
    public List<MedicationRmiDto> sendMedications(String username) {
        List<MedicationRmiDto> medicationRmiDtoList = new ArrayList<>();
        List<Prescription> prescriptions = prescriptionService.findPrescriptionsByPatient(username);
        for (Prescription prescription : prescriptions){
            for (Medication medication : prescription.getMedications()){
                MedicationRmiDto medicationRmiDto = MedicationRmiDtoBuilder.build(medication);
                medicationRmiDto.setPatient(prescription.getAssignedPatient().getName());
                medicationRmiDtoList.add(medicationRmiDto);
            }
        }
        return medicationRmiDtoList;
    }

    @Override
    public void addTaken(MedicationRmiDto medicationRmiDto, boolean taken) {
        System.out.println(medicationRmiDto);
        takenMedicationService.insert(medicationRmiDto, taken);
    }

    public static PrescriptionService getPrescriptionService() {
        return prescriptionService;
    }

    public static void setPrescriptionService(PrescriptionService prescriptionService) {
        RmiServerImpl.prescriptionService = prescriptionService;
    }

    public static void setTakenMedicationService(TakenMedicationService takenMedicationService) {
        RmiServerImpl.takenMedicationService = takenMedicationService;
    }
}
