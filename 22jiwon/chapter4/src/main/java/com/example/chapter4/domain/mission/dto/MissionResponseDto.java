package com.example.chapter4.domain.mission.dto;

import com.example.chapter4.domain.mission.entity.Mission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MissionResponseDto {
    private Long id;
    private String name;
    private String description;
    private Integer pointAwarded;
    private LocalDateTime deadline;
    private Long storeId;
    private String storeName;

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