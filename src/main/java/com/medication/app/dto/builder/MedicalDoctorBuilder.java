package com.medication.app.dto.builder;

import com.medication.app.dto.MedicalDoctorDTO;
import com.medication.app.dto.UserDTO;
import com.medication.app.entity.hospital.MedicalDoctor;
import com.medication.app.entity.hospital.Medication;
import com.medication.app.entity.hospital.Patient;
import com.medication.app.entity.hospital.Prescription;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MedicalDoctorBuilder {

    public MedicalDoctorBuilder(){

    }

    public static MedicalDoctorDTO toMedicalDoctorDTO(MedicalDoctor medicalDoctor) {

        MedicalDoctorDTO medicalDoctorDTO = new MedicalDoctorDTO();
        medicalDoctorDTO.setAddress(medicalDoctor.getAddress());
        medicalDoctorDTO.setBirthdate(medicalDoctor.getBirthdate());
        medicalDoctorDTO.setGender(medicalDoctor.getGender());
        medicalDoctorDTO.setName(medicalDoctor.getName());
        medicalDoctorDTO.setPassword(medicalDoctor.getPassword());
        medicalDoctorDTO.setRole(medicalDoctor.getRole());
        medicalDoctorDTO.setId(medicalDoctor.getId());

        List<Long> ids = new ArrayList<>();
        List<Long> ids2 = new ArrayList<>();

        for(Patient patient: medicalDoctor.getPatients()){
            ids.add(patient.getId());
        }

        for(Prescription prescription: medicalDoctor.getPrescriptions()){
            ids2.add(prescription.getId());
        }

        medicalDoctorDTO.setPatients(ids);
        medicalDoctorDTO.setPrescriptions(ids2);

        return medicalDoctorDTO;
    }

    public static MedicalDoctor toEntity(MedicalDoctorDTO medicalDoctorDTO) {

        MedicalDoctor medicalDoctor = new MedicalDoctor();
        medicalDoctor.setAddress(medicalDoctorDTO.getAddress());
        medicalDoctor.setGender(medicalDoctorDTO.getGender());
        medicalDoctor.setBirthdate(medicalDoctorDTO.getBirthdate());
        medicalDoctor.setPassword(medicalDoctorDTO.getPassword());
        medicalDoctor.setName(medicalDoctorDTO.getName());
        medicalDoctor.setRole("doctor");
        medicalDoctor.setId(medicalDoctorDTO.getId());

        return medicalDoctor;
    }

    public MedicalDoctor userToDoctor(UserDTO userDTO){
        MedicalDoctor doctor = new MedicalDoctor();
        doctor.setName(userDTO.getName());
        doctor.setPassword(userDTO.getPassword());
        doctor.setRole("doctor");
        doctor.setBirthdate(userDTO.getBirthdate());
        doctor.setGender(userDTO.getGender());
        doctor.setAddress(userDTO.getAddress());
        return doctor;
    }

    public MedicalDoctorDTO userToDoctorDTO(UserDTO userDTO){
        MedicalDoctorDTO doctor = new MedicalDoctorDTO();
        doctor.setName(userDTO.getName());
        doctor.setPassword(userDTO.getPassword());
        doctor.setRole("doctor");
        doctor.setBirthdate(userDTO.getBirthdate());
        doctor.setGender(userDTO.getGender());
        doctor.setAddress(userDTO.getAddress());
        return doctor;
    }
}
