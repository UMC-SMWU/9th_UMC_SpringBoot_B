package com.example.chapter4.domain.user.dto;

import lombok.Getter;
@Getter
public class UserSignupRequest {
    private String email;
    private String password;
    private String nickname;
    private String phone;
    private boolean marketingAgree;
}
