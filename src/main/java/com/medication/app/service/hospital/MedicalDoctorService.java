package com.medication.app.service.hospital;

import com.medication.app.dao.hospital.MedicalDoctorDAO;
import com.medication.app.dto.MedicalDoctorDTO;
import com.medication.app.dto.UserDetailsDTO;
import com.medication.app.dto.builder.MedicalDoctorBuilder;
import com.medication.app.entity.hospital.*;
import com.medication.app.service.base.CrudService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicalDoctorService extends CrudService<MedicalDoctor, Long> {

    private final MedicalDoctorDAO medicalDoctorDAO;

    @Inject
    public MedicalDoctorService(MedicalDoctorDAO medicalDoctorDAO ) {
        super(medicalDoctorDAO);
        this.medicalDoctorDAO = medicalDoctorDAO;
    }

    public List<MedicalDoctorDTO> findDoctors() {
        List<MedicalDoctor> medicalDoctors = medicalDoctorDAO.findAll();
        return medicalDoctors.stream()
                .map(MedicalDoctorBuilder::toMedicalDoctorDTO)
                .collect(Collectors.toList());
    }

    public MedicalDoctorDTO findDoctorById(Long id) {
        Optional<MedicalDoctor> prosumerOptional = medicalDoctorDAO.findById(id);
        if (!prosumerOptional.isPresent()) {
            throw new ResourceNotFoundException(MedicalDoctor.class.getSimpleName() + " with id: " + id);
        }
        return MedicalDoctorBuilder.toMedicalDoctorDTO(prosumerOptional.get());
    }

    public MedicalDoctorDTO findDoctorByName(String name) {
        Optional<MedicalDoctor> prosumerOptional = Optional.ofNullable(medicalDoctorDAO.findByName(name)).get();
        if (!prosumerOptional.isPresent()) {
            throw new ResourceNotFoundException(MedicalDoctor.class.getSimpleName() + " with name: " + name);
        }
        return MedicalDoctorBuilder.toMedicalDoctorDTO(prosumerOptional.get());
    }


    public void updateMedicalDoctor(MedicalDoctor medicalDoctor, String name, String password, Date birthdate, String gender, String address, String role) {

        medicalDoctor.setName(name);
        medicalDoctor.setPassword(password);
        medicalDoctor.setBirthdate(birthdate);
        medicalDoctor.setGender(gender);
        medicalDoctor.setAddress(address);
        medicalDoctor.setRole(role);
        medicalDoctor = medicalDoctorDAO.save(medicalDoctor);
        System.out.println("Medical doctor with id"  + medicalDoctor.getId() + "was updated in db");
    }


    public void insert(MedicalDoctorDTO medicalDoctorDTO) {

        MedicalDoctor medicalDoctor = MedicalDoctorBuilder.toEntity(medicalDoctorDTO);
        medicalDoctor = medicalDoctorDAO.save(medicalDoctor);
    }

    public boolean existByUsername(String username){
        return ((MedicalDoctorDAO)getBaseDAO()).existsByName(username);
    }

}
