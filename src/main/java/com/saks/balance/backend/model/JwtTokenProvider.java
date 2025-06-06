package com.saks.balance.backend.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.saks.balance.backend.controller.AgeGroupController;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String keyString;

    @Value("${jwt.expire}")
    private long tokenExpirationTime;

    private final UserDetailsService userDetailsService;
    private SecretKey secretKey;
    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @PostConstruct
    protected void init(){
        secretKey = Keys.hmacShaKeyFor(keyString.getBytes(StandardCharsets.UTF_8));
    }

    // JWT 토큰 생성
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();

        String userName = userDetails.getUsername();
        claims.put("userName", userName);

        return Jwts.builder()
                .claims()
                .add("userName", userName)
                .and()
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenExpirationTime * 1000))
                .signWith(secretKey)
                .compact();

    }

    // JWT 토큰에서 Authentication 획득
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원정보(유저명) 획득
    public String getUsername(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getSubject();
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken){
        try{
            return !Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwtToken).getPayload().getExpiration()
                    .before(new Date(System.currentTimeMillis()));
        } catch (Exception e){
            logger.error("유효하지 않은 Jwt 토큰", e);
            return false;
        }
    }
}