package com.example.chapter4.domain.user.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class UserSignupResponse {
    private Long userId;
    private String email;
    private String nickname;
    private LocalDateTime createdAt;
}
