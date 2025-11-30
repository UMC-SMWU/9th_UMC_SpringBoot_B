package com.example.chapter4.domain.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MyPageInfoDto {
    private final String name;
    private final String email;
    private final String phoneNum;
    private final Integer point;
}
