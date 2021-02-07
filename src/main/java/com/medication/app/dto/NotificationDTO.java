package com.medication.app.dto;

public class NotificationDTO {

    private String message;

    public NotificationDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
