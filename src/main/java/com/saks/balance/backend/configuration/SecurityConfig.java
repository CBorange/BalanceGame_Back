package com.saks.balance.backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.saks.balance.backend.model.JwtAuthenticationFilter;
import com.saks.balance.backend.model.JwtTokenProvider;
import com.saks.balance.backend.model.authentication.CustomAccessDeniedHandler;
import com.saks.balance.backend.model.authentication.CustomAuthenticationEntryPont;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired 
    private CustomAuthenticationEntryPont customAuthenticationEntryPont;

    // URL Route 시, DispatcherServlet 동작 전 Filter 단계에서 처리할 Spring Security Filter Bean 등록 및 설정
    // Spring Security는 기본적으로 Cookie, Session을 사용하여 유저정보를 저장한다.
    // 기본적인 formLogin 사용 시 로그인 성공하면 cookie를 생성하고(set-cookie header 반환)
    // 세션관리자에서 유저 세션을 생성한다(cookie 정보로)
    // 이 프로젝트에서는 JWT 사용
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) -> {
            authorizeHttpRequests
                .requestMatchers("/age-group").hasRole("Admin")
                .anyRequest().permitAll();
        })
        .exceptionHandling((exceptionHanding) -> {
            exceptionHanding.accessDeniedHandler(customAccessDeniedHandler)
            .authenticationEntryPoint(customAuthenticationEntryPont);
        });
        
        http.csrf((csrf) -> {
            csrf.disable();
        });
        http.httpBasic((httpBasic) -> {
            httpBasic.disable();
        });
        http.formLogin((formLogin) -> {
            formLogin.disable();
        });
        http.sessionManagement((sessionManagement) -> {
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        http.addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
