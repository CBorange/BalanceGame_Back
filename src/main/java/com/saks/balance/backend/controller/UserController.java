package com.saks.balance.backend.controller;

import com.saks.balance.backend.dto.SigninRequest;
import com.saks.balance.backend.dto.SignupRequest;
import com.saks.balance.backend.entity.User;
import com.saks.balance.backend.model.JwtTokenProvider;
import com.saks.balance.backend.service.CustomExceptions;
import com.saks.balance.backend.service.CustomUserDetailsService;
import com.saks.balance.backend.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("signup")
    public ResponseEntity<String> signUpUser(@RequestBody @Valid SignupRequest request) {
        User generatedUser = userService.signUpUser(request);

        URI location = URI.create("/user/" + generatedUser.getId());
        return ResponseEntity.created(location)
                .body(generatedUser.getId());
    }
    
    @PostMapping("signin")
    public ResponseEntity<String> signIn(@RequestBody @Valid SigninRequest request){
        UserDetails user = userDetailsService.loadUserByUsername(request.getId());
        
        if(!userService.validatePassword(request.getId(), request.getPassword())){
            return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다.");
        }
        
        String token = tokenProvider.generateToken(user);
        return ResponseEntity.ok(token);
    }

    @GetMapping()
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.getAllUser());
    }
}
