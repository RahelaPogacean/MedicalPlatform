package com.medication.app.dto;

import java.util.Date;
import java.util.List;

public class MedicalDoctorDTO extends UserDTO{

    List<Long> patients;
    List<Long> prescriptions;

    public MedicalDoctorDTO(Long id, String name, String password, Date birthdate, String gender,
                            String address, String role, List<Long> patients, List<Long> prescriptions){
        super(id, name, password, birthdate, gender, address, role);
        this.patients = patients;
        this.prescriptions = prescriptions;
    }

    public MedicalDoctorDTO(){

    }

    public List<Long> getPatients() {
        return patients;
    }

    public void setPatients(List<Long> patients) {
        this.patients = patients;
    }

    public List<Long> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Long> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
