package com.medication.app.entity.hospital;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.medication.app.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity(name = "prescription")
public class Prescription extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "patient")
    private Patient patientId;

    @ManyToOne
    @JoinColumn(name = "medical_doctor_id")
    private MedicalDoctor medical_doctor_id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "prescription_medication", joinColumns = @JoinColumn(name = "prescription_id"), inverseJoinColumns = @JoinColumn(name = "medication_id"))
    @JsonBackReference
    private List<Medication> medications;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;


    public Prescription(Date start_date, Date end_date, LocalTime start_time, LocalTime end_time, MedicalDoctor medicalDoctor, Patient patient) {
        this.startDate = start_date;
        this.endDate = end_date;
        this.startTime = start_time;
        this.endTime = end_time;
        this.medical_doctor_id = medicalDoctor;
        this.patientId = patient;
    }

    public Prescription(Date start_date, Date end_date, LocalTime start_time, LocalTime end_time, MedicalDoctor medicalDoctor, Patient patient, List<Medication> medications) {
        this.startDate = start_date;
        this.endDate = end_date;
        this.startTime = start_time;
        this.endTime = end_time;
        this.medical_doctor_id = medicalDoctor;
        this.patientId = patient;
        this.medications = medications;
    }

    public Prescription(){

    }



//    public Prescription(Patient patient, MedicalDoctor medicalDoctor, List<Long> medicationsIds, LocalTime start_time, LocalTime end_time, Date start_date, Date end_date) {
//        this.startDate = start_date;
//        this.endDate = end_date;
//        this.startTime = start_time;
//        this.endTime = end_time;
//        this.assignedMedicalDoctor = medicalDoctor;
//        this.assignedPatient = patient;
//        this.medicationsIds = medicationsIds;
//    }

    public Prescription(Patient patient, MedicalDoctor medicalDoctor, List<Medication> medications, LocalTime start_time, LocalTime end_time, Date start_date, Date end_date) {
        this.startDate = start_date;
        this.endDate = end_date;
        this.startTime = start_time;
        this.endTime = end_time;
        this.medical_doctor_id = medicalDoctor;
        this.patientId = patient;
        this.medications = medications;
    }



    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public Patient getAssignedPatient() {
        return patientId;
    }

    public void setAssignedPatient(Patient assignedPatient) {
        this.patientId = assignedPatient;
    }

    public MedicalDoctor getAssignedMedicalDoctor() {
        return medical_doctor_id;
    }

    public void setAssignedMedicalDoctor(MedicalDoctor assignedMedicalDoctor) {
        this.medical_doctor_id = assignedMedicalDoctor;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }



}
