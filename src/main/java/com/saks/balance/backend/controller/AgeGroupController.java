package com.saks.balance.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saks.balance.backend.entity.AgeGroup;
import com.saks.balance.backend.service.AgeGroupService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/age-group")
@Slf4j
public class AgeGroupController {
    private final AgeGroupService ageGroupService;

    @GetMapping("")
    public ResponseEntity<List<AgeGroup>> findAll(){
        return ResponseEntity.ok(ageGroupService.getAll());
    }
}
