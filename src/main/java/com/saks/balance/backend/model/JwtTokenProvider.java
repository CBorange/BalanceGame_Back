package com.saks.balance.backend.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

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

    @PostConstruct
    protected void init(){
        secretKey = Keys.hmacShaKeyFor(keyString.getBytes(StandardCharsets.UTF_8));
    }

    // JWT 토큰 생성
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();

        String myName = userDetails.getUsername();
        claims.put("myName", myName);

        return Jwts.builder()
                .claims()
                .add("myName", myName)
                .and()
                .subject(myName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenExpirationTime))
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
            return false;
        }
    }
}