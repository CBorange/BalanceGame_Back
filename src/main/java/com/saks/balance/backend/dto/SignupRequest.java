package com.saks.balance.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequest {

    @NotBlank
    private String id;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;
}
