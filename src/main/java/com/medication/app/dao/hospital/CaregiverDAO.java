package com.medication.app.dao.hospital;


import com.medication.app.dao.base.BaseDAO;
import com.medication.app.entity.hospital.Caregiver;
import com.medication.app.entity.hospital.MedicalDoctor;
import org.springframework.stereotype.Repository;


public interface CaregiverDAO extends BaseDAO<Caregiver, Long> {

    Caregiver findByName(String name);
    Boolean existsByName(String name);
    Boolean existsByPassword(String password);
}
