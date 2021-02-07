package com.medication.app.dto.builder;


import com.medication.app.dto.CaregiverDTO;
import com.medication.app.dto.UserDTO;
import com.medication.app.entity.hospital.Caregiver;
import com.medication.app.entity.hospital.MedicalDoctor;
import com.medication.app.entity.hospital.Patient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CaregiverBuilder {

    public CaregiverBuilder(){

    }

    public static CaregiverDTO toCaregiverDTO(Caregiver caregiver) {

        CaregiverDTO caregiverDTO = new CaregiverDTO();
        caregiverDTO.setAddress(caregiver.getAddress());
        caregiverDTO.setBirthdate(caregiver.getBirthdate());
        caregiverDTO.setGender(caregiver.getGender());
        caregiverDTO.setName(caregiver.getName());
        caregiverDTO.setPassword(caregiver.getPassword());
        caregiverDTO.setRole(caregiver.getRole());
        caregiverDTO.setId(caregiver.getId());

        if(caregiver.getPatients() != null){
            List<String> ids = new ArrayList<>();
            for(Patient patient: caregiver.getPatients()){
                ids.add(patient.getName());
            }
            caregiverDTO.setPatients(ids);
        }

        return caregiverDTO;
    }

    public static Caregiver toEntity(CaregiverDTO caregiverDTO) {

        Caregiver caregiver = new Caregiver();
        caregiver.setGender(caregiverDTO.getGender());
        caregiver.setRole("caregiver");
       // caregiver.setPatients(caregiverDTO.getPatients());
        caregiver.setAddress(caregiverDTO.getAddress());
        caregiver.setBirthdate(caregiverDTO.getBirthdate());
        caregiver.setName(caregiverDTO.getName());
        caregiver.setPassword(caregiverDTO.getPassword());
        caregiver.setId(caregiverDTO.getId());

        return caregiver;
    }

    public Caregiver userToCaregiver(UserDTO userDTO){
        Caregiver caregiver = new Caregiver();
        caregiver.setName(userDTO.getName());
        caregiver.setPassword(userDTO.getPassword());
        caregiver.setRole("caregiver");
        caregiver.setBirthdate(userDTO.getBirthdate());
        caregiver.setGender(userDTO.getGender());
        caregiver.setAddress(userDTO.getAddress());
        return caregiver;
    }

    public CaregiverDTO userToCaregiverDTO(UserDTO userDTO){
        CaregiverDTO caregiver = new CaregiverDTO();
        caregiver.setName(userDTO.getName());
        caregiver.setPassword(userDTO.getPassword());
        caregiver.setRole("caregiver");
        caregiver.setBirthdate(userDTO.getBirthdate());
        caregiver.setGender(userDTO.getGender());
        caregiver.setAddress(userDTO.getAddress());
        return caregiver;
    }
}
