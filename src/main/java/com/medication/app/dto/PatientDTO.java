package com.medication.app.dto;


import com.medication.app.entity.hospital.Prescription;

import java.util.Date;
import java.util.List;

public class PatientDTO extends UserDTO{

    private String medicalRecord;
    private Long assignedDoctor;
    private String assignedDoctorS;
    private Long assignedCaregiver;
    private String assignedCaregiverS;
    private List<Long> prescriptions;

    public PatientDTO(Long id, String name, String password, Date birthdate, String gender, String address, String role, String medicalRecord,  Long medicalDoctor, Long caregiver){
        super(id, name, password, birthdate, gender, address,role);
        this.medicalRecord = medicalRecord;
        this.assignedDoctor = medicalDoctor;
        this.assignedCaregiver = caregiver;
    }

    public PatientDTO(Long id, String name, String password, Date birthdate, String gender, String address, String role, String medicalRecord,  String medicalDoctor, String caregiver){
        super(id, name, password, birthdate, gender, address,role);
        this.medicalRecord = medicalRecord;
        this.assignedDoctorS = medicalDoctor;
        this.assignedCaregiverS = caregiver;
    }

    public PatientDTO(){

    }

    public PatientDTO(String name, String password, Date birthdate, String gender, String address, String role) {

        super( name, password, birthdate, gender, address,role);
    }

    public String getAssignedDoctorS() {
        return assignedDoctorS;
    }

    public void setAssignedDoctorS(String assignedDoctorS) {
        this.assignedDoctorS = assignedDoctorS;
    }

    public String getAssignedCaregiverS() {
        return assignedCaregiverS;
    }

    public void setAssignedCaregiverS(String assignedCaregiverS) {
        this.assignedCaregiverS = assignedCaregiverS;
    }

    public List<Long> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Long> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public Long getAssignedDoctor() {
        return assignedDoctor;
    }

    public void setAssignedDoctor(Long assignedDoctor) {
        this.assignedDoctor = assignedDoctor;
    }

    public Long getAssignedCaregiver() {
        return assignedCaregiver;
    }

    public void setAssignedCaregiver(Long assignedCaregiver) {
        this.assignedCaregiver = assignedCaregiver;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
