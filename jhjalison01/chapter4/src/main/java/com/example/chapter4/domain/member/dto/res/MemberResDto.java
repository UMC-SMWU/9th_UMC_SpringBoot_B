package com.example.chapter4.domain.member.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDto {
    @Builder
    public record JoinDto(
            Long memberId,
            LocalDateTime createAt
    ){}
}
