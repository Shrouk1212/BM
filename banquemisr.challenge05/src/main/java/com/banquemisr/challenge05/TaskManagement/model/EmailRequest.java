package com.banquemisr.challenge05.TaskManagement.model;

import lombok.Data;

@Data
public class EmailRequest {
    private String to;
    private String subject;
    private String content;

}

