package com.medication.app.dto;

import java.time.LocalDateTime;

public class ActivityDTO{

    private Long startDate;
    private Long endDate;
    private String name;
    private Long patientId;

    public ActivityDTO(){
    }

    public ActivityDTO(Long startDate, Long endDate, String name) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "ActivityDTO{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", name='" + name + '\'' +
                ", patientId=" + patientId +
                '}';
    }
}
