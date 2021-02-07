package com.medication.app.dto;

import java.sql.Time;

public class MedicationDTO extends BaseDTO<Long>{

    private String name;
    private String description;
    private String sideEffects;
    private int dosage;
    private String timeToTake;


    public MedicationDTO(Long id, String name, String description, String sideEffects, int dosage) {
        super(id);
        this.name = name;
        this.description = description;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
    }

    public MedicationDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public String getTimeToTake() {
        return timeToTake;
    }

    public void setTimeToTake(String timeToTake) {
        this.timeToTake = timeToTake;
    }
}
