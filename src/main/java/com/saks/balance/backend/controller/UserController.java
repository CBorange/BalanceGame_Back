package com.saks.balance.backend.controller;

import com.saks.balance.backend.entity.User;
import com.saks.balance.backend.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final CustomUserDetailsService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.getAllUser());
    }
}
