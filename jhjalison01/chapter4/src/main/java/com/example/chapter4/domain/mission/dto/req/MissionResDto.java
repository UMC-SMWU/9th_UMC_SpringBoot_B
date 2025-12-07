package com.example.chapter4.domain.mission.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MissionResDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDetailDto {
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
    public static class MissionPreviewListDto {
        private List<MissionDetailDto> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
