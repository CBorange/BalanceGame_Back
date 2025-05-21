package com.saks.balance.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.saks.balance.states.GlobalStates;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class HostGameRequest {
    @Nullable
    private String hostId;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private BigDecimal bet;

    @NotBlank
    private GlobalStates.GameBetType betType;

    @NotBlank
    private LocalDateTime dueDate;
}
