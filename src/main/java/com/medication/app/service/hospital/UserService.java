package com.medication.app.service.hospital;

import com.medication.app.dao.hospital.CaregiverDAO;
import com.medication.app.dao.hospital.MedicalDoctorDAO;
import com.medication.app.dao.hospital.PatientDAO;
import com.medication.app.dto.*;
import com.medication.app.dto.builder.CaregiverBuilder;
import com.medication.app.dto.builder.MedicalDoctorBuilder;
import com.medication.app.dto.builder.PatientBuilder;
import com.medication.app.entity.hospital.Caregiver;
import com.medication.app.entity.hospital.MedicalDoctor;
import com.medication.app.entity.hospital.Patient;
import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;


@Service
public class UserService {

    private final MedicalDoctorDAO medicalDoctorDAO;
    private final CaregiverDAO caregiverDAO;
    private final PatientDAO patientDAO;

    @Inject
    public UserService(MedicalDoctorDAO medicalDoctorDAO, CaregiverDAO caregiverDAO, PatientDAO patientDAO) {
        this.medicalDoctorDAO = medicalDoctorDAO;
        this.caregiverDAO = caregiverDAO;
        this.patientDAO = patientDAO;
    }

    public LoginDTO login(String name, String password){

        MedicalDoctor medicalDoctor = medicalDoctorDAO.findByName(name).orElse(null);
        if((medicalDoctor != null) && (medicalDoctorDAO.existsByPassword(password))){
            return new LoginDTO(medicalDoctor.getName(), medicalDoctor.getPassword(), medicalDoctor.getRole());
        }

        Caregiver caregiver = caregiverDAO.findByName(name);
        if((caregiver != null) && (caregiverDAO.existsByPassword(password))){
            return new LoginDTO(caregiver.getName(), caregiver.getPassword(), caregiver.getRole());
        }
        Patient patient = patientDAO.findByName(name);
        if((patient != null) && (patientDAO.existsByPassword(password))){
            return new LoginDTO(patient.getName(), patient.getPassword(), patient.getRole());
        }

        System.out.println("The user doesn't exist ");
        return null;
    }

    public RegisterDTO register(String name, String password, Date birthdate, String gender, String address, String role){

        if(role.equals("doctor")){
            MedicalDoctor medicalDoctor = new MedicalDoctor(name, password, birthdate, gender, address, role);
            medicalDoctorDAO.save(medicalDoctor);

            return new RegisterDTO(medicalDoctor.getName(), medicalDoctor.getPassword(), medicalDoctor.getBirthdate(),
                    medicalDoctor.getGender(), medicalDoctor.getAddress(), medicalDoctor.getRole());
        }
        if(role.equals("caregiver")){
            Caregiver caregiver = new Caregiver(name, password, birthdate, gender, address, role);
            caregiverDAO.save(caregiver);
            return new RegisterDTO(caregiver.getName(), caregiver.getPassword(), caregiver.getBirthdate(), caregiver.getGender(),
                    caregiver.getAddress(), caregiver.getRole());
        }
        if(role.equals("patient")){
            Patient patient = new Patient(name, password, birthdate, gender, address, role);
            patientDAO.save(patient);
            return new RegisterDTO(patient.getName(), patient.getPassword(), patient.getBirthdate(), patient.getGender(),
                    patient.getAddress(), patient.getRole());
        }

        System.out.println("Error ");
        return null;
    }
}
