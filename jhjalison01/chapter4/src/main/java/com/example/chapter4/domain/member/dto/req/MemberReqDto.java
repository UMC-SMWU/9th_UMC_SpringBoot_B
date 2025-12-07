package com.example.chapter4.domain.member.dto.req;

import com.example.chapter4.domain.member.enums.Gender;
import com.example.chapter4.global.annotation.ExistFoods;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDto {
    public record JoinDto(
            @NotBlank
            String name,
            @Email
            String email, // 추가된 속성
            @NotBlank
            String password,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            String address,
            @NotNull
            String specAddress,
            @NotNull
            String phoneNum,
            @ExistFoods
            List<Long> preferCategory
    ){}

        // 로그인
        public record LoginDTO(
                @NotBlank
                String email,
                @NotBlank
                String password
        ){}




}
