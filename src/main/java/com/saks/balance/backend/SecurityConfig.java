package com.saks.balance.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // URL Route 시, DispatcherServlet 동작 전 Filter 단계에서 처리할 Spring Security Filter Bean 등록 및 설정
    // Spring Security는 기본적으로 Cookie, Session을 사용하여 유저정보를 저장한다.
    // 기본적인 formLogin 사용 시 로그인 성공하면 cookie를 생성하고(set-cookie header 반환)
    // 세션관리자에서 유저 세션을 생성한다(cookie 정보로)
    // 이 프로젝트에서는 JWT 사용
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((authorizeHttpRequests) -> {
            authorizeHttpRequests.anyRequest().permitAll();
        });

        return http.build();
    }

}
