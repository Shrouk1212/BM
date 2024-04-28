package com.banquemisr.challenge05.TaskManagement.error;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(LocalDateTime now, int value, String reasonPhrase, String message, String description) {
    }
}
