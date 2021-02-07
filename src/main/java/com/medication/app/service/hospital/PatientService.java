package com.medication.app.service.hospital;

import com.medication.app.dao.hospital.CaregiverDAO;
import com.medication.app.dao.hospital.MedicalDoctorDAO;
import com.medication.app.dao.hospital.PatientDAO;
import com.medication.app.dto.PatientDTO;
import com.medication.app.dto.builder.PatientBuilder;
import com.medication.app.entity.hospital.*;
import com.medication.app.service.base.CrudService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService extends CrudService<Patient, Long> {

    private final PatientDAO patientDAO;
    private final MedicalDoctorDAO medicalDoctorDAO;
    private final CaregiverDAO caregiverDAO;

    @Inject
    public PatientService(PatientDAO patientDAO, MedicalDoctorDAO medicalDoctorDAO, CaregiverDAO caregiverDAO, MedicalDoctorDAO medicalDoctorDAO1, CaregiverDAO caregiverDAO1) {
        super(patientDAO);
        this.patientDAO = patientDAO;
        this.medicalDoctorDAO = medicalDoctorDAO1;
        this.caregiverDAO = caregiverDAO1;
    }

    public List<PatientDTO> findPatients() {
        List<Patient> patients = baseDAO.findAll();
        return patients.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO findPatientById(Long id) {
        Optional<Patient> prosumerOptional = baseDAO.findById(id);
        if (!prosumerOptional.isPresent()) {
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return PatientBuilder.toPatientDTO(prosumerOptional.get());
    }

    public PatientDTO findPatientByName(String name) {
        Optional<Patient> prosumerOptional = Optional.ofNullable(((PatientDAO)getBaseDAO()).findByName(name));
        if (!prosumerOptional.isPresent()) {
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with name: " + name);
        }
        return PatientBuilder.toPatientDTO(prosumerOptional.get());
    }


    public void updatePatientById(Patient patient, String name, String password, Date birthdate, String gender, String address, String role_id, String medical_record, Long caregiver_id) {

        patient.setName(name);
        patient.setPassword(password);
        patient.setBirthdate(birthdate);
        patient.setGender(gender);
        patient.setAddress(address);
        patient.setMedicalRecord(medical_record);
        patient.setRole(role_id);
        Caregiver caregiver = caregiverDAO.findById(caregiver_id).get();
        patient.setAssignedCaregiver(caregiver);
        patient = baseDAO.save(patient);
        System.out.println("Patient with id"  + patient.getId() + " was updated in db");
    }

    public void updatePatientByName(PatientDTO patientDTO) {

        Patient patient = patientDAO.findByName(patientDTO.getName());
        patient.setBirthdate(patientDTO.getBirthdate());
        patient.setGender(patientDTO.getGender());
        patient.setAddress(patientDTO.getAddress());
        patient.setMedicalRecord(patientDTO.getMedicalRecord());
        patient.setRole(patientDTO.getRole());

        Caregiver caregiver = caregiverDAO.findByName(patientDTO.getAssignedCaregiverS());
        patient.setAssignedCaregiver(caregiver);
        patient = baseDAO.save(patient);
        System.out.println("Patient with name"  + patient.getName() + " was updated in db");
    }


    public void insert(PatientDTO patientDTO) {

        MedicalDoctor medicalDoctor = medicalDoctorDAO.findByName(patientDTO.getAssignedDoctorS()).orElse(null);
        Caregiver caregiver = caregiverDAO.findByName(patientDTO.getAssignedCaregiverS());
        Patient patient = PatientBuilder.toEntity(patientDTO);

        patient.setAssignedDoctor(medicalDoctor);
        patient.setAssignedCaregiver(caregiver);

        patient = baseDAO.save(patient);
    }

    public void deleteByName(String name){
        Patient patient = (((PatientDAO)getBaseDAO()).findByName(name));
        baseDAO.delete(patient);
    }

    public Patient getByName(String name){
        return (((PatientDAO)getBaseDAO()).findByName(name));
    }

    public boolean existByUsername(String username){
        return ((PatientDAO)getBaseDAO()).existsByName(username);
    }
}
