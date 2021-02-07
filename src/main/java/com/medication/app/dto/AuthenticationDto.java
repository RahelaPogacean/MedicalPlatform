package com.medication.app.dto;


import com.medication.app.dto.builder.CaregiverBuilder;
import com.medication.app.dto.builder.MedicalDoctorBuilder;
import com.medication.app.dto.builder.PatientBuilder;
import com.medication.app.service.hospital.CaregiverService;
import com.medication.app.service.hospital.MedicalDoctorService;
import com.medication.app.service.hospital.PatientService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class AuthenticationDto {
    @Inject
    private MedicalDoctorService doctorService;

    @Inject
    private CaregiverService caregiverService;

    @Inject
    private PatientService patientService;

    @Inject
    private MedicalDoctorBuilder doctorBuilder;

    @Inject
    private CaregiverBuilder caregiverBuilder;

    @Inject
    private PatientBuilder patientBuilder;

    public boolean existsUserByUsername(String username){
        return doctorService.existByUsername(username)
                || caregiverService.existByUsername(username)
                || patientService.existByUsername(username);
    }

//    public boolean existUserByEmail(String email){
//        return doctorService.existByEmail(email)
//                || caregiverService.existByEmail(email)
//                || patientService.existByEmail(email);
//    }

    public void registerUser(UserDTO userDTO){
        switch (userDTO.getRole()){
            case "doctor":
                MedicalDoctorDTO doctor = doctorBuilder.userToDoctorDTO(userDTO);
                doctorService.insert(doctor);
                break;
            case "caregiver":
                CaregiverDTO caregiver = caregiverBuilder.userToCaregiverDTO(userDTO);
                caregiverService.insert(caregiver);
                break;
            case "patient":
                PatientDTO patient = patientBuilder.userToPatientDTO(userDTO);
                patientService.insert(patient);
                break;
            default:
                throw new NullPointerException();
        }
    }
}