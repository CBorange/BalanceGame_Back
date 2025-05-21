package com.saks.balance.backend.service;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.saks.balance.backend.dto.HostGameRequest;
import com.saks.balance.backend.entity.GameTopic;
import com.saks.balance.backend.entity.User;
import com.saks.balance.backend.repository.GameTopicRepository;
import com.saks.balance.backend.repository.UserRepository;
import com.saks.balance.states.GlobalStates;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {
    private final UserRepository userRepository;
    private final GameTopicRepository gameTopicRepository;

    public GameTopic createNewGame(HostGameRequest request){
        String hostId = request.getHostId();
        if(request.getHostId() == null || request.getHostId().isBlank()){

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth == null || !auth.isAuthenticated())
                throw new AuthenticationCredentialsNotFoundException("Authentication Token is invalid");
            UserDetails userDetails = (UserDetails)auth.getPrincipal();
            hostId = userDetails.getUsername();
        }
        User hostUser = userRepository.getReferenceById(hostId);

        GameTopic newGame = GameTopic.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .bet(request.getBet())
            .betType(request.getBetType())
            .state(GlobalStates.GameState.Active)
            .user(hostUser)
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .dueDate(request.getDueDate())
            .build();

        gameTopicRepository.save(newGame);
        return newGame;
    }
}
