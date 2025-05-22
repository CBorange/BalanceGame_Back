package com.saks.balance.backend.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

/**
 * 400, 404, 500등 오류 발생 시 Error Message Container Response DTO
 */
@Schema(description = "에러 응답")
public class ErrorResponse {

    @Schema(description = "HTTP 상태 코드", example = "400")
    private int status;

    @Schema(description = "에러 요약", example = "잘못된 DTO 양식")
    private String error;

    @Schema(description = "자세한 에러 메시지", example = "name: must not be null")
    private String message;

    @Schema(description = "에러 발생 시각", example = "2025-05-22T13:45:58.619")
    private String timestamp;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }
}
