package com.medication.app.entity.hospital;

import com.medication.app.entity.base.BaseEntity;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity(name="medication")
public class Medication extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "side_effects")
    private String sideEffects;

    @Column(name = "dosage")
    private int dosage;

    @Column(name = "time_to_take")
    private Time timeToTake;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Prescription> prescriptions;

    @OneToMany(mappedBy = "medication")
    private List<TakenMedication> takenMedications;

    public Medication(){

    }

    public Medication(String name, String description, String sideEffects, int dosage) {
        this.name = name;
        this.description = description;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
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

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
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

    public Time getTimeToTake() {
        return timeToTake;
    }

    public void setTimeToTake(Time timeToTake) {
        this.timeToTake = timeToTake;
    }
}
