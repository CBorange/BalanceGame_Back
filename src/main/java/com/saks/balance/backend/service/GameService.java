package com.saks.balance.backend.service;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saks.balance.backend.dto.HostGameRequest;
import com.saks.balance.backend.dto.MakeGameChoice;
import com.saks.balance.backend.entity.GameChoice;
import com.saks.balance.backend.entity.GameTopic;
import com.saks.balance.backend.entity.User;
import com.saks.balance.backend.repository.GameChoiceRepository;
import com.saks.balance.backend.repository.GameTopicRepository;
import com.saks.balance.backend.repository.UserRepository;
import com.saks.balance.states.GlobalStates;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {
    private final UserRepository userRepository;
    private final GameTopicRepository gameTopicRepository;
    private final GameChoiceRepository gameChoiceRepository;

    @Transactional
    public GameTopic createNewGame(HostGameRequest request){
        String hostId = request.getHostId();
        if(request.getHostId() == null || request.getHostId().isBlank()){

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth == null || !auth.isAuthenticated())
                throw new AuthenticationCredentialsNotFoundException("Authentication Token is null or not authenticated.");
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

        for (MakeGameChoice choice : request.getChoices()) {
            GameChoice newChoice = GameChoice.builder()
                .gameTopic(newGame)
                .title(choice.getTitle())
                .description(choice.getDescription())
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

            gameChoiceRepository.save(newChoice);
        }
        
        return newGame;
    }
}
