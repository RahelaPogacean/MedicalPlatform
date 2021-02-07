package com.medication.app.dao.hospital;


import com.medication.app.dao.base.BaseDAO;
import com.medication.app.entity.hospital.MedicalDoctor;
import com.medication.app.entity.hospital.Patient;
import org.springframework.stereotype.Repository;


public interface PatientDAO extends BaseDAO<Patient, Long> {

    Patient findByName(String name);
    Boolean existsByName(String name);
    Boolean existsByPassword(String password);
}
