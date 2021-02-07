package com.medication.app.service.hospital;

import com.medication.app.dao.base.BaseDAO;
import com.medication.app.dao.hospital.MedicationDAO;
import com.medication.app.dto.MedicationDTO;
import com.medication.app.dto.builder.MedicalDoctorBuilder;
import com.medication.app.dto.builder.MedicationBuilder;
import com.medication.app.entity.hospital.MedicalDoctor;
import com.medication.app.entity.hospital.Medication;
import com.medication.app.entity.hospital.Patient;
import com.medication.app.service.base.CrudService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MedicationService extends CrudService<Medication, Long> {

    private final MedicationDAO medicationDAO;

   @Inject
    public MedicationService(BaseDAO<Medication, Long> medicationDAO, MedicationDAO medicationDAO1) {
       super(medicationDAO);
       this.medicationDAO = medicationDAO1;
   }

    public List<MedicationDTO> findMedications() {
        List<Medication> medications = medicationDAO.findAll();
        return medications.stream()
                .map(MedicationBuilder::toMedicationDTO)
                .collect(Collectors.toList());
    }

    public MedicationDTO findMedicationById(Long id) {
        Optional<Medication> prosumerOptional = medicationDAO.findById(id);
        if (!prosumerOptional.isPresent()) {
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + id);
        }
        return MedicationBuilder.toMedicationDTO(prosumerOptional.get());
    }

    public MedicationDTO findMedicationByName(String name) {
        Optional<Medication> prosumerOptional = Optional.ofNullable(medicationDAO.findByName(name));
        if (!prosumerOptional.isPresent()) {
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with name: " + name);
        }
        return MedicationBuilder.toMedicationDTO(prosumerOptional.get());
    }

    public void updateMedicationById(Medication medication, String name, String description, String sideEffects, int dosage) {

        medication.setName(name);
        medication.setDescription(description);
        medication.setSideEffects(sideEffects);
        medication.setDosage(dosage);
        medication = medicationDAO.save(medication);
        System.out.println("Medication with id"  + medication.getId() + " was updated in db");
    }

    public void updateMedicationByName(MedicationDTO medicationDTO) {

        Medication medication = medicationDAO.findByName(medicationDTO.getName());

        medication.setDescription(medicationDTO.getDescription());
        medication.setSideEffects(medicationDTO.getSideEffects());
        medication.setDosage(medicationDTO.getDosage());

        medication = medicationDAO.save(medication);
        System.out.println("Medication with id"  + medication.getId() + " was updated in db");
    }


    public void insert(MedicationDTO medicationDTO) {

        Medication medication = MedicationBuilder.toEntity(medicationDTO);
        medication = medicationDAO.save(medication);

    }

    public void deleteByName(String name){
        Medication medication = medicationDAO.findByName(name);
        medicationDAO.delete(medication);
    }

    public Medication getByName(String name){
        return medicationDAO.findByName(name);
    }
}
