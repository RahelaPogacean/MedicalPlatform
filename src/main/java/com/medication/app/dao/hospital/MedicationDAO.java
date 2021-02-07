package com.medication.app.dao.hospital;

import com.medication.app.dao.base.BaseDAO;
import com.medication.app.entity.hospital.Caregiver;
import com.medication.app.entity.hospital.Medication;
import org.springframework.stereotype.Repository;

public interface MedicationDAO extends BaseDAO<Medication, Long> {

    public Medication findByName(String name);


}
