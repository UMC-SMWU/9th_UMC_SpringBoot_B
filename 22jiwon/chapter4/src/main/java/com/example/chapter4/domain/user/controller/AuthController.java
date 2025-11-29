package com.example.chapter4.domain.user.controller;

import com.example.chapter4.domain.user.dto.UserLoginRequest;
import com.example.chapter4.domain.user.dto.UserLoginResponse;
import com.example.chapter4.domain.user.dto.UserSignupRequest;
import com.example.chapter4.domain.user.dto.UserSignupResponse;
import com.example.chapter4.domain.user.service.UserService;
import com.example.chapter4.global.apiPayload.ApiResponse;
import com.example.chapter4.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ApiResponse<UserSignupResponse> signup(
            @RequestBody @Valid UserSignupRequest request
    ) {
        UserSignupResponse response = userService.signup(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, response);
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<UserLoginResponse> login(
            @RequestBody @Valid UserLoginRequest request
    ) {
        UserLoginResponse response = userService.login(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}