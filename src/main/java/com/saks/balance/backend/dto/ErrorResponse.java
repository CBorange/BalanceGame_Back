package com.saks.balance.backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

/**
 * 400, 404, 500등 오류 발생 시 Error Message Container Response DTO
 */
public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private String timestamp;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }
}
