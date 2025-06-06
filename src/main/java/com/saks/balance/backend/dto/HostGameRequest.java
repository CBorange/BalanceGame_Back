package com.saks.balance.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.saks.balance.states.GlobalStates;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HostGameRequest {
    @NotBlank
    private String hostId;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private BigDecimal bet;

    @NotNull
    private GlobalStates.GameBetType betType;

    @NotNull
    @Future
    private LocalDateTime dueDate;

    @NotNull
    @Size(min = 2, max = 2, message = "게임 선택지는 항상 2개여야 합니다.")
    private MakeGameChoice[] choices;
}
