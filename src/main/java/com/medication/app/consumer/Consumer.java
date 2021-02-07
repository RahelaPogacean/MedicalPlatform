package com.medication.app.consumer;

import com.medication.app.dto.ActivityDTO;
import com.medication.app.dto.NotificationDTO;
import com.medication.app.service.MessageService;
import com.medication.app.service.hospital.ActivityService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private final Long TIME_TO_SLEEP = 25200000L; // 7 * 60 * 60 * 1000
    private final Long TIME_TO_LEAVE = 18000000L; // 5 * 60 * 60 * 1000
    private final Long TIME_IN_BATHROOM = 1800000L; // 30 * 60 * 1000
    private final String SLEEPING = "Sleeping";
    private final String LEAVING = "Leaving";
    private final String SHOWERING = "Showering";
    private final String GROOMING = "Grooming";
    private final String TOILETING = "Toileting";

    @Autowired
    private ActivityService activityService;

    @Autowired
    private MessageService messageService;

    @RabbitListener(queues="${jsa.rabbitmq.queue}", containerFactory="jsaFactory")
    public void receivedMessage(ActivityDTO activityDTO) {
        activityDTO.setPatientId(3L);
        if (SLEEPING.equals(activityDTO.getName()) && (activityDTO.getEndDate() - activityDTO.getStartDate()) > TIME_TO_SLEEP){
            messageService.sendMessageNotification(new NotificationDTO("Patient slept more than needed."));
        }

        if (LEAVING.equals(activityDTO.getName()) && (activityDTO.getEndDate() - activityDTO.getStartDate()) > TIME_TO_LEAVE){
            messageService.sendMessageNotification(new NotificationDTO("Patient left more than required time."));
        }

        if ((GROOMING.equals(activityDTO.getName()) || SHOWERING.equals(activityDTO.getName()) || TOILETING.equals(activityDTO.getName()))
                && (activityDTO.getEndDate() - activityDTO.getStartDate()) > TIME_IN_BATHROOM){
            messageService.sendMessageNotification(new NotificationDTO("Patient spent time in bathroom more than needed."));
        }
        System.out.println(activityDTO);
        activityService.insert(activityDTO);
    }
}