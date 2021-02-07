package com.medication.app.entity.hospital;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "patient")
public class Patient extends User{

    @ManyToOne
    @JoinColumn(name = "medical_doctor_id")
    private MedicalDoctor medical_doctor_id;

    @ManyToOne
    @JoinColumn(name = "caregiver")
    private Caregiver caregiver;

    @OneToMany(mappedBy = "patientId")
    @JsonBackReference
    private List<Prescription> prescriptions;

    @Column(name = "medical_record")
    private String medicalRecord;

    @OneToMany(mappedBy = "patient")
    private List<Activity> activities;

    @OneToMany(mappedBy = "patient")
    private List<TakenMedication> takenMedication;

    public Patient(String name, String password) {
        super(name, password);
    }

    public Patient(){

    }

    public Patient(Long id, String name, String password, Date birthdate, String gender, String address, String role1, MedicalDoctor medicalDoctor, Caregiver caregiver){
        super(id, name, password, birthdate, gender, address, role1);
        this.medical_doctor_id = medicalDoctor;
        this.caregiver = caregiver;
    }


    public Patient(String name, String password, Date birthdate, String gender, String address, String role1) {
        super(name, password, birthdate, gender, address, role1);
    }

    public Patient(String name, String password, Date birthdate, String gender, String address, String role1, MedicalDoctor medicalDoctor, Caregiver caregiver){
        super(name, password, birthdate, gender, address, role1);
        this.medical_doctor_id = medicalDoctor;
        this.caregiver = caregiver;
    }


    public Patient(String name, Date birthdate, String gender, String address) {
        super(name, birthdate, gender, address);
    }

    public Patient(String name, Date birthdate, String gender, String address, String role, String medicalRecord, MedicalDoctor assignedDoctor, Caregiver assignedCaregiver) {
        super(name, birthdate, gender, address, role);
        this.medicalRecord = medicalRecord;
        this.medical_doctor_id = assignedDoctor;
        this.caregiver = assignedCaregiver;
    }

    public Patient(String name,String password,  Date birthdate, String gender, String address, String role, String medicalRecord, MedicalDoctor assignedDoctor, Caregiver assignedCaregiver) {
        super(name, password, birthdate, gender, address, role);
        this.medicalRecord = medicalRecord;
        this.medical_doctor_id = assignedDoctor;
        this.caregiver = assignedCaregiver;
    }

    public Patient(String name, String password, Date birthdate, String gender, String address, String role1, String medicalRecord) {
        super(name, password, birthdate, gender, address, role1);
        this.medicalRecord = medicalRecord;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public MedicalDoctor getAssignedDoctor() {
        return medical_doctor_id;
    }

    public void setAssignedDoctor(MedicalDoctor assignedDoctor) {
        this.medical_doctor_id = assignedDoctor;
    }

    public Caregiver getAssignedCaregiver() {
        return caregiver;
    }

    public void setAssignedCaregiver(Caregiver assignedCaregiver) {
        this.caregiver = assignedCaregiver;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

}
