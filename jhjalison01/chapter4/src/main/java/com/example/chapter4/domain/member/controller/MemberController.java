package com.example.chapter4.domain.member.controller;

import com.example.chapter4.domain.member.dto.req.MemberReqDto;
import com.example.chapter4.domain.member.dto.res.MemberResDto;
import com.example.chapter4.domain.member.exception.code.MemberSuccessCode;
import com.example.chapter4.domain.member.service.command.MemberCommandService;
import com.example.chapter4.global.apiPayLoad.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDto.JoinDto> signUp(
            @RequestBody @Valid MemberReqDto.JoinDto dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }
}
