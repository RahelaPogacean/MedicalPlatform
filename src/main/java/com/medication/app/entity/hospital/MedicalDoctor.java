package com.medication.app.entity.hospital;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity(name = "medical_doctor")
public class MedicalDoctor extends User {

    @OneToMany(mappedBy = "medical_doctor_id")
    @JsonBackReference
    private List<Patient> patients;

    @OneToMany(mappedBy = "medical_doctor_id")
    @JsonBackReference
    private List<Prescription> prescriptions;

    public MedicalDoctor(List<Patient> patients, List<Prescription> prescriptions){
        super();
        this.patients = patients;
        this.prescriptions = prescriptions;
    }

    public MedicalDoctor(){

    }

    public MedicalDoctor(String name, String password) {
        super(name, password);
    }

    public MedicalDoctor(Long id, String name, String password, Date birthdate, String gender, String address, String role) {
    super(id, name, password, birthdate, gender, address, role);
    }

    public MedicalDoctor(String name, String password, Date birthdate, String gender, String address, String role) {
        super(name, password, birthdate, gender, address, role);
    }


    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @Override
    public String toString() {
        return "MedicalDoctor{" +
                "patients=" + patients +
                ", prescriptions=" + prescriptions +
                '}';
    }
}
