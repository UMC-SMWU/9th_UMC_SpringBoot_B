package com.example.umc9th.domain.mission.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyMissionItem {
    private final Long missionId;
    private final String title;
    private final String storeName;
    private final String status;           // enum이면 타입을 enum으로 바꿔도 됨
    private final LocalDateTime updatedAt;
}

