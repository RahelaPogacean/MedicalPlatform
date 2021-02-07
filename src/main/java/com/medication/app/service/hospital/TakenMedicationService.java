package com.medication.app.service.hospital;

import assigment3.dto.MedicationRmiDto;
import com.medication.app.dao.base.BaseDAO;
import com.medication.app.dao.hospital.MedicalDoctorDAO;
import com.medication.app.dao.hospital.MedicationDAO;
import com.medication.app.dao.hospital.PatientDAO;
import com.medication.app.dao.hospital.TakenMedicationDao;
import com.medication.app.entity.hospital.Medication;
import com.medication.app.entity.hospital.Patient;
import com.medication.app.entity.hospital.TakenMedication;
import com.medication.app.service.base.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TakenMedicationService extends CrudService<TakenMedication, Long> {
    @Autowired
    private MedicationDAO medicationDAO;

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    public TakenMedicationService(TakenMedicationDao takenMedicationDao) {
        super(takenMedicationDao);
    }

    public void insert(MedicationRmiDto medicationRmiDto, boolean taken){
        TakenMedication takenMedication = new TakenMedication();
        Medication medication = medicationDAO.findByName(medicationRmiDto.getName());

        if (medication == null){
            throw new IllegalArgumentException("Medication not found");
        }
        Patient patient = patientDAO.findByName(medicationRmiDto.getPatient());
        if (patient == null){
            throw new IllegalArgumentException("Patient not found");
        }

        takenMedication.setMedication(medication);
        takenMedication.setPatient(patient);
        takenMedication.setTakenTime(medicationRmiDto.getTimeToTake());
        takenMedication.setTaken(taken);

        getBaseDAO().save(takenMedication);
    }
}
