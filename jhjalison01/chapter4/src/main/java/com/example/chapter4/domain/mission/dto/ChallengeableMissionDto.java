package com.example.chapter4.domain.mission.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ChallengeableMissionDto {
    // Mission 정보
    private final int point;
    private final Date deadline;
    private final String content;

    // Restaurant(Store) 정보
    private final String storeType;
    private final String storeName;
}
