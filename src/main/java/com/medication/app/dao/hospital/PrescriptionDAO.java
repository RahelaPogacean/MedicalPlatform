package com.medication.app.dao.hospital;

import com.medication.app.dao.base.BaseDAO;
import com.medication.app.entity.hospital.Patient;
import com.medication.app.entity.hospital.Prescription;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PrescriptionDAO extends BaseDAO<Prescription, Long> {
    List<Prescription> findAllByPatientId(Patient patient);
}
