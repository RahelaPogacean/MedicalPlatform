package com.medication.app.dao.hospital;

import com.medication.app.dao.base.BaseDAO;
import com.medication.app.entity.hospital.MedicalDoctor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface MedicalDoctorDAO extends BaseDAO<MedicalDoctor, Long> {

     Optional<MedicalDoctor> findByName(String name);
     Boolean existsByName(String name);
     Boolean existsByPassword(String password);
}
