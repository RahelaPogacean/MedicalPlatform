package com.medication.app.entity.hospital;

import com.medication.app.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Time;

@Entity
public class TakenMedication extends BaseEntity<Long> {
    @Column(name = "taken_time")
    private Time takenTime;

    @ManyToOne
    @JoinColumn(name = "medication")
    private Medication medication;

    @ManyToOne
    @JoinColumn(name = "patient")
    private Patient patient;

    @Column(name = "taken")
    private boolean taken;

    public Time getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(Time takenTime) {
        this.takenTime = takenTime;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
