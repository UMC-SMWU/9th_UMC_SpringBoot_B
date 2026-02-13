package com.example.chapter4.domain.mission.dto;

import com.example.chapter4.domain.mission.entity.UserMissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class UserMissionListResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UserMissionPreviewDto {
        private Long missionId;
        private String missionName;
        private String storeName;
        private Integer pointAwarded;
        private LocalDateTime deadline;

        private UserMissionStatus status;
        private LocalDateTime challengedAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UserMissionPreviewListDto {
        private List<UserMissionPreviewDto> content;
        private int page;              // 1-based
        private int size;              // 10
        private long totalElements;
        private int totalPages;
        private boolean isFirst;
        private boolean isLast;
    }
}