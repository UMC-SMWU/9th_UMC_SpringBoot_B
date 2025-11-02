package com.example.chapter4.domain.mission.dto;

import com.example.chapter4.domain.mission.entity.Mission;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionResponseDto {
    private final Long id;
    private final String name;
    private final String description;
    private final Integer pointAwarded;
    private final LocalDateTime deadline;
    private final Long storeId;
    private final String storeName;

    public MissionResponseDto(Mission m) {
        this.id = m.getId();
        this.name = m.getName();
        this.description = m.getDescription();
        this.pointAwarded = m.getPointAwarded();
        this.deadline = m.getDeadline();
        this.storeId = m.getStore().getId();
        this.storeName = m.getStore().getName();
    }
}
