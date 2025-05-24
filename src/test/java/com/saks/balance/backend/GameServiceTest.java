package com.saks.balance.backend;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saks.balance.backend.dto.HostGameRequest;
import com.saks.balance.states.GlobalStates;

import jakarta.transaction.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

// 테스트 코드는 지금 사용안함, 예시용으로만 냅둠
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Transactional
public class GameServiceTest{
    @Autowired
    private MockMvc mvc;

    // DTO Json 변환을 위한 ObjectMapper
    @Autowired
    private ObjectMapper mapper;

    // @Test
    // public void createNewGame_Success() throws Exception{
    //     // given
    //     HostGameRequest request = new HostGameRequest(
    //         "testUser",
    //         "Test Game Title",
    //         "game description",
    //         new BigDecimal(1000),
    //         GlobalStates.GameBetType.FixedBet,
    //         LocalDateTime.now().plusMinutes(1)
    //     );
        
    //     String jsoString = mapper.writeValueAsString(request);
    //     // when & then
    //     mvc.perform(MockMvcRequestBuilders.post("/game")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(jsoString)
    //                     .with(csrf())
    //                     .with(user("testUser").roles("USER")))
    //             .andExpect(MockMvcResultMatchers.status().isCreated());
    // }
}