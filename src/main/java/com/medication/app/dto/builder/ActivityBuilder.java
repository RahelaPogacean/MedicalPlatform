package com.medication.app.dto.builder;

import com.medication.app.dto.ActivityDTO;
import com.medication.app.entity.hospital.Activity;

import java.sql.Date;

public class ActivityBuilder {

    public ActivityBuilder() {
    }

    public static ActivityDTO toActivityDTO(Activity activity) {

        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setName(activity.getName());
        activityDTO.setStartDate(activity.getStartDate().getTime());
        activityDTO.setEndDate(activity.getEndDate().getTime());

        return activityDTO;
    }

    public static Activity toEntity(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        activity.setEndDate(new Date(activityDTO.getEndDate()));
        activity.setStartDate(new Date(activityDTO.getStartDate()));
        activity.setName(activityDTO.getName());

        return activity;
    }
}

