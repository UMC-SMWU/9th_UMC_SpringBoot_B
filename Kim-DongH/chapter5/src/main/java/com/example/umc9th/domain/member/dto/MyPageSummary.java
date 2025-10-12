package com.example.umc9th.domain.member.dto;

public record MyPageSummary(
        Long memberId,
        String name,
        String email,      // phoneNumber로 바꿨다면 여기도 동일
        Integer point,
        Long reviewCount,
        Long likedFoodKinds
) {}
