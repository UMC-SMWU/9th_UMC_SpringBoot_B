package com.example.chapter4.domain.mission.dto;

import com.example.chapter4.domain.mission.entity.UserMissionCompletion;
import com.example.chapter4.domain.mission.entity.UserMissionStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserMissionChallengeResponseDto {

    private Long userMissionId;
    private Long userId;
    private Long missionId;
    private UserMissionStatus status;
    private LocalDateTime challengedAt;
    private LocalDateTime completedAt;

    @Builder
    public UserMissionChallengeResponseDto(
            Long userMissionId,
            Long userId,
            Long missionId,
            UserMissionStatus status,
            LocalDateTime challengedAt,
            LocalDateTime completedAt
    ) {
        this.userMissionId = userMissionId;
        this.userId = userId;
        this.missionId = missionId;
        this.status = status;
        this.challengedAt = challengedAt;
        this.completedAt = completedAt;
    }

    public static UserMissionChallengeResponseDto from(UserMissionCompletion entity) {
        return UserMissionChallengeResponseDto.builder()
                .userMissionId(entity.getId())
                .userId(entity.getUser().getId())
                .missionId(entity.getMission().getId())
                .status(entity.getStatus())
                .challengedAt(entity.getChallengedAt())
                .completedAt(entity.getCompletedAt())
                .build();
    }
}