package com.saks.balance.backend.dto;

import org.hibernate.validator.constraints.Range;

import com.saks.balance.states.GlobalStates;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequest {

    // Alphanumeric, 5-20 characters
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "아이디는 알파벳 또는 숫자, 5~20글자 이내여야 합니다.")
    private String id;

    // Nickname (Alphanumeric + underscores, 3-15 characters)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_\\p{IsHangul}]{3,15}$", message = "닉네임은 한글, 알파벳 또는 숫자 언더바만 포함할 수 있으며 3~15글자 이내여야 합니다.")
    private String username;

    // // Password (At least 8 characters, must include one letter, one number, and one special character)
    // @NotBlank
    // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
    //          message = "비밀번호는 최소 8글자 이상, 최소 한 개 이상의 알파벳 및 숫자를 포함하고 최소 한 개 이상의 특수문자를 포함해야 합니다.")
    // private String password;
    
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$",
         message = "비밀번호는 최소 5글자 이상, 최소 한 개 이상의 알파벳과 숫자를 포함해야 합니다.")
    private String password;

    // Email (Standard email format)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
             message = "올바른 이메일 양식이 아닙니다.")
    private String email;

    // Phone Number (Supports formats like 123-456-7890 or +1 123-456-7890)
    @Pattern(regexp = "^\\+?\\d{1,3}?[-.\\s]?\\(?\\d{1,4}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$",
             message = "올바른 전화번호 양식이 아닙니다.")
    private String phoneNumber;

    @Size(max = 200,
          message = "소개글은 최대 200글자 까지 가능합니다.")
    private String introduce;

    @NotNull(message = "나이는 필수 입력사항입니다.")
    @Min(value = 1, message = "1살 이상만 가입할 수 있습니다")
    @Max(value = 1000, message = "1000살 이하만 가입할 수 있습니다")
    private Integer age;

    @NotNull(message = "SNS 타입은 필수 입력 값 입니다.")
    private GlobalStates.SnsType snsType;
}
