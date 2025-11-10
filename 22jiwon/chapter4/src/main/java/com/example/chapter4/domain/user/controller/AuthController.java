package com.example.chapter4.domain.user.controller;

import com.example.chapter4.domain.user.dto.UserSignupRequest;
import com.example.chapter4.domain.user.dto.UserSignupResponse;
import com.example.chapter4.domain.user.dto.UserLoginRequest;
import com.example.chapter4.domain.user.dto.UserLoginResponse;
import com.example.chapter4.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import com.example.chapter4.global.apiPayload.ApiResponse;
import com.example.chapter4.global.apiPayload.code.GeneralSuccessCode;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    // 1. 반환 타입 변경
    public ApiResponse<UserSignupResponse> signup(@RequestBody @Valid UserSignupRequest request) {
        UserSignupResponse response = userService.signup(request);
        // 2. ApiResponse.onSuccess 사용 (CREATED 코드 사용)
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, response);
    }

    @PostMapping("/login")
    // 1. 반환 타입 변경
    public ApiResponse<UserLoginResponse> login(@RequestBody @Valid UserLoginRequest request) {
        UserLoginResponse response = userService.login(request);
        // 2. ApiResponse.onSuccess 사용 (OK 코드 사용)
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}
