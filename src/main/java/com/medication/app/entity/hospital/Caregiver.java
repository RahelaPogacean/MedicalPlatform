package com.medication.app.entity.hospital;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity(name = "caregiver")
public class Caregiver extends User {

    @OneToMany(mappedBy = "caregiver")
    @JsonBackReference
    private List<Patient> patients;

    public Caregiver(String name, String password) {
        super(name, password);
    }

    public Caregiver(String name, String password, Date birthdate, String gender, String address, String role1) {
        super(name, password, birthdate, gender, address, role1);
    }

    public Caregiver(){

    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
