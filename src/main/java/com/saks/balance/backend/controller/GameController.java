package com.saks.balance.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.RestController;

import com.saks.balance.backend.dto.HostGameRequest;
import com.saks.balance.backend.entity.GameTopic;
import com.saks.balance.backend.service.GameService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController("/game")
@RequiredArgsConstructor
public class GameController {
    private final Logger logger = LoggerFactory.getLogger(GameController.class);
    private final GameService gameService;

    @PostMapping()
    public ResponseEntity<?> hostGame(@RequestBody @Valid HostGameRequest request){
        try{
            GameTopic newGame = gameService.createNewGame(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
        } catch(AuthenticationCredentialsNotFoundException ex){
            logger.error("신규 게임생성 실패", ex);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
