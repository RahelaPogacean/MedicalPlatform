package com.medication.app.service.hospital;

import com.medication.app.dao.base.BaseDAO;
import com.medication.app.dao.hospital.ActivityDAO;
import com.medication.app.dao.hospital.PatientDAO;
import com.medication.app.dto.ActivityDTO;
import com.medication.app.dto.builder.ActivityBuilder;
import com.medication.app.entity.hospital.Activity;
import com.medication.app.service.base.CrudService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService extends CrudService<Activity, Long> {

    private final ActivityDAO activityDAO;
    private final PatientDAO patientDAO;

    @Inject
    public ActivityService(ActivityDAO activityDAO, PatientDAO patientDAO) {
        super(activityDAO);
        this.activityDAO = activityDAO;
        this.patientDAO = patientDAO;
    }
    public List<ActivityDTO> findActivities() {
        List<Activity> activities = activityDAO.findAll();
        return activities.stream()
                .map(ActivityBuilder::toActivityDTO)
                .collect(Collectors.toList());
    }

    public ActivityDTO findActivityById(Long id) {
        Optional<Activity> prosumerOptional = activityDAO.findById(id);
        if (!prosumerOptional.isPresent()) {
            throw new ResourceNotFoundException(Activity.class.getSimpleName() + " with id: " + id);
        }
        return ActivityBuilder.toActivityDTO(prosumerOptional.get());
    }

    public ActivityDTO findActivityByName(String name) {
        Optional<Activity> prosumerOptional = Optional.ofNullable(activityDAO.findByName(name));
        if (!prosumerOptional.isPresent()) {
            throw new ResourceNotFoundException(Activity.class.getSimpleName() + " with name: " + name);
        }
        return ActivityBuilder.toActivityDTO(prosumerOptional.get());
    }

    public void insert(ActivityDTO activityDTO) {

        Activity activity = ActivityBuilder.toEntity(activityDTO);
        activity.setPatient(patientDAO.findById(activityDTO.getPatientId()).orElse(null));
        activityDAO.save(activity);
    }

    public void deleteByName(String name){
        Activity activity = activityDAO.findByName(name);

        activityDAO.delete(activity);
    }

    public Activity getByName(String name){
        return activityDAO.findByName(name);
    }
}
