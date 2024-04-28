package com.banquemisr.challenge05.TaskManagement.controller;

import com.banquemisr.challenge05.TaskManagement.model.EmailRequest;
import com.banquemisr.challenge05.TaskManagement.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public void sendEmailNotification(@RequestBody EmailRequest request) {
        String to = request.getTo();
        String subject = request.getSubject();
        String content = request.getContent();
        emailService.sendNotificationEmail(to, subject, content);
    }
}
