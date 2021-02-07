package com.medication.app.service.hospital;

import com.medication.app.dao.hospital.MedicalDoctorDAO;
import com.medication.app.dao.hospital.MedicationDAO;
import com.medication.app.dao.hospital.PatientDAO;
import com.medication.app.dao.hospital.PrescriptionDAO;
import com.medication.app.dto.MedicalDoctorDTO;
import com.medication.app.dto.PatientDTO;
import com.medication.app.dto.PrescriptionDTO;
import com.medication.app.dto.builder.MedicalDoctorBuilder;
import com.medication.app.dto.builder.PatientBuilder;
import com.medication.app.dto.builder.PrescriptionBuilder;
import com.medication.app.entity.hospital.*;
import com.medication.app.service.base.CrudService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrescriptionService extends CrudService<Prescription, Long> {


    private final PrescriptionDAO prescriptionDAO;
    private final MedicationDAO medicationDAO;
    private final PatientDAO patientDAO;
    private final MedicalDoctorDAO medicalDoctorDAO;

    @Inject
    public PrescriptionService(PrescriptionDAO prescriptionDAO, MedicationDAO medicationDAO, PatientDAO patientDAO, MedicalDoctorDAO medicalDoctorDAO) {
        super(prescriptionDAO);
        this.prescriptionDAO = prescriptionDAO;
        this.medicationDAO = medicationDAO;
        this.patientDAO = patientDAO;
        this.medicalDoctorDAO = medicalDoctorDAO;
    }

    public PrescriptionDTO findPrescriptionById(Long id) {
        Optional<Prescription> prosumerOptional = ((PrescriptionDAO)getBaseDAO()).findById(id);
        if (!prosumerOptional.isPresent()) {
            throw new ResourceNotFoundException(Prescription.class.getSimpleName() + " with id: " + id);
        }
        return PrescriptionBuilder.toPrescriptionDTO(prosumerOptional.get());
    }


    public void insert(PrescriptionDTO prescriptionDTO) {

        MedicalDoctor medicalDoctor = medicalDoctorDAO.findByName(prescriptionDTO.getAssignedMedicalDoctor()).get();
        Patient patient = patientDAO.findByName(prescriptionDTO.getAssignedPatient());
        Prescription prescription = PrescriptionBuilder.toEntity(prescriptionDTO);

        List<Medication> medications = new ArrayList<>();

        String[] medicationNames = prescriptionDTO.getMedicationNames().split(",");
        for(String name: medicationNames){
            Medication medication = medicationDAO.findByName(name);
            medication.getPrescriptions().add(prescription);
            medications.add(medication);
        }
        prescription.setMedications(medications);

        prescription.setAssignedPatient(patient);
        prescription.setAssignedMedicalDoctor(medicalDoctor);
        prescriptionDAO.save(prescription);
        for(Medication medication: medications){
            medicationDAO.save(medication);
        }

    }

    public List<PrescriptionDTO> findPrescriptions() {
        List<Prescription> prescriptions = prescriptionDAO.findAll();
        return prescriptions.stream()
                .map(PrescriptionBuilder::toPrescriptionDTO)
                .collect(Collectors.toList());
    }

    public List<Prescription> findPrescriptionsByPatient(String username){
        Patient patient = patientDAO.findByName(username);
        if (patient!= null){
            return prescriptionDAO.findAllByPatientId(patient);
        }
        return null;
    }
}
