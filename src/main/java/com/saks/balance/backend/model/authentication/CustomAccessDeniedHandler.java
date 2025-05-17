package com.saks.balance.backend.model.authentication;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
        response.setContentType("application/json;charset=UTF-8");

        // 원하는 커스텀 JSON 응답
        String json = "{\"error\": \"권한이 없습니다.\", \"message\": \"" + accessDeniedException.getMessage() + "\"}";

        response.getWriter().write(json);
    }
}
