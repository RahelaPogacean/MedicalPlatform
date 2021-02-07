package com.medication.app.service;

import com.medication.app.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    public void sendMessageNotification(NotificationDTO notificationDTO){
        simpMessageSendingOperations.convertAndSend("/message", notificationDTO);
    }

}
