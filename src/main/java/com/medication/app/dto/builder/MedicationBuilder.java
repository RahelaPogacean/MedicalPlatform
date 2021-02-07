package com.medication.app.dto.builder;

import com.medication.app.dto.MedicationDTO;
import com.medication.app.entity.hospital.Medication;

import java.sql.Time;

public class MedicationBuilder {

    public MedicationBuilder(){

    }

    public static MedicationDTO toMedicationDTO(Medication medication) {
       MedicationDTO medicationDTO = new MedicationDTO();
       medicationDTO.setId(medication.getId());
       medicationDTO.setDescription(medication.getDescription());
       medicationDTO.setDosage(medication.getDosage());
       medicationDTO.setName(medication.getName());
       medicationDTO.setSideEffects(medication.getSideEffects());
        medicationDTO.setTimeToTake(medication.getTimeToTake().toString());
       return  medicationDTO;
    }

    public static Medication toEntity(MedicationDTO medicationDTO) {
        Medication medication = new Medication();
        medication.setDosage(medicationDTO.getDosage());
        medication.setSideEffects(medicationDTO.getSideEffects());
        medication.setDescription(medicationDTO.getDescription());
        medication.setName(medicationDTO.getName());
        medication.setTimeToTake(Time.valueOf(medicationDTO.getTimeToTake()));

        return medication;
    }

}
