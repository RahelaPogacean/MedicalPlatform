package com.medication.app.service.hospital;

import com.medication.app.dao.base.BaseDAO;
import com.medication.app.dao.hospital.CaregiverDAO;
import com.medication.app.dao.hospital.MedicalDoctorDAO;
import com.medication.app.dao.hospital.PatientDAO;
import com.medication.app.dto.CaregiverDTO;
import com.medication.app.dto.builder.CaregiverBuilder;
import com.medication.app.dto.builder.PatientBuilder;
import com.medication.app.entity.hospital.Caregiver;
import com.medication.app.entity.hospital.Patient;
import com.medication.app.service.base.CrudService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CaregiverService extends CrudService<Caregiver, Long> {
    private final CaregiverDAO caregiverDAO;

    @Inject
    public CaregiverService(CaregiverDAO caregiverDAO) {
        super(caregiverDAO);
        this.caregiverDAO = caregiverDAO;
    }

    public List<CaregiverDTO> findCaregivers() {
        List<Caregiver> caregivers = caregiverDAO.findAll();
        return caregivers.stream()
                .map(CaregiverBuilder::toCaregiverDTO)
                .collect(Collectors.toList());
    }

    public CaregiverDTO findCaregiverById(Long id) {
        Optional<Caregiver> prosumerOptional = caregiverDAO.findById(id);
        if (!prosumerOptional.isPresent()) {
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);
        }
        return CaregiverBuilder.toCaregiverDTO(prosumerOptional.get());
    }

    public CaregiverDTO findCaregiverByName(String name) {
        Optional<Caregiver> prosumerOptional = Optional.ofNullable(caregiverDAO.findByName(name));
        if (!prosumerOptional.isPresent()) {
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with name: " + name);
        }
        return CaregiverBuilder.toCaregiverDTO(prosumerOptional.get());
    }


    public void updateCaregiverById(Caregiver caregiver, String name, String password, Date birthdate, String gender, String address, String role) {

        caregiver.setName(name);
        caregiver.setBirthdate(birthdate);
        caregiver.setGender(gender);
        caregiver.setAddress(address);
        caregiver = caregiverDAO.save(caregiver);
        System.out.println("Caregiver with id"  + caregiver.getId() + " was updated in db");
    }

    public void updateCaregiverByName(CaregiverDTO caregiverDTO) {

        Caregiver caregiver = caregiverDAO.findByName(caregiverDTO.getName());

        caregiver.setBirthdate(caregiverDTO.getBirthdate());
        caregiver.setGender(caregiverDTO.getGender());
        caregiver.setAddress(caregiverDTO.getAddress());
        caregiver = caregiverDAO.save(caregiver);

        System.out.println("Caregiver with name"  + caregiver.getName() + " was updated in db");
    }


    public void insert(CaregiverDTO caregiverDTO) {

        Caregiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);
        caregiver = caregiverDAO.save(caregiver);
    }

    public void deleteByName(String name){
        Caregiver caregiver = caregiverDAO.findByName(name);
        caregiverDAO.delete(caregiver);
    }

    public Caregiver getByName(String name){
        return caregiverDAO.findByName(name);
    }

    public List<Patient> assignedPatients(Long id){

        Caregiver caregiver = caregiverDAO.findById(id).get();
        List<Patient> myPatients = caregiver.getPatients();

        return myPatients;
    }

    public boolean existByUsername(String username){
        return ((CaregiverDAO)getBaseDAO()).existsByName(username);
    }

}
