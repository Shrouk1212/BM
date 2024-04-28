package com.banquemisr.challenge05.TaskManagement.service;

public interface EmailService {

    public void sendNotificationEmail(String to, String subject, String content);
}
