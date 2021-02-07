package com.medication.app.dto.builder;

import com.medication.app.dto.PrescriptionDTO;
import com.medication.app.entity.hospital.Medication;
import com.medication.app.entity.hospital.Prescription;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionBuilder {

    public PrescriptionBuilder(){

    }

    public static PrescriptionDTO toPrescriptionDTO(Prescription prescription) {
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
        prescriptionDTO.setAssignedMedicalDoctor(prescription.getAssignedMedicalDoctor().getName());
        prescriptionDTO.setAssignedPatient(prescription.getAssignedPatient().getName());
        prescriptionDTO.setStartDate(prescription.getStartDate());
        prescriptionDTO.setEndDate(prescription.getEndDate());
        prescriptionDTO.setStartTime(prescription.getStartTime().toString());
        prescriptionDTO.setEndTime(prescription.getEndTime().toString());
        prescriptionDTO.setId(prescription.getId());

        List<Long> ids = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(Medication medication: prescription.getMedications()){
            ids.add(medication.getId());
            stringBuilder.append(medication.getName());
            stringBuilder.append(",");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
        prescriptionDTO.setMedicationNames(stringBuilder.toString());
        prescriptionDTO.setMedicationsIds(ids);
        return prescriptionDTO;
    }

    public static Prescription toEntity(PrescriptionDTO prescriptionDTO) {

        Prescription prescription = new Prescription();
        prescription.setStartDate(prescriptionDTO.getStartDate());
        prescription.setEndDate(prescriptionDTO.getEndDate());
        prescription.setStartTime(LocalTime.parse(prescriptionDTO.getStartTime()));
        prescription.setEndTime(LocalTime.parse(prescriptionDTO.getEndTime()));
        prescription.setId(prescriptionDTO.getId());

        return prescription;
    }
}
