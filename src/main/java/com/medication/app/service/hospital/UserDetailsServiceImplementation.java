package com.medication.app.service.hospital;


import com.medication.app.dao.hospital.CaregiverDAO;
import com.medication.app.dao.hospital.MedicalDoctorDAO;
import com.medication.app.dao.hospital.PatientDAO;
import com.medication.app.dto.UserPrinciple;
import com.medication.app.entity.hospital.Caregiver;
import com.medication.app.entity.hospital.MedicalDoctor;
import com.medication.app.entity.hospital.Patient;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    @Inject
    private MedicalDoctorDAO doctorDAO;

    @Inject
    private CaregiverDAO caregiverDAO;

    @Inject
    private PatientDAO patientDAO;

//    @Inject
//    private PatientDTO patientDTO;
//
//    @Inject
//    private CaregiverDAO caregiverDAO;
//
//    @Injectse
//    private DoctorDtoBuilder doctorDtoBuilder;

    public UserPrinciple loadUserByUsername(String username) throws UsernameNotFoundException {
        MedicalDoctor doctor = doctorDAO.findByName(username).orElse(null);
        if (doctor != null){
            return UserPrinciple.convert(doctor);
        }

        Caregiver caregiver = caregiverDAO.findByName(username);
        if (caregiver != null){
            return UserPrinciple.convert(caregiver);
        }

        Patient patient = patientDAO.findByName(username);
        if (patient != null){
            return UserPrinciple.convert(patient);
        }

        throw new UsernameNotFoundException("User Not Found with username : " + username);
    }
}