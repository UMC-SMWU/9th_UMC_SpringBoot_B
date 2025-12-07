package com.example.chapter4.domain.mission.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MemberMissionRequestDto {
    @Getter
    public static class CreateDto {
        @NotNull
        private Long missionId; // 어떤 미션에 도전할지 ID가 필요합니다.
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionDetailDto {
        private Long missionId;
        private String storeName;
        private String content;
        private int point;
        private String deadline; // 날짜 포맷팅을 위해 String 처리 (Converter에서 변환)
        private Long daysLeft;   // D-Day 계산 등
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionPreviewListDto {
        private List<MemberMissionDetailDto> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
