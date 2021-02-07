package com.medication.app.rmi.builders;

import assigment3.dto.MedicationRmiDto;
import com.medication.app.entity.hospital.Medication;

public class MedicationRmiDtoBuilder {
    public static MedicationRmiDto build(Medication medication){
        MedicationRmiDto medicationRmiDto = new MedicationRmiDto();
        medicationRmiDto.setName(medication.getName());
        medicationRmiDto.setDosage(medication.getDosage());
        medicationRmiDto.setDescription(medication.getDescription());
        medicationRmiDto.setSideEffects(medication.getSideEffects());
        medicationRmiDto.setTimeToTake(medication.getTimeToTake());
        return medicationRmiDto;
    }
}
