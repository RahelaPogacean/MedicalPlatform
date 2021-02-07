package com.medication.app.dto;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class PrescriptionDTO extends BaseDTO<Long>{

    private String assignedPatient;
    private String assignedMedicalDoctor;
    private String medicationNames;
    private List<Long> medicationsIds;
    private String startTime;
    private String endTime;
    private Date startDate;
    private Date endDate;

    public  PrescriptionDTO(){

    }

    public PrescriptionDTO(String assignedPatient, String medicalDoctor, List<Long> medications,
                           String startTime, String endTime, Date startDate, Date endDate){
        this.assignedPatient = assignedPatient;
        this.assignedMedicalDoctor = medicalDoctor;
        this.medicationsIds = medications;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public List<Long> getMedicationsIds() {
        return medicationsIds;
    }

    public void setMedicationsIds(List<Long> medicationsIds) {
        this.medicationsIds = medicationsIds;
    }

    public String getAssignedPatient() {
        return assignedPatient;
    }

    public void setAssignedPatient(String assignedPatient) {
        this.assignedPatient = assignedPatient;
    }

    public String getAssignedMedicalDoctor() {
        return assignedMedicalDoctor;
    }

    public void setAssignedMedicalDoctor(String assignedMedicalDoctor) {
        this.assignedMedicalDoctor = assignedMedicalDoctor;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public String getMedicationNames() {
        return medicationNames;
    }

    public void setMedicationNames(String medicationNames) {
        this.medicationNames = medicationNames;
    }
}
