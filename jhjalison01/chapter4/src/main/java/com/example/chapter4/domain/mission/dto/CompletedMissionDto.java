package com.example.chapter4.domain.mission.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CompletedMissionDto {
    private final int point;
    private final String content;
    private final Long userMissionId;
}
