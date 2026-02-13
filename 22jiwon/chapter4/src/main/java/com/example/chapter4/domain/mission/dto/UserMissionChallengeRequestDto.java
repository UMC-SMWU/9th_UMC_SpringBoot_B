package com.example.chapter4.domain.mission.dto;

import com.example.chapter4.global.annotation.ExistMission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserMissionChallengeRequestDto {
    @NotNull(message = "미션ID는 필수입니다.")
    @ExistMission
    private Long missionId;
    // 필요시 userId도 받음(인증 없이 테스트용), 보통은 SecurityContext에서 가져옴

    // 생성자 등 추가 필요시
}
