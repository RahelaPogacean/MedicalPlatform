package com.medication.app.dto.builder;

import com.medication.app.dto.PatientDTO;
import com.medication.app.dto.UserDTO;
import com.medication.app.entity.hospital.MedicalDoctor;
import com.medication.app.entity.hospital.Patient;
import com.medication.app.entity.hospital.Prescription;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientBuilder{

    public PatientBuilder(){

    }

    public static PatientDTO toPatientDTO(Patient patient) {

        PatientDTO patientDTO = new PatientDTO();
        if(patient.getAssignedCaregiver() != null){
            patientDTO.setAssignedCaregiver(patient.getAssignedCaregiver().getId());
            patientDTO.setAssignedCaregiverS(patient.getAssignedCaregiver().getName());
        }

        if(patient.getAssignedDoctor() != null){
            patientDTO.setAssignedDoctor(patient.getAssignedDoctor().getId());
            patientDTO.setAssignedDoctorS(patient.getAssignedDoctor().getName());
        }

        patientDTO.setMedicalRecord(patient.getMedicalRecord());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setBirthdate(patient.getBirthdate());
        patientDTO.setGender(patient.getGender());
        patientDTO.setId(patient.getId());
        patientDTO.setName(patient.getName());
        patientDTO.setPassword(patient.getPassword());
        patientDTO.setRole(patient.getRole());

        List<Long> ids = new ArrayList<>();
        for(Prescription prescription: patient.getPrescriptions()){
            ids.add(prescription.getId());
        }
        patientDTO.setPrescriptions(ids);

        return patientDTO;
    }

    public static Patient toEntity(PatientDTO patientdto) {

        Patient patient = new Patient();
        patient.setMedicalRecord(patientdto.getMedicalRecord());
        patient.setAddress(patientdto.getAddress());
        patient.setBirthdate(patientdto.getBirthdate());
        patient.setGender(patientdto.getGender());
        patient.setName(patientdto.getName());
        patient.setPassword(patientdto.getPassword());
        patient.setId(patientdto.getId());
        patient.setRole("patient");
        //patient.setPrescriptions(patientdto.getPrescriptions());

        return patient;
    }

    public Patient userToPatient(UserDTO userDTO){
        Patient patient = new Patient();
        patient.setName(userDTO.getName());
        patient.setPassword(userDTO.getPassword());
        patient.setRole("patient");
        patient.setBirthdate(userDTO.getBirthdate());
        patient.setGender(userDTO.getGender());
        patient.setAddress(userDTO.getAddress());
        return patient;
    }

    public PatientDTO userToPatientDTO(UserDTO userDTO){
        PatientDTO patient = new PatientDTO();
        patient.setName(userDTO.getName());
        patient.setPassword(userDTO.getPassword());
        patient.setRole("patient");
        patient.setBirthdate(userDTO.getBirthdate());
        patient.setGender(userDTO.getGender());
        patient.setAddress(userDTO.getAddress());
        return patient;
    }
}
