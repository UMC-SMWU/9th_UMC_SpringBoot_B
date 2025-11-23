package com.example.chapter4.domain.mission.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MemberMissionRequestDto {
    @Getter
    public static class CreateDto {
        @NotNull
        private Long missionId; // 어떤 미션에 도전할지 ID가 필요합니다.
    }
}
