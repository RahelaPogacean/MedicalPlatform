package com.medication.app.dto;


import java.util.List;

public class CaregiverDTO extends UserDTO{

    private List<String> patients;

    public CaregiverDTO(Long id, String name, String password){
        super(id, name, password);
    }

    public CaregiverDTO(){

    }

    public List<String> getPatients() {
        return patients;
    }

    public void setPatients(List<String> patients) {
        this.patients = patients;
    }
}
