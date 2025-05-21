package com.saks.balance.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SigninRequest {
    @NotBlank
    private String id;

    @NotBlank
    private String password;
}
