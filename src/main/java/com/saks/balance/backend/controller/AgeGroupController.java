package com.saks.balance.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saks.balance.backend.entity.AgeGroup;
import com.saks.balance.backend.service.AgeGroupService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequiredArgsConstructor
@RequestMapping("/age-group")
public class AgeGroupController {
    private final Logger logger = LoggerFactory.getLogger(AgeGroupController.class);
    private final AgeGroupService ageGroupService;

    @GetMapping("")
    public ResponseEntity<List<AgeGroup>> findAll(){
        logger.info("test log");
        return ResponseEntity.ok(ageGroupService.getAll());
    }
}
