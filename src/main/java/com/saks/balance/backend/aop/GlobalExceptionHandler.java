package com.saks.balance.backend.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.saks.balance.backend.controller.GameController;
import com.saks.balance.backend.dto.ErrorResponse;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GameController.class);

    // Handle @Valid / @Validated body validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException ex) {
        String firstError = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .orElse("Validation error");

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "잘못된 DTO 양식",
                firstError
        );

        logger.error("DTO Validation Error 400 return: {}", ex.getBindingResult().getFieldError().getDefaultMessage());

        // 추가로 전체 필드 오류 찍기
        ex.getBindingResult().getFieldErrors().forEach(ee ->
                logger.error("field: {}, rejected: {}, message: {}", ee.getField(), ee.getRejectedValue(), ee.getDefaultMessage())
        );

        logger.error("DTO Validation Error 400 return: " + firstError);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle path/query param validation errors
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        String firstError = ex.getConstraintViolations().stream()
                .findFirst()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .orElse("Constraint violation");

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "잘못된 URL 요청",
                firstError
        );

        logger.error("URI Path Validation Error 400 return: " + firstError);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle not found
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(EntityNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "데이터 찾을 수 없음",
                ex.getMessage()
        );

        logger.error("Entity Data not founded 404 return: ");
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "예상치 못한 서버 오류 발생",
                ex.getMessage()
        );

        logger.error("Unhandeled Exception occured 500 return: " + ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}