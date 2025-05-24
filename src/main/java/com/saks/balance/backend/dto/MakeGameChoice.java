package com.saks.balance.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.saks.balance.states.GlobalStates;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MakeGameChoice {
    @NotBlank
    private String title;

    private String description;
}
